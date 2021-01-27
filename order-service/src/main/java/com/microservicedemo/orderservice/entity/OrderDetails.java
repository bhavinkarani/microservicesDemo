package com.microservicedemo.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(OrderItem.class)
public class OrderDetails implements Serializable {
    @Id
    public long orderId;
    @Id
    public long itemId;
    public String itemStatus;
    public double itemAmount;
}
