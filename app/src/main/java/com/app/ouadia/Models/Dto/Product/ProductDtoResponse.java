package com.app.ouadia.Models.Dto.Product;

import com.app.ouadia.Models.Dto.AssignSize.AssignSizeDtoRequest;
import com.app.ouadia.Models.Dto.Category.CategoryDtoRequest;
import com.app.ouadia.Models.Entity.AssignSize;
import com.app.ouadia.Models.Entity.Category;
import com.app.ouadia.Models.Enums.PriceCategory;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ProductDtoResponse {
    private UUID id;
    private String name;
    private String description;
    private double promotion;
    private PriceCategory priceType;
    private CategoryDtoRequest category;
    private List<AssignSizeDtoRequest> assignSizes;
}
