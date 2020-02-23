package com.gpsolutions.edu.beershop.controller;

import com.gpsolutions.edu.beershop.dto.BeerDTO;
import com.gpsolutions.edu.beershop.exception.NoSuchBeerException;
import com.gpsolutions.edu.beershop.exception.SuchBeerAlreadyExistException;
import com.gpsolutions.edu.beershop.service.BeerCatalogService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beer-shop-app/catalog")
@Log
public class BeerCatalogController {

    private final BeerCatalogService beerCatalogService;

    public BeerCatalogController(final BeerCatalogService beerCatalogService) {
        this.beerCatalogService = beerCatalogService;
    }

    @GetMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    public List<BeerDTO> catalog(){
        return beerCatalogService.getCatalog();
    }


    @PostMapping(value = "/add-new-beer", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void addNewBeer(@RequestBody final BeerDTO beer) throws SuchBeerAlreadyExistException {
        beerCatalogService.addNewBeer(beer);
    }

    @PostMapping(value = "/delete-beer/{beerId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBeer(@PathVariable final Long beerId) throws NoSuchBeerException {
        beerCatalogService.deleteBeer(beerId);
    }
}
