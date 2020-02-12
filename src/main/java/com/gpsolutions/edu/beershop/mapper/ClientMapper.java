package com.gpsolutions.edu.beershop.mapper;

import com.gpsolutions.edu.beershop.dto.ClientDTO;
import com.gpsolutions.edu.beershop.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    UserEntity sourceToDestination(ClientDTO source);

    ClientDTO destinationToSource(UserEntity destination);
}
