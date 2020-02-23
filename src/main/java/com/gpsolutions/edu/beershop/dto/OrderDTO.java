package com.gpsolutions.edu.beershop.dto;

import lombok.Data;

import java.util.Map;

@Data
public class OrderDTO {

    private Long id;

    private Long clientId;

    private Map<BeerDTO,Integer> beerMap;
    private double cost;
}
