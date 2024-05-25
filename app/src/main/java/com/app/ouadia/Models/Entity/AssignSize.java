package com.app.ouadia.Models.Entity;

import com.app.ouadia.Models.Entity.Keys.AssignSizeEmbedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public final class AssignSize {
    @Id
    @EmbeddedId
    private AssignSizeEmbedded id;

    private Integer size_number;

    private double height;
    private double waist;
    private double chest;
    private double neck;
    private double armLength;
    private double hip;
}
