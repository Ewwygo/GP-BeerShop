package com.gpsolutions.edu.beershop.service;

import com.gpsolutions.edu.beershop.dto.Beer;
import com.gpsolutions.edu.beershop.dto.ClientSignInRequest;
import com.gpsolutions.edu.beershop.dto.ClientSignUpRequest;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Log
@Service
public class ClientService {

    public String signUp(final ClientSignUpRequest clientSignUpRequest){
        return "{\"id\" : 1}";
    }

    public String signIn(final ClientSignInRequest clientSignInRequest){
        return "";
    }

    public void addToBucket(final Long beerId, final Long clientId) {
        log.info(String.format("Beer (%d) successfully added to client (%d) bucket", beerId, clientId));
    }

    public void removeFromBucket(final Long beerId, final Long clientId) {
        log.info(String.format("Beer (%d) successfully removed from client (%d) bucket", beerId, clientId));
    }

    public void makeOrder(final Long clientId){
        log.info(String.format("Order has been successfully created for client (%d)", clientId));
    }
}
