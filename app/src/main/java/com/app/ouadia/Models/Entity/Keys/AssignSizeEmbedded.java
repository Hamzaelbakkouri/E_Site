package com.app.ouadia.Models.Entity.Keys;

import com.app.ouadia.Models.Entity.Product;
import com.app.ouadia.Models.Entity.Size;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class AssignSizeEmbedded implements Serializable {
    @ManyToOne
    private Product product;

    @ManyToOne
    private Size size;
}
