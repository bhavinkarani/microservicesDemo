package com.microservicedemo.cartservice.controller;

import com.microservicedemo.cartservice.entity.Cart;
import com.microservicedemo.cartservice.vo.CheckoutDetails;
import com.microservicedemo.cartservice.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping()
    public Cart addItemToCart(@RequestBody Cart cart){
        log.info("saving an item to cart");
        return cartService.save(cart);
    }

    @GetMapping("/{id}")
    public ResponseEntity getItemByUserId(@PathVariable("id") long userId){
        log.info("get all items from cart of user: {}", userId);
        List<Cart> cartItems = cartService.getCartById(userId);
        return new ResponseEntity(cartItems, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeAllItems(@PathVariable("id") long userId){
        log.info("remove all items from cart of user: {}", userId);
        cartService.delete(userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/{itemId}")
    public ResponseEntity removeItemsByItemId(@PathVariable("id") long userId,  @PathVariable("itemId") long itemId){
        log.info("remove item from cart of user: {} and itemId : {}", userId, itemId);
        cartService.deleteByItemId(userId, itemId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity checkoutCart(@RequestBody
                                       checkoutDetails){
        //validate the request
        log.info("inside checkout service for user : {}",checkoutDetails.getUserId());
        if(cartService.isValid(checkoutDetails)){
            boolean isTransactionSuccess = cartService.makeTransaction(checkoutDetails);
            if(isTransactionSuccess) {
                log.info("order is places successfully");
                return new ResponseEntity("Order placed successfully", HttpStatus.OK);
            }else{
                //this could be more detailed but for now just sending Expectation_failed back.
                return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
            }

        }
        return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
    }


}
