package com.gpsolutions.edu.beershop.dto;

import lombok.Data;

@Data
public class ClientSignInRequest {
    String email;
    String password;
}
