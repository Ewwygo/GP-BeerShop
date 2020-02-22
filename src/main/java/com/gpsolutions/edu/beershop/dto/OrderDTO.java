package com.gpsolutions.edu.beershop.dto;

import com.gpsolutions.edu.beershop.entity.OrderCompleteStatus;
import com.gpsolutions.edu.beershop.entity.OrderProcessStatus;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {

    private Long id;

    private ClientDTO clientDTO;

    private List<BeerDTO> beerDTOList;
    private OrderProcessStatus orderProcessStatus;
    private OrderCompleteStatus orderCompleteStatus;
}
