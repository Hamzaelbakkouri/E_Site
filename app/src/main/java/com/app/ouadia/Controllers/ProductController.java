package com.app.ouadia.Controllers;

import com.app.ouadia.Models.Dto.Product.ProductDtoRequest;
import com.app.ouadia.Models.Dto.Product.ProductDtoResponse;
import com.app.ouadia.Services.Specs.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@Validated
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDtoResponse> createProduct(@RequestBody ProductDtoRequest productDtoRequest) {
        return productService.save(productDtoRequest)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ProductDtoResponse> updateProduct(@RequestBody ProductDtoRequest productDtoRequest, @PathVariable UUID uuid) {
        return productService.update(productDtoRequest, uuid)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable UUID uuid) {
        Boolean deleted = productService.delete(uuid);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ProductDtoResponse> getProductById(@PathVariable UUID uuid) {
        return productService.getById(uuid)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<Page<ProductDtoResponse>> getAllProducts(Pageable pageable) {
        Page<ProductDtoResponse> products = productService.paginate(pageable);
        return ResponseEntity.ok(products);
    }
}
