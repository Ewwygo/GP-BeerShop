package com.gpsolutions.edu.beershop.controller;

import com.gpsolutions.edu.beershop.dto.OrderDTO;
import com.gpsolutions.edu.beershop.exception.OrderNotFoundException;
import com.gpsolutions.edu.beershop.service.AdminService;
import com.gpsolutions.edu.beershop.service.OrderService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beer-shop-app/admin")
@Log
public class AdminController {

    private final OrderService orderService;

    public AdminController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> orders(){
        return orderService.orders();
    }

    @PostMapping(value = "/orders/{orderId}/complete-order")
    @ResponseStatus(HttpStatus.OK)
    public void completeOrder(@PathVariable final Long orderId) throws OrderNotFoundException {
        orderService.completeOrder(orderId);
    }
}
