package com.gpsolutions.edu.beershop.controller;

import com.gpsolutions.edu.beershop.dto.ClientDTO;
import com.gpsolutions.edu.beershop.dto.OrderDTO;
import com.gpsolutions.edu.beershop.entity.UserEntity;
import com.gpsolutions.edu.beershop.exception.NoSuchBeerException;
import com.gpsolutions.edu.beershop.exception.OrderNotFoundException;
import com.gpsolutions.edu.beershop.service.OrderService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Api
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/beer-shop-app/orders/{beerId}")
    public void addBeerInOrder(@PathVariable final Long beerId,final UserEntity user) throws NoSuchBeerException {
        orderService.addBeerToOrder(beerId,user);
    }

    @PostMapping(value = "/beer-shop-app/orders/make-order")
    @ResponseStatus(HttpStatus.OK)
    public void makeOrder(final ClientDTO user) throws OrderNotFoundException {
        orderService.makeOrder(user);
    }
}
