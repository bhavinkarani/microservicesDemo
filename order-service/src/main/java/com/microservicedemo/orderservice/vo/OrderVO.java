package com.microservicedemo.orderservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderVO {
    public long orderId;
    public long userId;
    public double amount;
    public String orderStatus;
    public String deliveryAddress;
    private List<Items> itemsList;
}
