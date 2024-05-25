package com.app.ouadia.Models.Entity;

import com.app.ouadia.Models.Enums.PriceCategory;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Builder
@NoArgsConstructor
public final class Product {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    private String name;
    private String description;
    private double promotion;
    private PriceCategory priceType = PriceCategory.DH;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    @OneToMany(mappedBy = "id.product")
    private List<AssignSize> assignSizes;
}
