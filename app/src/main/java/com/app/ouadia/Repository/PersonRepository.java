package com.app.ouadia.Repository;

import com.app.ouadia.Models.Entity.Person;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person , UUID> {
    Optional<Person> findByEmail(@Email(message = "Email was not provided") String email);
}
