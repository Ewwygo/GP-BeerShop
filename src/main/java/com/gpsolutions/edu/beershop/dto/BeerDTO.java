package com.gpsolutions.edu.beershop.dto;

import lombok.Data;

@Data

public class BeerDTO {

    String title;
    String description;
    String alco;
    String density;
    double price;
    long id;
}
