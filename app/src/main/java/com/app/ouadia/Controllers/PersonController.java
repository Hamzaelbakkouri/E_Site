package com.app.ouadia.Controllers;

import com.app.ouadia.Models.Dto.Person.Profile;
import com.app.ouadia.Services.Specs.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/person")
@Validated
public class PersonController {
    private final AuthenticationService service;

    @GetMapping("/profile")
    public ResponseEntity<?> getCurrentUser() {
        Optional<Profile> authenticatedPerson = this.service.getCurrentUser();
        if (authenticatedPerson.isPresent()) {
            return ResponseEntity.ok(authenticatedPerson.get());
        }
        return ResponseEntity.ofNullable("Person Not Found , Try Sign In");
    }
}
