package com.gpsolutions.edu.beershop.service;

import com.gpsolutions.edu.beershop.dto.ClientDTO;
import com.gpsolutions.edu.beershop.dto.OrderDTO;
import com.gpsolutions.edu.beershop.entity.*;
import com.gpsolutions.edu.beershop.exception.NoSuchBeerException;
import com.gpsolutions.edu.beershop.exception.OrderNotFoundException;
import com.gpsolutions.edu.beershop.mapper.ClientMapper;
import com.gpsolutions.edu.beershop.mapper.OrderMapper;
import com.gpsolutions.edu.beershop.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final BeerCatalogService beerCatalogService;
    private final ClientMapper clientMapper;
    private final OrderMapper orderMapper;


    @Transactional
    public void makeOrder(final ClientDTO user) throws OrderNotFoundException {
        final UserEntity userEntity = clientMapper.sourceToDestination(user);
        final Optional<OrderEntity> orderEntity = orderRepository.findByUserEntityAndOrderProcessStatus(userEntity, OrderProcessStatus.IN_PROCCESS);
        if (orderEntity.isPresent()){
            orderEntity.get().setOrderProcessStatus(OrderProcessStatus.READY);
        } else {
            throw new OrderNotFoundException("Order for user " + userEntity.getLogin() + " not found");
        }
    }

    @Transactional
    public void addBeerToOrder(final Long beerId,final Integer amount, final UserEntity user) throws NoSuchBeerException {
        final Optional<BeerEntity> beerEntity = beerCatalogService.getBeerRepository().findById(beerId);

        if (beerEntity.isPresent()){
            final Optional<OrderEntity> orderEntity = orderRepository.findByUserEntityAndOrderProcessStatus(user, OrderProcessStatus.IN_PROCCESS);
            if (orderEntity.isPresent()){
                orderEntity.get().getBeerList().add(beerEntity.get());
            } else {
                final OrderEntity order = new OrderEntity();
                order.setUserEntity(user);
                order.setOrderProcessStatus(OrderProcessStatus.IN_PROCCESS);
                order.setBeerList(new ArrayList<>());
                order.setOrderCompleteStatus(OrderCompleteStatus.NOT_COMPLETE);
                for (int i = 0; i <amount; i++){
                    order.getBeerList().add(beerEntity.get());
                }
                orderRepository.save(order);
            }
        } else {
            throw new NoSuchBeerException("Beer not found");
        }
    }

    public List<OrderDTO> orders(){
        List<OrderEntity> orderEntityList = orderRepository.findAllByOrderProcessStatusAndOrderCompleteStatus
                (OrderProcessStatus.READY, OrderCompleteStatus.NOT_COMPLETE);
        List<OrderDTO> orderDTOList = orderEntityList.stream().map(orderMapper::entityToDto).collect(Collectors.toList());
        return orderDTOList;
    }

    @Transactional
    public void completeOrder(final Long orderId) throws OrderNotFoundException {
        Optional<OrderEntity> orderEntity = orderRepository.findById(orderId);
        if (orderEntity.isPresent()){
            orderEntity.get().setOrderCompleteStatus(OrderCompleteStatus.COMPLETE);
        } else {
            throw new OrderNotFoundException("Order with id=" + orderId + " not found");
        }
    }

    @Transactional
    public void removeBeerFromOrder(final Long beerId, final UserEntity userEntity){
        final Optional<OrderEntity> orderEntity = orderRepository.findByUserEntityAndOrderProcessStatus(
                userEntity, OrderProcessStatus.IN_PROCCESS);
        final Optional<BeerEntity> beerEntity = beerCatalogService.getBeerRepository().findById(beerId);

        if (orderEntity.isPresent()){
            if (beerEntity.isPresent()){
                final List<BeerEntity> beerList = orderEntity.get().getBeerList();
                while (beerList.contains(beerEntity.get())){
                    beerList.remove(beerEntity.get());
                }
            }
        }
    }
}
