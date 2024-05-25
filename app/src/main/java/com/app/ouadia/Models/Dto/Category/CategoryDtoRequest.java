package com.app.ouadia.Models.Dto.Category;

import com.app.ouadia.Models.Entity.Product;
import com.app.ouadia.Models.Enums.Gender;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDtoRequest implements Serializable {
    private UUID id;
    @NotBlank(message = "name must be not blank")
    private String name;
    @NotBlank(message = "name must be not blank")
    private String description;
    @NotNull(message = "name must be not Null")
    private Gender gender;
}
