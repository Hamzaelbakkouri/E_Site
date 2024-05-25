package com.app.ouadia.Services.Implementations;

import com.app.ouadia.Exceptions.ResourceNotFoundException;
import com.app.ouadia.Models.Dto.Category.CategoryDtoRequest;
import com.app.ouadia.Models.Dto.Category.CategoryDtoResponse;
import com.app.ouadia.Models.Entity.Category;
import com.app.ouadia.Repository.CategoryRepository;
import com.app.ouadia.Services.Specs.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<CategoryDtoResponse> save(CategoryDtoRequest categoryDtoRequest) {
        Category category = modelMapper.map(categoryDtoRequest, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return Optional.of(modelMapper.map(savedCategory, CategoryDtoResponse.class));
    }

    @Override
    public Optional<CategoryDtoResponse> update(CategoryDtoRequest categoryDtoRequest, UUID uuid) {
        Category existingCategory = categoryRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + uuid));

        Category updatedCategory = modelMapper.map(categoryDtoRequest, Category.class);
        updatedCategory.setId(existingCategory.getId());

        Category savedCategory = categoryRepository.save(updatedCategory);
        return Optional.of(modelMapper.map(savedCategory, CategoryDtoResponse.class));
    }

    @Override
    public Boolean delete(UUID uuid) {
        Category existingCategory = categoryRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + uuid));

        categoryRepository.delete(existingCategory);
        return true;
    }

    @Override
    public Optional<CategoryDtoResponse> getById(UUID uuid) {
        Category category = categoryRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + uuid));
        return Optional.of(modelMapper.map(category, CategoryDtoResponse.class));
    }

    @Override
    public Page<CategoryDtoResponse> paginate(Pageable pageable) {
        Page<Category> categoriesPage = categoryRepository.findAll(pageable);
        return categoriesPage.map(category -> modelMapper.map(category, CategoryDtoResponse.class));
    }
}