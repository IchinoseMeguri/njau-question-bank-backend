package com.example.backend.service.impl;

import com.example.backend.dao.QuestionDao;
import com.example.backend.entity.Question;
import com.example.backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("QuestionService")
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionDao questionDao;
    public List<Question> getQuestionList(){
        return questionDao.getQuestionList();
    }

    public List<Question> getQuestionListByKnowledge(int id){
        return questionDao.getQuestionListByKnowledge(id);
    }

    public Question getQuestionById(String id){
        return questionDao.getQuestionById(id);
    }

    public List<Question> searchQuestion(String str) {
        return questionDao.searchQuestion(str);
    }

    public int getQuestionNumByKnowledge(int id){
        return questionDao.getQuestionNumByKnowledge(id);
    }

    public int insertQuestion(Question question){
        return questionDao.insertQuestion(question);
    }

    public int deleteQuestion(String id){
        return questionDao.deleteQuestion(id);
    }

    public int updateQuestion(Question question){
        return questionDao.updateQuestion(question);
    }

    public List<Question> getQuestionsByKnowledgeIds(List<String> knowledgeIds){
        return questionDao.getQuestionsByKnowledgeIds(knowledgeIds);
    }

    public List<Question> getQuestionByKnowledgeIdsAndType(int type,List<String> knowledgeIds){
        return questionDao.getQuestionByKnowledgeIdsAndType(type, knowledgeIds);
    }

    public List<Question> getQuestionByKnowledgeIdsAndScore(int score,List<String> knowledgeIds){
        return questionDao.getQuestionByKnowledgeIdsAndScore(score, knowledgeIds);
    }
}
