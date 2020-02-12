package com.gpsolutions.edu.beershop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BeerDTO {

    private Long id;
    private String title;
    private String description;
    private String alco;
    private String density;
    private double price;
}
