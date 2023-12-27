package com.example.backend.dao;

import com.example.backend.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    @Select("select * from user")
    public List<User> getUserList();

    @Select("select * from user where user_name=#{name}")
    public User getUserByName(String name);

    @Select("select * from user where user_id=#{id}")
    public User getUserById(String id);

    @Insert("insert into user(user_name,user_type,user_password) " +
            "values(#{user_name},#{user_type},#{user_password})")
    public int insertUser(User user);

    @Update("update user set user_password=#{password} where user_id=#{id}")
    public int updatePassword(String id,String password);

    @Update("update user set user_name=#{user_name},user_type=#{user_type} where user_id=#{user_id}")
    public int updateUser(User user);

    @Delete("delete from user where user_id=#{id}")
    public int deleteUser(String id);
}
