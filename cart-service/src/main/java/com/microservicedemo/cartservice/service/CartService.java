package com.microservicedemo.cartservice.service;

import com.microservicedemo.cartservice.entity.Cart;
import com.microservicedemo.cartservice.vo.*;
import com.microservicedemo.cartservice.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class CartService {

    public static final String PAYMENT_SUCCESS = "PAYMENT_SUCCESS";
    @Autowired
    private CartRepository cartRepository;


    @Autowired
    private RestTemplate restTemplate;


    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Transactional
    public void delete(long userId) {
        cartRepository.deleteByUserId(userId);
    }

    @Transactional
    public void deleteByItemId(long userId, long itemId) {
        cartRepository.deleteByUserIdAndItemId(userId, itemId);
    }

    public List<Cart> getCartById(long userId) {
        return cartRepository.findAllByUserId(userId);
    }

    public boolean isValid(CheckoutDetails checkoutDetails) {
        //check if card number is valid
        // check if amount of the checkout matches the total amount of the cart
        // check if the pickup location is valid
        // .. more validations
        //return false if anything
        return true;
    }


    public boolean makeTransaction(CheckoutDetails checkoutDetails) {

        log.info("calling multiple microservices to achive the checkout flow");
        OrderVO orderVO = createOrderVO(checkoutDetails);
        ResponseEntity<OrderVO> orderVOResponseEntity = restTemplate.postForEntity("http://ORDER-SERVICE/order/", orderVO, OrderVO.class);
        log.info("create order received"+ orderVOResponseEntity.getStatusCode());
        if(orderVOResponseEntity.getStatusCode()!=HttpStatus.CREATED){
            return false;
        }
        orderVO = orderVOResponseEntity.getBody();
        //make real transaction
        ResponseEntity<PaymentCardInfo> paymentCardInfoResponseEntity = restTemplate.postForEntity("http://PAYMENT-SERVICE/payment/", checkoutDetails.getPaymentCardInfo(), PaymentCardInfo.class);
        log.info("payment order received"+ paymentCardInfoResponseEntity.getStatusCode());
        if(HttpStatus.OK != paymentCardInfoResponseEntity.getStatusCode()){
            //mark order as payment failed
            return false;
        }

        //mark order as payment success
        orderVO.setOrderStatus(PAYMENT_SUCCESS);
        restTemplate.put("http://ORDER-SERVICE/order", orderVO);
        log.info("update order received");
        return true;
    }

    private OrderVO createOrderVO(CheckoutDetails checkoutDetails) {
        OrderVO orderVO = new OrderVO();
        orderVO.setAmount(checkoutDetails.getAmount());
        orderVO.setItemsList(checkoutDetails.getItemsList());
        orderVO.setUserId(checkoutDetails.getUserId());
        setDeliveryAddress(orderVO, checkoutDetails);
        return orderVO;
    }

    private void setDeliveryAddress(OrderVO orderVO, CheckoutDetails checkoutDetails) {
        String deliveryAddress = "test address1 ";
        if(checkoutDetails.isPickupOrder()){
            log.info("call location service to get location pickup point");
            Location location = restTemplate.getForEntity("http://LOCATION-SERVICE/location/?id="+ checkoutDetails.getLocationId(), Location.class).getBody();
            deliveryAddress = location.getBuildingNumber() + " "+location.getStreet() +", " + location.getCity() + ", "+location.getPinCode();
        }else{
            log.info("call user service to get location of the user");
            User user = restTemplate.getForEntity("http://localhost:9005/user/?id="+ checkoutDetails.getUserId(), User.class).getBody();
            deliveryAddress = user.getDeliveryAddress();
        }
        orderVO.setDeliveryAddress(deliveryAddress);

    }
}
