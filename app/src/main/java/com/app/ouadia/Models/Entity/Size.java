package com.app.ouadia.Models.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Builder
@NoArgsConstructor
public final class Size {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    @Column(unique = true)
    private String Mark;
    private String description;

    @OneToMany(mappedBy = "id.size")
    private List<AssignSize> assignSizes;

    @OneToMany(mappedBy = "size")
    private List<Request> requests;
}
