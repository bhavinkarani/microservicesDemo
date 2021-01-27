package com.microservicedemo.orderservice.service;

import com.microservicedemo.orderservice.entity.PurchaseOrder;
import com.microservicedemo.orderservice.entity.OrderDetails;
import com.microservicedemo.orderservice.repository.OrderDetailRepository;
import com.microservicedemo.orderservice.repository.OrderRepository;
import com.microservicedemo.orderservice.vo.ItemStatusEnum;
import com.microservicedemo.orderservice.vo.Items;
import com.microservicedemo.orderservice.vo.OrderStatusEnum;
import com.microservicedemo.orderservice.vo.OrderVO;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Transactional
    public OrderVO save(OrderVO ordervo) {
        PurchaseOrder purchaseOrder = new PurchaseOrder(ordervo.getOrderId(), ordervo.getUserId(),
                ordervo.getAmount(), OrderStatusEnum.ORDERED.name(), ordervo.getDeliveryAddress());
        purchaseOrder = orderRepository.save(purchaseOrder);
        ordervo.setOrderStatus(OrderStatusEnum.ORDERED.name());

        for(Items item: ordervo.getItemsList()){
            OrderDetails i = new OrderDetails(purchaseOrder.getOrderId(), item.itemId, ItemStatusEnum.ORDERED.name(), item.getItemAmount());
            orderDetailRepository.save(i);
            item.setItemStatus(ItemStatusEnum.ORDERED.name());
        }
        ordervo.setOrderId(purchaseOrder.getOrderId());
        return ordervo;
    }

    public OrderVO updateStatus(OrderVO ordervo) {
        Optional<PurchaseOrder> optionalPurchaseOrder = orderRepository.findById(ordervo.getOrderId());
        if(!optionalPurchaseOrder.isPresent()) {
            return null;
        }
        PurchaseOrder purchaseOrder = optionalPurchaseOrder.get();
        purchaseOrder.setOrderStatus(ordervo.getOrderStatus());
        purchaseOrder = orderRepository.save(purchaseOrder);
        ordervo.setOrderStatus(purchaseOrder.getOrderStatus());
        return ordervo;
    }

    public OrderVO get(long id) {
        OrderVO orderVO = new OrderVO();
        Optional<PurchaseOrder> optionalOrder = orderRepository.findById(id);
        if(!optionalOrder.isPresent()){
            return null;
        }
        PurchaseOrder order = optionalOrder.get();
        List<OrderDetails> orderDetails = orderDetailRepository.findByOrderId(order.getOrderId());
        orderVO.setOrderStatus(order.getOrderStatus());
        orderVO.setOrderId(id);
        orderVO.setAmount(order.getAmount());
        orderVO.setDeliveryAddress(order.getDeliveryAddress());
        orderVO.setUserId(order.getUserId());
        orderVO.setItemsList(orderDetails.stream().map(i -> new Items(i.getItemId(), i.getItemStatus(), i.getItemAmount())).collect(Collectors.toList()));
        return orderVO;
    }

    public List<PurchaseOrder> get() {
        return orderRepository.findAll();
    }
}
