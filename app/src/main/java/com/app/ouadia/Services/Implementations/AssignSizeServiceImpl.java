package com.app.ouadia.Services.Implementations;

import com.app.ouadia.Exceptions.ResourceNotFoundException;
import com.app.ouadia.Models.Dto.AssignSize.AssignSizeDtoRequest;
import com.app.ouadia.Models.Dto.AssignSize.AssignSizeDtoResponse;
import com.app.ouadia.Models.Dto.Keys.AssignSizeEmbeddedRequest;
import com.app.ouadia.Models.Entity.AssignSize;
import com.app.ouadia.Models.Entity.Keys.AssignSizeEmbedded;
import com.app.ouadia.Repository.AssignSizeRepository;
import com.app.ouadia.Repository.ProductRepository;
import com.app.ouadia.Repository.SizeRepository;
import com.app.ouadia.Services.Specs.AssignSizeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignSizeServiceImpl implements AssignSizeService {
    private final AssignSizeRepository assignSizeRepository;
    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<AssignSizeDtoResponse> save(AssignSizeDtoRequest assignSizeDtoRequest) {
        AssignSizeEmbedded assignSizeEmbedded = new AssignSizeEmbedded();
        assignSizeEmbedded.setProduct(this.productRepository.findById(assignSizeDtoRequest.getId().getProduct()).orElseThrow(() -> new ResourceNotFoundException("Product Not Found")));
        assignSizeEmbedded.setSize(this.sizeRepository.findById(assignSizeDtoRequest.getId().getSize()).orElseThrow(() -> new ResourceNotFoundException("Size Not Found")));

        AssignSize assignSize = modelMapper.map(assignSizeDtoRequest, AssignSize.class);
        assignSize.setId(assignSizeEmbedded);

        AssignSize savedAssignSize = assignSizeRepository.save(assignSize);
        return Optional.of(modelMapper.map(savedAssignSize, AssignSizeDtoResponse.class));
    }

    @Override
    public Optional<AssignSizeDtoResponse> update(AssignSizeDtoRequest assignSizeDtoRequest, AssignSizeEmbeddedRequest assignSizeEmbeddedRequest) {
        AssignSizeEmbedded assignSizeEmbedded = new AssignSizeEmbedded();
        assignSizeEmbedded.setProduct(this.productRepository.findById(assignSizeEmbeddedRequest.getProduct()).orElseThrow(() -> new ResourceNotFoundException("Product Not Found")));
        assignSizeEmbedded.setSize(this.sizeRepository.findById(assignSizeEmbeddedRequest.getSize()).orElseThrow(() -> new ResourceNotFoundException("Size Not Found")));

        Optional<AssignSize> assignSizeOptional = assignSizeRepository.findById(assignSizeEmbedded);

        if (assignSizeOptional.isPresent()) {
            AssignSize assignSize = assignSizeOptional.get();
            modelMapper.map(assignSizeDtoRequest, assignSize);

            AssignSize updatedAssignSize = assignSizeRepository.save(assignSize);
            return Optional.of(modelMapper.map(updatedAssignSize, AssignSizeDtoResponse.class));
        }

        return Optional.empty();
    }

    @Override
    public Boolean delete(AssignSizeEmbeddedRequest assignSizeEmbeddedRequest) {
        AssignSizeEmbedded assignSizeEmbedded = new AssignSizeEmbedded();
        assignSizeEmbedded.setProduct(this.productRepository.findById(assignSizeEmbeddedRequest.getProduct()).orElseThrow(() -> new ResourceNotFoundException("Product Not Found")));
        assignSizeEmbedded.setSize(this.sizeRepository.findById(assignSizeEmbeddedRequest.getSize()).orElseThrow(() -> new ResourceNotFoundException("Size Not Found")));
        Optional<AssignSize> assignSizeOptional = assignSizeRepository.findById(assignSizeEmbedded);

        if (assignSizeOptional.isPresent()) {
            assignSizeRepository.delete(assignSizeOptional.get());
            return true;
        }

        return false;
    }

    @Override
    public Optional<AssignSizeDtoResponse> getById(AssignSizeEmbeddedRequest assignSizeEmbeddedRequest) {
        AssignSizeEmbedded assignSizeEmbedded = new AssignSizeEmbedded();
        assignSizeEmbedded.setProduct(this.productRepository.findById(assignSizeEmbeddedRequest.getProduct()).orElseThrow(() -> new ResourceNotFoundException("Product Not Found")));
        assignSizeEmbedded.setSize(this.sizeRepository.findById(assignSizeEmbeddedRequest.getSize()).orElseThrow(() -> new ResourceNotFoundException("Size Not Found")));

        Optional<AssignSize> assignSizeOptional = assignSizeRepository.findById(assignSizeEmbedded);

        if (assignSizeOptional.isPresent()) {
            AssignSizeDtoResponse assignSizeDtoResponse = modelMapper.map(assignSizeOptional.get(), AssignSizeDtoResponse.class);
            return Optional.of(assignSizeDtoResponse);
        }

        return Optional.empty();
    }

    @Override
    public Page<AssignSizeDtoResponse> paginate(Pageable pageable) {
        Page<AssignSize> assignSizePage = assignSizeRepository.findAll(pageable);
        List<AssignSizeDtoResponse> assignSizeDtoResponses = assignSizePage.getContent().stream()
                .map(assignSize -> modelMapper.map(assignSize, AssignSizeDtoResponse.class))
                .collect(Collectors.toList());

        return new PageImpl<>(assignSizeDtoResponses, pageable, assignSizePage.getTotalElements());
    }
}