package com.microservicedemo.cartservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private long locationId;
    private String city;
    private String pinCode;
    private String Street;
    private int buildingNumber;
}
