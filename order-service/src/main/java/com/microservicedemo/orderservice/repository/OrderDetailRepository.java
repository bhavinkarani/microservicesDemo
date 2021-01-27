package com.microservicedemo.orderservice.repository;

import com.microservicedemo.orderservice.entity.OrderDetails;
import com.microservicedemo.orderservice.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetails, OrderItem> {
    List<OrderDetails> findByOrderId(long orderId);
}
