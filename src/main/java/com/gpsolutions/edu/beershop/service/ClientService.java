package com.gpsolutions.edu.beershop.service;

import com.gpsolutions.edu.beershop.dto.ClientSignInRequest;
import com.gpsolutions.edu.beershop.dto.ClientSignUpRequest;
import com.gpsolutions.edu.beershop.exception.SuchClientAlreadyExistException;
import com.gpsolutions.edu.beershop.security.LoadClientDetailService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log
@Service
@AllArgsConstructor
public class ClientService {

    private final LoadClientDetailService loadClientDetailService;

    public void signUp(final ClientSignUpRequest request) throws SuchClientAlreadyExistException {
        try {
            if (loadClientDetailService.loadUserByUsername(request.getEmail()) != null) {
                throw new SuchClientAlreadyExistException("User with email=" + request.getEmail() + " already exists");
            }
        } catch (final UsernameNotFoundException e){
            loadClientDetailService.saveClient(request.getEmail(), request.getPassword());
        }
    }

    public String signIn(final ClientSignInRequest clientSignInRequest){
        return "";
    }


}
