package com.app.ouadia.Models.Dto.Size;

import com.app.ouadia.Models.Dto.AssignSize.AssignSizeDtoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class SizeDtoResponse implements Serializable {
    private UUID id;
    private String Mark;
    private String description;
    private List<AssignSizeDtoResponse> assignSizes;
}
