package com.gpsolutions.edu.beershop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientSignInRequest {
    String email;
    String password;
}
