package com.gpsolutions.edu.beershop.mapper;

import com.gpsolutions.edu.beershop.dto.BeerDTO;
import com.gpsolutions.edu.beershop.entity.BeerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BeerMapper {

    BeerEntity sourceToDestination(BeerDTO source);

    BeerDTO destinationToSource(BeerEntity destination);
}
