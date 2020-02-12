package com.gpsolutions.edu.beershop.service;

import com.gpsolutions.edu.beershop.dto.ClientSignInRequest;
import com.gpsolutions.edu.beershop.dto.ClientSignUpRequest;
import com.gpsolutions.edu.beershop.entity.AuthInfoEntity;
import com.gpsolutions.edu.beershop.entity.UserEntity;
import com.gpsolutions.edu.beershop.exception.SuchClientAlreadyExistException;
import com.gpsolutions.edu.beershop.mapper.ClientSignUpRequestMapper;
import com.gpsolutions.edu.beershop.repository.AuthInfoRepository;
import com.gpsolutions.edu.beershop.repository.UserRepository;
import com.gpsolutions.edu.beershop.security.LoadClientDetailService;
import com.gpsolutions.edu.beershop.security.Roles;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Log
@Service
@AllArgsConstructor
public class ClientService {

    private final AuthInfoRepository authInfoRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ClientSignUpRequestMapper clientSignUpRequestMapper;

    @Transactional
    public void signUp(final ClientSignUpRequest request) throws SuchClientAlreadyExistException {

        if (authInfoRepository.findByLogin(request.getEmail()).isPresent()){
            throw new SuchClientAlreadyExistException("User with email=" + request.getEmail() + " already exists");
        }
        saveUser(request);
    }

    private void saveUser(final ClientSignUpRequest request) {
        final UserEntity userEntity = clientSignUpRequestMapper.sourceToDestination(request);
        userEntity.setRole(Roles.CLIENT);
        final UserEntity savedUser = userRepository.save(userEntity);
        saveAuthInfo(request, savedUser);
    }

    private void saveAuthInfo(final ClientSignUpRequest request, final UserEntity savedUser) {
        final AuthInfoEntity authInfoEntity = new AuthInfoEntity();
        authInfoEntity.setLogin(request.getEmail());
        authInfoEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        authInfoEntity.setUser(savedUser);
        authInfoRepository.save(authInfoEntity);
    }
}
