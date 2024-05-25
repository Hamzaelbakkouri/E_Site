package com.app.ouadia.Models.Dto.Size;

import com.app.ouadia.Models.Entity.AssignSize;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class SizeDtoRequest {
    private UUID id;
    @NotNull(message = "Mark must be not null")
    private String Mark;
    @NotNull(message = "message must be not null")
    private String description;

}
