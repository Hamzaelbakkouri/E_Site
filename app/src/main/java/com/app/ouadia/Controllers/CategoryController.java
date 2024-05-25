package com.app.ouadia.Controllers;

import com.app.ouadia.Models.Dto.Category.CategoryDtoRequest;
import com.app.ouadia.Models.Dto.Category.CategoryDtoResponse;
import com.app.ouadia.Services.Specs.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Validated
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDtoResponse> createCategory(@Valid @RequestBody CategoryDtoRequest categoryDtoRequest) {
        return categoryService.save(categoryDtoRequest)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<CategoryDtoResponse> updateCategory(@Valid @RequestBody CategoryDtoRequest categoryDtoRequest, @PathVariable UUID uuid) {
        return categoryService.update(categoryDtoRequest, uuid)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable UUID uuid) {
        Boolean deleted = categoryService.delete(uuid);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<CategoryDtoResponse> getCategoryById(@PathVariable UUID uuid) {
        return categoryService.getById(uuid)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDtoResponse>> getAllCategories(Pageable pageable) {
        Page<CategoryDtoResponse> categories = categoryService.paginate(pageable);
        return ResponseEntity.ok(categories);
    }
}