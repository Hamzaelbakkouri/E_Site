package com.app.ouadia.Controllers;

import com.app.ouadia.Exceptions.ResourceNotFoundException;
import com.app.ouadia.Models.Dto.Auth.Authentication.AuthenticationRequest;
import com.app.ouadia.Models.Dto.Auth.Authentication.AuthenticationResponse;
import com.app.ouadia.Models.Dto.Auth.RegisterRequest;
import com.app.ouadia.Services.Specs.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthenticationController {
    private final AuthenticationService service;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody final RegisterRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new ResourceNotFoundException(bindingResult.toString());

        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody final AuthenticationRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new ResourceNotFoundException(bindingResult.toString());

        return ResponseEntity.ok(service.authenticate(request));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@Valid @RequestBody final RegisterRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new ResourceNotFoundException(bindingResult.toString());

        return ResponseEntity.ok(service.registerAdmin(request));
    }
}
