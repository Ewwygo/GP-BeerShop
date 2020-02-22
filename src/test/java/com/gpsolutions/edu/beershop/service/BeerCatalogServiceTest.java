package com.gpsolutions.edu.beershop.service;

import com.gpsolutions.edu.beershop.dto.BeerDTO;
import com.gpsolutions.edu.beershop.entity.BeerEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;


public class BeerCatalogServiceTest extends AbstractServiceTest {

    @Autowired
    protected BeerCatalogService beerCatalogService;


    @Test
    public void testGetCatalog(){
        final List<BeerDTO> beerEntity = List.of(BeerDTO.builder()
                .id(1L)
                .title("Goose")
                .description("Strong")
                .alco("5.7%")
                .density("10%")
                .price(5)
                .build());
        final List<BeerDTO> catalog = beerCatalogService.getCatalog();
        assertEquals(catalog,beerEntity);
    }

    @Test void createNewBeerIsOk(){

    }
}
