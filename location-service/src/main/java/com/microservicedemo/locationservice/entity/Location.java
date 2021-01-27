package com.microservicedemo.locationservice.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    private long locationId;
    private String city;
    private String pinCode;
    private String Street;
    private int buildingNumber;
}
