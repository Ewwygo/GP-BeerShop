package com.gpsolutions.edu.beershop.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoadClientDetailService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final Map<String,String> inMemoryClients =new HashMap<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final String password = inMemoryClients.get(username);
        if (password == null) {
            throw new UsernameNotFoundException("User with email: " + username + " not found");
        } else {
            return new User(username, password, Collections.emptyList());
        }
    }

    public void saveClient(final String username, final String password){
        inMemoryClients.put(username,passwordEncoder.encode(password));
    }
}
