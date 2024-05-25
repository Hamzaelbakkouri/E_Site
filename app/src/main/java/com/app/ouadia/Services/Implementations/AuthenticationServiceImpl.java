package com.app.ouadia.Services.Implementations;

import com.app.ouadia.Exceptions.ResourceNotFoundException;
import com.app.ouadia.Models.Dto.Auth.Authentication.AuthenticationRequest;
import com.app.ouadia.Models.Dto.Auth.Authentication.AuthenticationResponse;
import com.app.ouadia.Models.Dto.Auth.RegisterRequest;
import com.app.ouadia.Models.Dto.Person.Profile;
import com.app.ouadia.Models.Entity.Person;
import com.app.ouadia.Models.Entity.Token.Token;
import com.app.ouadia.Models.Enums.Role;
import com.app.ouadia.Models.Enums.TokenType;
import com.app.ouadia.Repository.PersonRepository;
import com.app.ouadia.Repository.TokenRepository;
import com.app.ouadia.Security.JwtService;
import com.app.ouadia.Services.Specs.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtService jwtService;
    private final PersonRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;


    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        return this.createUser(request, Role.ADMIN);
    }
    public AuthenticationResponse register(RegisterRequest request) {
        return this.createUser(request, Role.USER);
    }

    private AuthenticationResponse createUser(RegisterRequest request, Role role) {
        log.info("Creating a new user with role: {}", role);

        var user = Person.builder().firstName(request.getFirstname()).address(request.getAddress()).phoneNumber(request.getPhoneNumber()).lastName(request.getLastname()).email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).build();
        user.setRole(role);

        var savedUser = repository.save(user);
        log.info("User with ID {} created successfully.", savedUser.getId());

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        saveUserToken(savedUser, jwtToken);
        log.info("Access and refresh tokens generated and saved for user with ID: {}", savedUser.getId());

        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException ex) {
            log.error("Authentication failed for user: {}", request.getEmail(), ex);
            throw new BadCredentialsException("Invalid credentials");
        } catch (AuthenticationException ex) {
            log.error("Authentication failed for user: {}", request.getEmail(), ex);
            throw new ResourceNotFoundException("Authentication failed");
        }
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    private void saveUserToken(Person user, String jwtToken) {
        var token = Token.builder().person(user).token(jwtToken).tokenType(TokenType.BEARER).expired(false).revoked(false).build();
        tokenRepository.save(token);
    }


    private void revokeAllUserTokens(Person user) {
        var validUserTokens = tokenRepository.findAllValidTokenByPerson(user.getId());
        if (!validUserTokens.isEmpty()) {
            validUserTokens.forEach(token -> {
                token.setExpired(true);
                token.setRevoked(true);
            });
            tokenRepository.saveAll(validUserTokens);
        }
    }


    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUserName(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail).orElseThrow(() -> new ResourceNotFoundException("User not found"));
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder().accessToken(accessToken).build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }


    public Boolean checkToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return false;
        }
        String jwt = token.substring(7);
        var userEmail = jwtService.extractUserName(jwt);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail).orElseThrow(() -> new ResourceNotFoundException("User not found"));
            return jwtService.isTokenValid(jwt, user);
        }
        return false;
    }

    public Optional<Profile> getCurrentUser() {
        Optional<Person> person = this.repository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (person.isPresent()) {
            Profile profileDtoResponse = this.modelMapper.map(person.get(), Profile.class);
            return Optional.of(profileDtoResponse);
        }
        throw new ResourceNotFoundException("Person Profile Not Found");
    }
}

