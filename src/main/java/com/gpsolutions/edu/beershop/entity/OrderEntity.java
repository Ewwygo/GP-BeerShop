package com.gpsolutions.edu.beershop.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<BeerEntity> beerList;

    private OrderProcessStatus orderProcessStatus;
    private OrderCompleteStatus orderCompleteStatus;

}
