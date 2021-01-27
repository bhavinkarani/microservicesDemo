package com.microservicedemo.cloudGateway;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("cartServiceFallback")
    public String cartServiceFallback(){
        return "cart service is taking longer than expected. Please try after sometime";
    }

    @GetMapping("locationServiceFallback")
    public String locationServiceFallback(){
        return "location service is taking longer than expected. Please try after sometime";
    }

    @GetMapping("orderServiceFallback")
    public String orderServiceFallback(){
        return "order service is taking longer than expected. Please try after sometime";
    }

    @GetMapping("paymentServiceFallback")
    public String paymentServiceFallback(){
        return "payment service is taking longer than expected. Please try after sometime";
    }

    @GetMapping("userServiceFallback")
    public String userServiceFallback(){
        return "user service is taking longer than expected. Please try after sometime";
    }
}
