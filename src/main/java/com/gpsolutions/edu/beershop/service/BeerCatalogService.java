package com.gpsolutions.edu.beershop.service;

import com.gpsolutions.edu.beershop.dto.BeerDTO;
import com.gpsolutions.edu.beershop.entity.BeerEntity;
import com.gpsolutions.edu.beershop.exception.SuchBeerAlreadyExistException;
import com.gpsolutions.edu.beershop.mapper.BeerMapper;
import com.gpsolutions.edu.beershop.repository.BeerRepository;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
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

    public List<BeerDTO> getCatalog(){
        return beerRepository.findAll().stream().map(beerMapper::destinationToSource).collect(Collectors.toList());
    }

    public void addNewBeer(BeerDTO beer) throws SuchBeerAlreadyExistException {

        beerRepository.save(beerMapper.sourceToDestination(beer));

        /*if (beerSet.contains(beer)){
            throw new SuchBeerAlreadyExistException("Beer " + beer.getTitle() + " with its parameters already exists");
        }else {
            beerSet.add(beer);
        }*/
    }

    public void deleteBeer(Long beerId){
        beerRepository.delete(beerRepository.findById(beerId).get());
    }
}
