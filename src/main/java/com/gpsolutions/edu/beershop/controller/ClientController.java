package com.gpsolutions.edu.beershop.controller;

import com.gpsolutions.edu.beershop.dto.ClientSignInRequest;
import com.gpsolutions.edu.beershop.dto.ClientSignUpRequest;
import com.gpsolutions.edu.beershop.service.ClientService;
import com.gpsolutions.edu.beershop.service.OrderService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/beer-shop-app/client")
@Log
public class ClientController {

    private final ClientService clientService;
    private final OrderService orderService;

    public ClientController(ClientService clientService, OrderService orderService) {
        this.clientService = clientService;
        this.orderService = orderService;
    }

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String signUp(@RequestBody final ClientSignUpRequest request){
        log.info("property " + request.getEmail());
        return clientService.signUp(request);
    }

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String signIn(@RequestBody final ClientSignInRequest request){
        return clientService.signIn(request);
    }

    @PostMapping(value = "/add-to-bucket/catalog/{beerId}")
    @ResponseStatus(HttpStatus.OK)
    public void addToBucket(@PathVariable final Long beerId,
                              @RequestHeader final Long clientId) {
        clientService.addToBucket(beerId,clientId);
    }

    @PostMapping(value = "/bucket/remove-from-bucket/{beerId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeFromBucket(@PathVariable final Long beerId,
                                 @RequestHeader final Long clientId) {
        clientService.removeFromBucket(beerId,clientId);
    }

    @PostMapping(value = "/make-order")
    @ResponseStatus(HttpStatus.OK)
    public void makeOrder(@RequestHeader final Long clientId){
        orderService.makeOrder(clientId);
    }
}
