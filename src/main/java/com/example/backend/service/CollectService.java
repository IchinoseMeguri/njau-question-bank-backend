package com.example.backend.service;

import com.example.backend.entity.Collect;
import com.example.backend.entity.QuestionDemo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CollectService {

    public List<Collect> getCollectList();

    public List<Collect> getCollectListByUser(String id);

    public Collect getCollectByUserAndQuestion(String userId,String questionId);

    public List<QuestionDemo> getCollectQuestionsDetailByUser(String id);

    public List<QuestionDemo> searchCollect(String id,String str);

    public int getCollectUserNum(String id);

    public int insertCollect(Collect collect);

    public int deleteCollect(Collect collect);
}
