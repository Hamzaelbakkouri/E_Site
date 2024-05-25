package com.app.ouadia.Services.Specs;

import com.app.ouadia.Models.Dto.Auth.Authentication.AuthenticationRequest;
import com.app.ouadia.Models.Dto.Auth.Authentication.AuthenticationResponse;
import com.app.ouadia.Models.Dto.Auth.RegisterRequest;
import com.app.ouadia.Models.Dto.Person.Profile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public interface AuthenticationService {
    AuthenticationResponse registerAdmin(RegisterRequest request);

    AuthenticationResponse register(RegisterRequest request);


    AuthenticationResponse authenticate(AuthenticationRequest request);


    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    Boolean checkToken(String token);
    Optional<Profile> getCurrentUser();
}
