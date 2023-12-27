package com.example.backend.service.impl;

import com.example.backend.dao.TestDao;
import com.example.backend.entity.Test;
import com.example.backend.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TestService")
public class TestServiceImpl implements TestService {

    @Autowired
    TestDao testDao;

    public List<Test> getTestList(){
        return testDao.getTestList();
    }

    public int getLastTestId(){
        return testDao.getLastTestId();
    }

    public List<Test> getTestListByUser(String id){
        return testDao.getTestListByUser(id);
    }

    public Test getTestById(int id){
        return testDao.getTestById(id);
    }

    public int insertTest(Test test){
        return testDao.insertTest(test);
    }

    public int updateTest(Test test){
        return testDao.updateTest(test);
    }

}
