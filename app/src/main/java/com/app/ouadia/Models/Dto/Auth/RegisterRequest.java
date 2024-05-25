package com.app.ouadia.Models.Dto.Auth;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements Serializable {
    @NotNull(message = "First name cannot be null")
    @Size(min = 1, message = "First name cannot be empty")
    @Size(max = 30, message = "First name is too long")
    String firstname;

    @Size(max = 30, message = "Last name is too long")
    String lastname;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email was not provided")
    @Size(max = 80, message = "Email is too long")
    @Column(unique = true)
    String email;

    @NotNull(message = "Address cannot be null")
    @Size(min = 1, message = "Address cannot be empty")
    String address;

    @NotNull(message = "Password cannot be null")
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password is too short")
    String password;

    @NotNull(message = "Phone Number cannot be null")
    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "0\\d{9}", message = "Phone number must match the format '0XXXXXXXXX'")
    String phoneNumber;
}

