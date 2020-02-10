package com.gpsolutions.edu.beershop.service;

import com.gpsolutions.edu.beershop.dto.BeerDTO;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Log
@Service
public class BeerCatalogService {

    public String catalog(){
        return "[\n" +
                "  {\n" +
                "      \"id\" : 1,\n" +
                "      \"title\" : \"Goose\",\n" +
                "      \"description\" : \"Strong\",\n" +
                "      \"alco\" : \"5.7%\",\n" +
                "      \"price\" : 5\n" +
                "    }\n" +
                "]";
    }

    public void addNewBeer(BeerDTO beer){
        log.info("Beer " + beer.getTitle() + " successfully added");
    }

    public void deleteBeer(Long beerId){
        log.info("Beer " + beerId + " successfully deleted");
    }
}
