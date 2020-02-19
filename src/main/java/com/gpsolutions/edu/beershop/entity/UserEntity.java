package com.gpsolutions.edu.beershop.entity;

import com.gpsolutions.edu.beershop.security.Roles;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;
    private String password;


    private String fio;
    private String phoneNumber;
    private String info;

    @Column
    private Roles role;
}
