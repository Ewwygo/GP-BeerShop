package com.gpsolutions.edu.beershop.service;

import com.gpsolutions.edu.beershop.dto.ClientSignUpRequest;
import com.gpsolutions.edu.beershop.entity.UserEntity;
import com.gpsolutions.edu.beershop.exception.SuchClientAlreadyExistException;
import com.gpsolutions.edu.beershop.mapper.ClientSignUpRequestMapper;
import com.gpsolutions.edu.beershop.repository.UserRepository;
import com.gpsolutions.edu.beershop.security.Roles;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Log
@Service
@AllArgsConstructor
public class ClientService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final static String SALT = "my salt stonks";

    private final ClientSignUpRequestMapper clientSignUpRequestMapper;

    @Transactional
    public void signUp(final ClientSignUpRequest request) throws SuchClientAlreadyExistException {

        if (userRepository.findByLogin(request.getLogin()).isPresent()){
            throw new SuchClientAlreadyExistException("User with email=" + request.getLogin() + " already exists");
        }
        saveUser(request);
    }

    private void saveUser(final ClientSignUpRequest request) {
        final UserEntity userEntity = clientSignUpRequestMapper.sourceToDestination(request);
        userEntity.setRole(Roles.CLIENT);
        userEntity.setPassword(passwordEncoder.encode(request.getPassword() + SALT));
        userRepository.save(userEntity);
    }
}
