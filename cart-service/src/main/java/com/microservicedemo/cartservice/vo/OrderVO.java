package com.microservicedemo.cartservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {
    public long orderId;
    public long userId;
    public double amount;
    public String orderStatus;
    public String deliveryAddress;
    private List<Items> itemsList;
}
