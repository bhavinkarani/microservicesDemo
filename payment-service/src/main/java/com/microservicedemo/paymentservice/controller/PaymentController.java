package com.microservicedemo.paymentservice.controller;

import com.microservicedemo.paymentservice.entity.PaymentCardInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @PostMapping("")
    public ResponseEntity makePayment(@RequestBody PaymentCardInfo paymentCartInfo){
        //this is dummy implementation of the payment service
        log.info("processing payment");
        return new ResponseEntity(HttpStatus.OK);

    }
}
