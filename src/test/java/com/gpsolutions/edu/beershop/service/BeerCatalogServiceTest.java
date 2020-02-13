package com.gpsolutions.edu.beershop.service;

import com.gpsolutions.edu.beershop.dto.BeerDTO;
import com.gpsolutions.edu.beershop.entity.BeerEntity;
import com.gpsolutions.edu.beershop.mapper.BeerMapper;
import com.gpsolutions.edu.beershop.repository.BeerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BeerCatalogServiceTest {

    @Mock
    protected BeerRepository mBeerRepository;

    @Mock
    protected BeerMapper mBeerMapper;

    @InjectMocks
    protected BeerCatalogService beerCatalogService = new BeerCatalogService(mBeerRepository,mBeerMapper);

    @Test
    public void testGetCatalog(){
        List<BeerDTO> beerDTO = List.of(BeerDTO.builder()
                .id(1L)
                .title("Goose")
                .description("Strong")
                .alco("5.7%")
                .density("10%")
                .price(5)
                .build());
        doReturn(beerDTO).when(mBeerRepository).findAll();
        assertEquals(beerCatalogService.getCatalog(),beerDTO);
    }
}
