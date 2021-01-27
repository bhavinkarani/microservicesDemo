package com.microservicedemo.cartservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Items {
    public long itemId;
    public String itemStatus;
    public double itemAmount;
}
