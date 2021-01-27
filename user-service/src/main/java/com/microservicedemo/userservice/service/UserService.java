package com.microservicedemo.userservice.service;

import com.microservicedemo.userservice.entity.User;
import com.microservicedemo.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public Optional<User> getUserByID(long id) {
        return userRepository.findById(id);
    }
}
