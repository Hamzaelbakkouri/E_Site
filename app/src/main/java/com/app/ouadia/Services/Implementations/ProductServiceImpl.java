package com.app.ouadia.Services.Implementations;

import com.app.ouadia.Exceptions.ResourceNotFoundException;
import com.app.ouadia.Models.Dto.Product.ProductDtoRequest;
import com.app.ouadia.Models.Dto.Product.ProductDtoResponse;
import com.app.ouadia.Models.Entity.Category;
import com.app.ouadia.Models.Entity.Product;
import com.app.ouadia.Repository.CategoryRepository;
import com.app.ouadia.Repository.ProductRepository;
import com.app.ouadia.Services.Specs.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<ProductDtoResponse> save(ProductDtoRequest productDtoRequest) {
        Category category = this.categoryRepository.findById(productDtoRequest.getCategory()).orElseThrow(() -> new ResourceNotFoundException("Category Not Found "));
        Product product = modelMapper.map(productDtoRequest, Product.class);
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return Optional.of(modelMapper.map(savedProduct, ProductDtoResponse.class));
    }

    @Override
    public Optional<ProductDtoResponse> update(ProductDtoRequest productDtoRequest, UUID uuid) {
        Product existingProduct = productRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + uuid));
        Category category = this.categoryRepository.findById(productDtoRequest.getCategory()).orElseThrow(() -> new ResourceNotFoundException("Category Not Found "));

        Product updatedProduct = modelMapper.map(productDtoRequest, Product.class);
        updatedProduct.setCategory(category);
        updatedProduct.setId(existingProduct.getId());

        Product savedProduct = productRepository.save(updatedProduct);
        return Optional.of(modelMapper.map(savedProduct, ProductDtoResponse.class));
    }

    @Override
    public Boolean delete(UUID uuid) {
        Product existingProduct = productRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + uuid));

        productRepository.delete(existingProduct);
        return true;
    }

    @Override
    public Optional<ProductDtoResponse> getById(UUID uuid) {
        Product product = productRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + uuid));
        return Optional.of(modelMapper.map(product, ProductDtoResponse.class));
    }

    @Override
    public Page<ProductDtoResponse> paginate(Pageable pageable) {
        Page<Product> productsPage = productRepository.findAll(pageable);
        return productsPage.map(product -> modelMapper.map(product, ProductDtoResponse.class));
    }
}