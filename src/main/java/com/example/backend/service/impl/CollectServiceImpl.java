package com.example.backend.service.impl;

import com.example.backend.dao.CollectDao;
import com.example.backend.entity.Collect;
import com.example.backend.entity.QuestionDemo;
import com.example.backend.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CollectService")
public class CollectServiceImpl implements CollectService {

    @Autowired
    CollectDao collectDao;

    public List<Collect> getCollectList(){
        return collectDao.getCollectList();
    }

    public List<Collect> getCollectListByUser(String id){
        return collectDao.getCollectListByUser(id);
    }

    public Collect getCollectByUserAndQuestion(String userId,String questionId){
        return collectDao.getCollectByUserAndQuestion(userId, questionId);
    }

    public List<QuestionDemo> getCollectQuestionsDetailByUser(String id){
        return collectDao.getCollectQuestionsDetailByUser(id);
    }

    public List<QuestionDemo> searchCollect(String id,String str){
        return collectDao.searchCollect(id, str);
    }

    public int getCollectUserNum(String id) {
        return collectDao.getCollectUserNum(id);
    }

    public int insertCollect(Collect collect){
        return collectDao.insertCollect(collect);
    }

    public int deleteCollect(Collect collect) {
        return collectDao.deleteCollect(collect);
    }
}
