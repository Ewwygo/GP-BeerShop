package com.gpsolutions.edu.beershop.security;

import com.gpsolutions.edu.beershop.entity.UserEntity;
import com.gpsolutions.edu.beershop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LoadClientDetailService implements UserDetailsService {


    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        final Optional<UserEntity> userEntity = userRepository.findByLogin(username);
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException("User with email: " + username + " not found");
        } else {
            final SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + userEntity.get().getRole().name());
            return new User(username,userEntity.get().getPassword(), List.of(authority));
        }
    }

}
