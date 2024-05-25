package com.app.ouadia.Models.Dto.Category;

import com.app.ouadia.Models.Dto.Product.ProductDtoRequest;
import com.app.ouadia.Models.Enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDtoResponse implements Serializable {
    private UUID id;
    private String name;
    private String description;
    private Gender gender;
    private List<ProductDtoRequest> products;
}
