package com.app.ouadia.Models.Dto.Request;

import com.app.ouadia.Models.Dto.Person.Profile;
import com.app.ouadia.Models.Dto.Product.ProductDtoRequest;
import com.app.ouadia.Models.Dto.Size.SizeDtoRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RequestDtoResponse implements Serializable {
    private Integer id;
    private SizeDtoRequest size;
    private ProductDtoRequest product;
    private Profile person;
    private Float currentPrice;

}
