package com.gpsolutions.edu.beershop.mapper;

import com.gpsolutions.edu.beershop.dto.OrderDTO;
import com.gpsolutions.edu.beershop.entity.OrderEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderMapper {

    private final ClientMapper clientMapper;
    private final BeerMapper beerMapper;

    public OrderDTO entityToDto(OrderEntity orderEntity){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orderEntity.getId());
        orderDTO.setOrderCompleteStatus(orderEntity.getOrderCompleteStatus());
        orderDTO.setOrderProcessStatus(orderEntity.getOrderProcessStatus());
        orderDTO.setClientDTO(clientMapper.destinationToSource(orderEntity.getUserEntity()));
        orderDTO.setBeerDTOList(orderEntity.getBeerList().stream().map(beerMapper::destinationToSource).collect(Collectors.toList()));
        return orderDTO;
    }

    public OrderEntity dtoToEntity(OrderDTO orderDTO){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderDTO.getId());
        orderEntity.setOrderCompleteStatus(orderDTO.getOrderCompleteStatus());
        orderEntity.setOrderProcessStatus(orderDTO.getOrderProcessStatus());
        orderEntity.setUserEntity(clientMapper.sourceToDestination(orderDTO.getClientDTO()));
        orderEntity.setBeerList(orderDTO.getBeerDTOList().stream().map(beerMapper::sourceToDestination).collect(Collectors.toList()));
        return orderEntity;
    }
}
