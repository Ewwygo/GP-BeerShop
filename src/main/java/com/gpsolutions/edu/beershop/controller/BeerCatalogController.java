package com.gpsolutions.edu.beershop.controller;

import com.gpsolutions.edu.beershop.dto.Beer;
import com.gpsolutions.edu.beershop.service.BeerCatalogService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/beer-shop-app/catalog")
@Log
public class BeerCatalogController {

    private final BeerCatalogService beerCatalogService;

    public BeerCatalogController(BeerCatalogService beerCatalogService) {
        this.beerCatalogService = beerCatalogService;
    }

    @GetMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String catalog(){
        return beerCatalogService.catalog();
    }

    @PostMapping(value = "/add-new-beer", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void addNewBeer(@RequestBody final Beer beer){
        beerCatalogService.addNewBeer(beer);
    }

    @PostMapping(value = "/delete-beer/{beerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteBeer(@PathVariable final Long beerId){
        beerCatalogService.deleteBeer(beerId);
    }
}
