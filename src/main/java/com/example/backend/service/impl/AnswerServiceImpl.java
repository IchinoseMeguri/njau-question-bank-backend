package com.example.backend.service.impl;

import com.example.backend.dao.AnswerDao;
import com.example.backend.entity.Answer;
import com.example.backend.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AnswerService")
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerDao answerDao;

    public List<Answer> getAnswerList(){
        return answerDao.getAnswerList();
    }

    public Answer getAnswerByUserAndQuestion(String userId,String questionId){
        return answerDao.getAnswerByUserAndQuestion(userId, questionId);
    }

    public int getUserAnswerNum(String userId){
        return answerDao.getUserAnswerNum(userId);
    }

    public String getAnswerContentByUserAndQuestion(String userId,String questionId){
        return answerDao.getAnswerContentByUserAndQuestion(userId, questionId);
    }

    public int insertAnswer(Answer answer){
        return answerDao.insertAnswer(answer);
    }

    public int updateAnswer(String userId,String questionId,String answer,int score){
        return answerDao.updateAnswer(userId, questionId, answer,score);
    }
}
