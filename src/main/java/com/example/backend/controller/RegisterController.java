package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegisterController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public int Register(String name,String pwd){
        User exist=userService.getUserByName(name);
        if (exist!=null) {
            System.out.println("repeated name");
            return -1;
        }
        User user=new User();
        user.setUser_name(name);
        user.setUser_password(pwd);
        user.setUser_type("用户");
        return userService.insertUser(user);
    }
}
