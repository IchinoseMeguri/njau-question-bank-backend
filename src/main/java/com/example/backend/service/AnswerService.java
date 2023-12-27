package com.example.backend.service;

import com.example.backend.entity.Answer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnswerService {
    public List<Answer> getAnswerList();

    public Answer getAnswerByUserAndQuestion(String userId,String questionId);

    public int getUserAnswerNum(String userId);

    public String getAnswerContentByUserAndQuestion(String userId,String questionId);

    public int insertAnswer(Answer answer);

    public int updateAnswer(String userId,String questionId,String answer,int score);
}
