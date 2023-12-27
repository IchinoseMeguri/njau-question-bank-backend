package com.example.backend.service;

import com.example.backend.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<User> getUserList();

    public User getUserByName(String name);

    public User getUserById(String id);

    public int insertUser(User user);

    public int updatePassword(String id,String password);

    public int updateUser(User user);

    public int deleteUser(String id);
}
