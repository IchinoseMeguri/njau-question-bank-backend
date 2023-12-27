package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public int login(String name, String pwd){
        User user=userService.getUserByName(name);
        if(user==null){
            System.out.println("login failed");
            return 0;
        }
        if (pwd.equals(user.getUser_password())) {
            System.out.println("login success");
            return 1;
        }
        else {
            System.out.println("login failed");
            return 0;
        }
    }
}
