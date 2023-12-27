package com.example.backend.service;

import com.example.backend.entity.Test;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestService {
    public List<Test> getTestList();

    public int getLastTestId();

    public List<Test> getTestListByUser(String id);

    public Test getTestById(int id);

    public int insertTest(Test test);

    public int updateTest(Test test);

}
