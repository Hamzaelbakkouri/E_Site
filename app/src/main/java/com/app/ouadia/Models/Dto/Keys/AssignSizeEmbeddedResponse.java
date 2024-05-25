package com.app.ouadia.Models.Dto.Keys;

import com.app.ouadia.Models.Dto.Product.ProductDtoRequest;
import com.app.ouadia.Models.Dto.Size.SizeDtoRequest;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class AssignSizeEmbeddedResponse implements Serializable {
    private ProductDtoRequest product;

    private SizeDtoRequest size;
}
