package com.gpsolutions.edu.beershop.dto;

import lombok.Data;

@Data
public class Beer {
    String title;
    String description;
    String alco;
    String density;
    double price;
    long id;
}
