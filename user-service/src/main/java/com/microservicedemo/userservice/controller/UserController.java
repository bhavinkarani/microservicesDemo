package com.microservicedemo.userservice.controller;


import com.microservicedemo.userservice.entity.User;
import com.microservicedemo.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(params = "id")
    public User getUserById(@RequestParam("id")long id ){
        log.info("getting user by useerID : {}", id );
        Optional<User> optionalUser = userService.getUserByID(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        return null;
    }

}
