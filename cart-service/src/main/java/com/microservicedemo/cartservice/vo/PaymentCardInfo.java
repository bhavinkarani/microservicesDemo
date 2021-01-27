package com.microservicedemo.cartservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCardInfo {
    // this is dummy object for now but should contain all the nessesary info for payment
    String cardNumber;
    int cardSequence;
    // etc ..
}
