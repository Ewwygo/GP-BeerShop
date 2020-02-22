package com.gpsolutions.edu.beershop.service;

import com.gpsolutions.edu.beershop.dto.BeerDTO;
import com.gpsolutions.edu.beershop.entity.BeerEntity;
import com.gpsolutions.edu.beershop.exception.NoSuchBeerException;
import com.gpsolutions.edu.beershop.exception.SuchBeerAlreadyExistException;
import com.gpsolutions.edu.beershop.mapper.BeerMapper;
import com.gpsolutions.edu.beershop.repository.BeerRepository;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log
@Service
@Data
public class BeerCatalogService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @PostConstruct
    public void init() {
        beerRepository.save(beerMapper.sourceToDestination(BeerDTO.builder()
                .id(1L)
                .title("Goose")
                .description("Strong")
                .alco("5.7%")
                .density("10%")
                .price(5)
                .build()));
    }

    public List<BeerDTO> getCatalog() {
        final List<BeerEntity> beerEntities = beerRepository.findAll();
        final List<BeerDTO> berrDtos = beerEntities.stream().map(beerMapper::destinationToSource).collect(Collectors.toList());
        return berrDtos;
    }

    public void addNewBeer(final BeerDTO beer) throws SuchBeerAlreadyExistException {
        final String title = beer.getTitle();
        final Optional<BeerEntity> beerEntity = beerRepository.findByTitle(title);
        if (beerEntity.isEmpty()){
            beerRepository.save(beerMapper.sourceToDestination(beer));
        } else {
            throw new SuchBeerAlreadyExistException("Beer " + title + " already exists");
        }
    }

    public void deleteBeer(final Long beerId) throws NoSuchBeerException {
        Optional<BeerEntity> beerEntity = beerRepository.findById(beerId);
        if (beerEntity.isPresent()) {
            beerRepository.delete(beerEntity.get());
        } else {
            throw new NoSuchBeerException("Beer with id=" + beerId + " not found");
        }
    }
}
