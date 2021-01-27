package com.microservicedemo.userservice.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String deliveryAddress;
}
