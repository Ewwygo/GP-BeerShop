package com.gpsolutions.edu.beershop.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data

public class ClientDTO {

    private Long id;
    private String email;
    private String fio;
    private String phoneNumber;
    private String info;
}
