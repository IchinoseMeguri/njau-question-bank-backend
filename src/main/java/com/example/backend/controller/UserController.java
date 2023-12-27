package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.service.AnswerService;
import com.example.backend.service.UserService;
import com.example.backend.service.WrongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AnswerService answerService;

    @Autowired
    WrongService wrongService;

    @RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
    public User getUserInfo(String name){
        return userService.getUserByName(name);
    }

    @RequestMapping(value = "/getNum",method = RequestMethod.GET)
    public String[] getNum(String userId){
        String[] res=new String[2];
        int total=answerService.getUserAnswerNum(userId);
        int wrong=wrongService.getUserWrongNum(userId);
        if(total==0){
            res[0]="0";
            res[1]="0.00%";
        }
        else {
            res[0]=String.valueOf(total);
            double rate=1-(double)wrong/(double)total;
            res[1]=String.format("%.2f",rate*100)+"%";
        }
        return res;
    }

    @RequestMapping(value = "/changePwd",method = RequestMethod.GET)
    public int changePwd(String userId,String pwd,String newPwd){
        User user=userService.getUserById(userId);
        if(!user.getUser_password().equals(pwd)) return -1;
        user.setUser_password(newPwd);
        return userService.updatePassword(userId,newPwd);
    }
}
