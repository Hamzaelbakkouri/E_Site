package com.app.ouadia.Services.Specs;

import com.app.ouadia.Models.Dto.Product.ProductDtoRequest;
import com.app.ouadia.Models.Dto.Product.ProductDtoResponse;

import java.util.UUID;

public interface ProductService extends G_Type<ProductDtoRequest, ProductDtoResponse, UUID> {
}
