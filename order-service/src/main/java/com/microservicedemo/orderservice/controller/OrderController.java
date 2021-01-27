package com.microservicedemo.orderservice.controller;

import com.microservicedemo.orderservice.entity.PurchaseOrder;
import com.microservicedemo.orderservice.service.OrderService;
import com.microservicedemo.orderservice.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping()
    public ResponseEntity<OrderVO> createOrder(@RequestBody OrderVO ordervo){
        log.info("creating order");
        return new ResponseEntity<>(orderService.save(ordervo), HttpStatus.CREATED);
    }
    @PutMapping()
    public ResponseEntity<OrderVO> updateOrder(@RequestBody OrderVO ordervo){
        log.info("updating order status"+ordervo);
        OrderVO updatedOrderVO = orderService.updateStatus(ordervo);
        if(updatedOrderVO==null || !updatedOrderVO.getOrderStatus().equalsIgnoreCase(ordervo.getOrderStatus())){
            return new ResponseEntity<>(updatedOrderVO, HttpStatus.PRECONDITION_FAILED );
        }
        return new ResponseEntity<>(updatedOrderVO, HttpStatus.OK);
    }
    @GetMapping(params = "id")
    public ResponseEntity<OrderVO> getOrder(@RequestParam("id")long id ){

        return new ResponseEntity<>(orderService.get(id), HttpStatus.OK);
    }

    @GetMapping()
    public List<PurchaseOrder> getAllOrders(){
        return orderService.get();
    }

}
