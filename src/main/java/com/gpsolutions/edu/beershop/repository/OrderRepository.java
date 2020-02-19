package com.gpsolutions.edu.beershop.repository;

import com.gpsolutions.edu.beershop.entity.OrderCompleteStatus;
import com.gpsolutions.edu.beershop.entity.OrderEntity;
import com.gpsolutions.edu.beershop.entity.OrderProcessStatus;
import com.gpsolutions.edu.beershop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    Optional<OrderEntity> findByUserEntityAndOrderProcessStatus(UserEntity userEntity,
                                                                OrderProcessStatus orderProcessStatus);

    List<OrderEntity> findAllByOrderProcessStatusAndOrderCompleteStatus(OrderProcessStatus orderProcessStatus,
                                                                        OrderCompleteStatus orderCompleteStatus);

    Optional<OrderEntity> findById(Long id);
}
