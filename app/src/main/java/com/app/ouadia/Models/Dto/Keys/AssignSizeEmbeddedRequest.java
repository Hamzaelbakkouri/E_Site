package com.app.ouadia.Models.Dto.Keys;

import com.app.ouadia.Models.Dto.Product.ProductDtoRequest;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class AssignSizeEmbeddedRequest implements Serializable {
    private UUID product;
    private UUID size;
}
