package com.microservicedemo.cartservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CartId.class)
public class Cart implements Serializable {
    @Id
    public long userId;
    @Id
    public long itemId;
    public long productId;
    public double itemAmount;
}
