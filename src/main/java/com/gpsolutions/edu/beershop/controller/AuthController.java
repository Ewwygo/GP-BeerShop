package com.gpsolutions.edu.beershop.controller;

import com.gpsolutions.edu.beershop.dto.ClientSignInRequest;
import com.gpsolutions.edu.beershop.dto.ClientSignUpRequest;
import com.gpsolutions.edu.beershop.dto.UserSignInResponse;
import com.gpsolutions.edu.beershop.exception.SuchClientAlreadyExistException;
import com.gpsolutions.edu.beershop.security.JwtUtil;
import com.gpsolutions.edu.beershop.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beer-shop-app/client")
@AllArgsConstructor
@Log
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final ClientService clientService;

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserSignInResponse signUp(@RequestBody final ClientSignUpRequest request) throws SuchClientAlreadyExistException {
        clientService.signUp(request);
        return signIn(new ClientSignInRequest(request.getEmail(),request.getPassword()));
    }

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserSignInResponse signIn(@RequestBody final ClientSignInRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

        return new UserSignInResponse(
                jwtUtil.generateToken(
                        new User(request.getEmail(),request.getPassword(), List.of(new SimpleGrantedAuthority("CLIENT")))));
    }
}
