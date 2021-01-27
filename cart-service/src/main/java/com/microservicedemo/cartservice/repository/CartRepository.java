package com.microservicedemo.cartservice.repository;

import com.microservicedemo.cartservice.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository  extends JpaRepository<Cart, Long> {

    void deleteByUserId(long userId);

    void deleteByUserIdAndItemId(long userId, long itemId);

    List<Cart> findAllByUserId(long userId);
}
