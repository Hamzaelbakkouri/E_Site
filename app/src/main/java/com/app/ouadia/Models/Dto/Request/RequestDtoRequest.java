package com.app.ouadia.Models.Dto.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class RequestDtoRequest implements Serializable {
    private Integer id;
    @NotNull(message = "the size must be NotNull")
    private UUID size;
    @NotNull(message = "the product must be NotNull")
    private UUID product;
    @NotNull(message = "the person must be NotNull")
    private UUID person;
    @NotNull(message = "the currentPrice must be NotNull")
    @Min(value = 0,message = "price must be > 0")
    private Float currentPrice;
}
