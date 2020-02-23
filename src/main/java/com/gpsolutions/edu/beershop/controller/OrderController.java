package com.gpsolutions.edu.beershop.controller;

import com.gpsolutions.edu.beershop.dto.ClientDTO;
import com.gpsolutions.edu.beershop.entity.UserEntity;
import com.gpsolutions.edu.beershop.exception.NoSuchBeerException;
import com.gpsolutions.edu.beershop.exception.OrderNotFoundException;
import com.gpsolutions.edu.beershop.service.OrderService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Api
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/beer-shop-app/orders/beer/{beerId}")
    public void addBeerInOrder(@PathVariable final Long beerId,
                               @RequestParam final Integer amount,
                               final UserEntity user) throws NoSuchBeerException {
        orderService.addBeerToOrder(beerId,amount,user);
    }

    @PostMapping(value = "/beer-shop-app/orders/make-order")
    @ResponseStatus(HttpStatus.OK)
    public void makeOrder(final ClientDTO user) throws OrderNotFoundException {
        orderService.makeOrder(user);
    }

    @DeleteMapping(value = "/beer-shop-app/orders/beer/{beerId}")
    public void removeBeerFromOrder(@PathVariable final Long beerId, final  UserEntity user) {
        orderService.removeBeerFromOrder(beerId,user);
    }
}
