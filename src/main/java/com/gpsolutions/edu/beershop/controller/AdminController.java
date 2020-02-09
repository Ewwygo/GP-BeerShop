package com.gpsolutions.edu.beershop.controller;

import com.gpsolutions.edu.beershop.service.AdminService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/beer-shop-app/admin")
@Log
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(value = "/orders")
    @ResponseStatus(HttpStatus.OK)
    public String orders(){
        return adminService.orders();
    }

    @PostMapping(value = "/orders/{orderId}/complete-order")
    @ResponseStatus(HttpStatus.OK)
    public void completeOrder(@PathVariable final Long orderId){
        adminService.completeOrder(orderId);
    }
}
