package com.app.ouadia.Models.Entity;

import com.app.ouadia.Models.Enums.RequestStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public final class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Size size;
    @ManyToOne
    private Product product;
    private RequestStatus requestStatus = RequestStatus.PENDING;
    @ManyToOne
    private Person person;

    private Float currentPrice;
}
