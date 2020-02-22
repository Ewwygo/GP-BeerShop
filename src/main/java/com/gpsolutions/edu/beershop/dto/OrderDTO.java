package com.gpsolutions.edu.beershop.dto;

import com.gpsolutions.edu.beershop.entity.OrderCompleteStatus;
import com.gpsolutions.edu.beershop.entity.OrderProcessStatus;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class OrderDTO {

    private Long id;

    private Long clientId;

    private Map<BeerDTO,Integer> beerMap;
    private double cost;
}
