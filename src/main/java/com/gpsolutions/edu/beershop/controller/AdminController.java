package com.gpsolutions.edu.beershop.controller;

import com.gpsolutions.edu.beershop.service.AdminService;
import com.gpsolutions.edu.beershop.service.OrderService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/beer-shop-app/admin")
@Log
public class AdminController {

    private final AdminService adminService;
    private final OrderService orderService;

    public AdminController(AdminService adminService, OrderService orderService) {
        this.adminService = adminService;
        this.orderService = orderService;
    }

    @GetMapping(value = "/orders")
    @ResponseStatus(HttpStatus.OK)
    public String orders(final Authentication authentication){
        return orderService.orders();
    }

    @PostMapping(value = "/orders/{orderId}/complete-order")
    @ResponseStatus(HttpStatus.OK)
    public void completeOrder(@PathVariable final Long orderId){
        orderService.completeOrder(orderId);
    }
}
