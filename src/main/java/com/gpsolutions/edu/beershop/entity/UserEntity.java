package com.gpsolutions.edu.beershop.entity;

import com.gpsolutions.edu.beershop.security.Roles;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String fio;
    private String phoneNumber;
    private String info;

    private Roles role;
}
