package com.app.ouadia.Models.Dto.AssignSize;

import com.app.ouadia.Models.Dto.Keys.AssignSizeEmbeddedResponse;
import jakarta.persistence.EmbeddedId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AssignSizeDtoResponse implements Serializable {
    @EmbeddedId
    private AssignSizeEmbeddedResponse id;

    private Integer size_number;

    private double height;
    private double waist;
    private double chest;
    private double neck;
    private double armLength;
    private double hip;
}
