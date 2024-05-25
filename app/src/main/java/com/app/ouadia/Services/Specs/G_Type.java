package com.app.ouadia.Services.Specs;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface G_Type<Request, Response, Id> {
    Optional<Response> save(@Valid Request request);

    Optional<Response> update(@Valid Request request, Id id);

    Boolean delete(Id id);

    Optional<Response> getById(Id id);

    Page<Response> paginate(Pageable pageable);
}
