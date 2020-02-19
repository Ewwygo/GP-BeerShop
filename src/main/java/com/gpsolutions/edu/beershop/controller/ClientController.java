package com.gpsolutions.edu.beershop.controller;

import com.gpsolutions.edu.beershop.dto.ClientSignInRequest;
import com.gpsolutions.edu.beershop.dto.ClientSignUpRequest;
import com.gpsolutions.edu.beershop.dto.OrderDTO;
import com.gpsolutions.edu.beershop.service.ClientService;
import com.gpsolutions.edu.beershop.service.OrderService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/beer-shop-app/client")
@Log
public class ClientController {


    private final OrderService orderService;

    public ClientController(OrderService orderService) {
        this.orderService = orderService;
    }

}
