package com.app.ouadia.Services.Implementations;

import com.app.ouadia.Models.Dto.Request.RequestDtoRequest;
import com.app.ouadia.Models.Dto.Request.RequestDtoResponse;
import com.app.ouadia.Models.Entity.Person;
import com.app.ouadia.Models.Entity.Product;
import com.app.ouadia.Models.Entity.Request;
import com.app.ouadia.Models.Entity.Size;
import com.app.ouadia.Repository.PersonRepository;
import com.app.ouadia.Repository.ProductRepository;
import com.app.ouadia.Repository.RequestRepository;
import com.app.ouadia.Repository.SizeRepository;
import com.app.ouadia.Services.Specs.RequestService;
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
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final PersonRepository personRepository;
    private final SizeRepository sizeRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<RequestDtoResponse> save(RequestDtoRequest requestDtoRequest) {
        Optional<Person> personOptional = personRepository.findById(requestDtoRequest.getPerson());
        Optional<Product> productOptional = productRepository.findById(requestDtoRequest.getProduct());
        Optional<Size> sizeOptional = sizeRepository.findById(requestDtoRequest.getSize());

        if (personOptional.isPresent() && productOptional.isPresent() && sizeOptional.isPresent()) {
            Request request = new Request();
            request.setPerson(personOptional.get());
            request.setProduct(productOptional.get());
            request.setSize(sizeOptional.get());

            Request savedRequest = requestRepository.save(request);

            RequestDtoResponse requestDtoResponse = modelMapper.map(savedRequest, RequestDtoResponse.class);
            return Optional.of(requestDtoResponse);
        }

        return Optional.empty();
    }

    @Override
    public Optional<RequestDtoResponse> update(RequestDtoRequest requestDtoRequest, Integer requestId) {
        Optional<Request> requestOptional = requestRepository.findById(requestId);

        if (requestOptional.isPresent()) {
            Optional<Person> personOptional = personRepository.findById(requestDtoRequest.getPerson());
            Optional<Product> productOptional = productRepository.findById(requestDtoRequest.getProduct());
            Optional<Size> sizeOptional = sizeRepository.findById(requestDtoRequest.getSize());

            if (personOptional.isPresent() && productOptional.isPresent() && sizeOptional.isPresent()) {
                Request request = requestOptional.get();
                request.setPerson(personOptional.get());
                request.setProduct(productOptional.get());
                request.setSize(sizeOptional.get());

                Request updatedRequest = requestRepository.save(request);

                RequestDtoResponse requestDtoResponse = modelMapper.map(updatedRequest, RequestDtoResponse.class);
                return Optional.of(requestDtoResponse);
            }
        }

        return Optional.empty();
    }

    @Override
    public Boolean delete(Integer requestId) {
        Optional<Request> requestOptional = requestRepository.findById(requestId);

        if (requestOptional.isPresent()) {
            requestRepository.delete(requestOptional.get());
            return true;
        }

        return false;
    }

    @Override
    public Optional<RequestDtoResponse> getById(Integer requestId) {
        Optional<Request> requestOptional = requestRepository.findById(requestId);

        if (requestOptional.isPresent()) {
            RequestDtoResponse requestDtoResponse = modelMapper.map(requestOptional.get(), RequestDtoResponse.class);
            return Optional.of(requestDtoResponse);
        }

        return Optional.empty();
    }

    @Override
    public Page<RequestDtoResponse> paginate(Pageable pageable) {
        Page<Request> requestPage = requestRepository.findAll(pageable);

        List<RequestDtoResponse> requestDtoResponses = requestPage.getContent().stream()
                .map(request -> modelMapper.map(request, RequestDtoResponse.class))
                .collect(Collectors.toList());

        return new PageImpl<>(requestDtoResponses, pageable, requestPage.getTotalElements());
    }
}