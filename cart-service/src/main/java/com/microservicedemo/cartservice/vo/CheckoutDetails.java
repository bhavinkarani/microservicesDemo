package com.microservicedemo.cartservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutDetails {
    private long userId;
    private PaymentCardInfo paymentCardInfo;
    private double amount;
    private boolean pickupOrder;
    private long locationId;
    List<Items> itemsList;
}
