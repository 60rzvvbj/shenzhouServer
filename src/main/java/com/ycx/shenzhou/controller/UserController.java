package com.ycx.shenzhou.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ycx.shenzhou.pojo.User;
import com.ycx.shenzhou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(User user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<User> users =  userService.getAllUser();
        String s = mapper.writeValueAsString(users.get(0));
        return s;
    }
}
