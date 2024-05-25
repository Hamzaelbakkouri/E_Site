package com.app.ouadia.Models.Dto.Product;

import com.app.ouadia.Models.Entity.AssignSize;
import com.app.ouadia.Models.Entity.Category;
import com.app.ouadia.Models.Enums.PriceCategory;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
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
public class ProductDtoRequest implements Serializable {
    private UUID id;
    @NotBlank(message = "name must be not null")
    private String name;
    @NotBlank(message = "description must be not null")
    private String description;
    @NotNull(message = "promotion must be not null")
    private double promotion;
    @NotNull(message = "priceType must be not null")
    private PriceCategory priceType;
    @NotNull(message = "category id must be not null")
    private UUID category;

}
