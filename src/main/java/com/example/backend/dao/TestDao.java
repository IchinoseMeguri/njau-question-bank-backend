package com.example.backend.dao;

import com.example.backend.entity.Test;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDao {
    @Select("select * from test")
    public List<Test> getTestList();

    @Select("select * from test where test_user_id=#{id} order by test_time desc")
    public List<Test> getTestListByUser(String id);

    @Select("select test_id from test order by test_id desc limit 1")
    public int getLastTestId();

    @Select("select * from test where test_id=#{id}")
    public Test getTestById(int id);

    @Insert("insert into test(test_user_id,test_file,test_time) " +
            "values(#{test_user_id},#{test_file},#{test_time})")
    public int insertTest(Test test);

    @Update("update test set test_file=#{test_file},test_state=#{test_state} where test_id=#{test_id}")
    public int updateTest(Test test);
}
