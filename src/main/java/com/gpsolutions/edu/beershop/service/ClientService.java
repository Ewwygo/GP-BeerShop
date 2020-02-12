package com.gpsolutions.edu.beershop.service;

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


}
