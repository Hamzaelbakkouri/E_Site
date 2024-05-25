package com.app.ouadia.Models.Dto.AssignSize;

import com.app.ouadia.Models.Dto.Keys.AssignSizeEmbeddedRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AssignSizeDtoRequest implements Serializable {
    @NotNull
    private AssignSizeEmbeddedRequest id;
    @NotNull(message = "Size Number Must ber Not Null")
    private Integer size_number;
    @NotNull(message = "Height Must ber Not Null")
    private double height;
    @NotNull(message = "Waist Must ber Not Null")
    private double waist;
    @NotNull(message = "Chest Must ber Not Null")
    private double chest;
    @NotNull(message = "Neck Must ber Not Null")
    private double neck;
    @NotNull(message = "arm Length Number Must ber Not Null")
    private double armLength;
    @NotNull(message = "hip Number Must ber Not Null")
    private double hip;
}
