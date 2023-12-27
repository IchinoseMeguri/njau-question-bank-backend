package com.example.backend.service.impl;

import com.example.backend.dao.UserDao;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    public List<User> getUserList(){
        return userDao.getUserList();
    }

    public User getUserByName(String name){
        return userDao.getUserByName(name);
    }

    public User getUserById(String id){
        return userDao.getUserById(id);
    }

    public int insertUser(User user){
        return userDao.insertUser(user);
    }

    public int updatePassword(String id,String password){
        return userDao.updatePassword(id, password);
    }

    public int updateUser(User user){
        return userDao.updateUser(user);
    }

    public int deleteUser(String id){
        return userDao.deleteUser(id);
    }
}
