package com.gpsolutions.edu.beershop.security;

import com.gpsolutions.edu.beershop.entity.AuthInfoEntity;
import com.gpsolutions.edu.beershop.repository.AuthInfoRepository;
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


    private final AuthInfoRepository authInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final Optional<AuthInfoEntity> authInfoEntity = authInfoRepository.findByLogin(username);
        if (authInfoEntity.isEmpty()) {
            throw new UsernameNotFoundException("User with email: " + username + " not found");
        } else {
            final SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + authInfoEntity.get().getUser().getRole().name());
            return new User(username,authInfoEntity.get().getPassword(), List.of(authority));
        }
    }

}
