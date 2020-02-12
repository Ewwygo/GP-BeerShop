package com.gpsolutions.edu.beershop.mapper;

import com.gpsolutions.edu.beershop.dto.ClientSignUpRequest;
import com.gpsolutions.edu.beershop.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientSignUpRequestMapper {

    UserEntity sourceToDestination(ClientSignUpRequest source);

    ClientSignUpRequest destinationToSource(UserEntity destination);
}
