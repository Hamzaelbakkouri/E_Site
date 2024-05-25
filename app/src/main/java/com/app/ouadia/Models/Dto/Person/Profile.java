package com.app.ouadia.Models.Dto.Person;


import com.app.ouadia.Models.Enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
public class Profile implements Serializable {
    private UUID id;
    private String firstName;
    private String lastName;
    private String address;
    private Role role;
    private String email;
    private String phoneNumber;
}
