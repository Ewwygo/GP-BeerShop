package com.gpsolutions.edu.beershop.mapper;

import com.gpsolutions.edu.beershop.dto.BeerDTO;
import com.gpsolutions.edu.beershop.dto.OrderDTO;
import com.gpsolutions.edu.beershop.entity.BeerEntity;
import com.gpsolutions.edu.beershop.entity.OrderEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderMapper {

    private final ClientMapper clientMapper;
    private final BeerMapper beerMapper;

    public OrderDTO entityToDto(final OrderEntity orderEntity){
        final OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orderEntity.getId());
        orderDTO.setClientId(orderEntity.getUserEntity().getId());
        orderDTO.setBeerMap(mapBeer(orderEntity));
        final double cost = orderEntity.getBeerList().stream().mapToDouble(BeerEntity::getPrice).sum();
        orderDTO.setCost(cost);
        return orderDTO;
    }

    public OrderEntity dtoToEntity(final OrderDTO orderDTO){
        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderDTO.getId());
        return orderEntity;
    }
    
    private Map<BeerDTO,Integer> mapBeer(final OrderEntity entity){
        final Map<BeerDTO,Integer> map = new HashMap<>();
        for (final BeerEntity beerEntity: entity.getBeerList()
             ) {
            final BeerDTO key = beerMapper.destinationToSource(beerEntity);
            if (map.containsKey(key)){
                map.put(key, map.get(key)+1);
            } else {
                map.put(key,1);
            }
        }
        return map;
    }
}
