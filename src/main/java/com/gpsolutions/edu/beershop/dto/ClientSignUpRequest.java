package com.gpsolutions.edu.beershop.dto;

import lombok.Data;

@Data
public class ClientSignUpRequest {
    private String login;
    private String password;
    private String fio;
    private String phoneNumber;
    private String info;
}
