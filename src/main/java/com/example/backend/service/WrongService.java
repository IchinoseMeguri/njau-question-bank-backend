package com.example.backend.service;

import com.example.backend.entity.QuestionDemo;
import com.example.backend.entity.Wrong;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WrongService {
    public List<Wrong> getWrongList();

    public List<Wrong> getWrongListByUser(String id);

    public Wrong getWrong(String userId,String questionId);

    public List<QuestionDemo> getWrongQuestionsDetailByUser(String id);

    public List<QuestionDemo> searchWrong(String id,String str);

    public int getUserWrongNum(String id);

    public int insertWrong(Wrong wrong);

    public int deleteWrong(Wrong wrong);
}
