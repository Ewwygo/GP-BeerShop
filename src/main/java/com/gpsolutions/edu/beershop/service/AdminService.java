package com.gpsolutions.edu.beershop.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@Log
public class AdminService {

    public String orders(){
        return "[\n" +
                "    {\n" +
                "      \"id\" : 1,\n" +
                "      \"clientName\" : \"Alex\",\n" +
                "      \"date\" : \"04.02.2020\",\n" +
                "      \"Beer\" : \"Goose x 2, Kozel x 3\",\n" +
                "      \"totalCost\" : 22\n" +
                "    }\n" +
                "]";
    }

    public void completeOrder(Long orderId){
        log.info("Order " + orderId + " successfully completed");
    }
}
