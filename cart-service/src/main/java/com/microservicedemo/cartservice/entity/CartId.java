package com.microservicedemo.cartservice.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class CartId implements Serializable {
    public long userId;
    public long itemId;
}
