package com.gpsolutions.edu.beershop.repository;

import com.gpsolutions.edu.beershop.entity.BeerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeerRepository extends JpaRepository<BeerEntity, Long> {

    Optional<BeerEntity> findByTitle(String title);

}
