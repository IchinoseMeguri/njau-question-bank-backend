package com.example.backend.service;

import com.example.backend.entity.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    public List<Question> getQuestionList();

    public List<Question> getQuestionListByKnowledge(int id);

    public Question getQuestionById(String id);

    public List<Question> searchQuestion(String str);

    public int getQuestionNumByKnowledge(int id);

    public int insertQuestion(Question question);

    public int deleteQuestion(String id);

    public int updateQuestion(Question question);

    public List<Question> getQuestionsByKnowledgeIds(List<String> knowledgeIds);

    public List<Question> getQuestionByKnowledgeIdsAndType(int type,List<String> knowledgeIds);

    public List<Question> getQuestionByKnowledgeIdsAndScore(int score,List<String> knowledgeIds);
}
