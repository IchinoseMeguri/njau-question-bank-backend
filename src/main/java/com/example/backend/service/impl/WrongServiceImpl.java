package com.example.backend.service.impl;

import com.example.backend.dao.WrongDao;
import com.example.backend.entity.QuestionDemo;
import com.example.backend.entity.Wrong;
import com.example.backend.service.WrongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("WrongService")
public class WrongServiceImpl implements WrongService {

    @Autowired
    WrongDao wrongDao;
    public List<Wrong> getWrongList(){
        return wrongDao.getWrongList();
    }

    public List<Wrong> getWrongListByUser(String id){
        return wrongDao.getWrongListByUser(id);
    }

    public Wrong getWrong(String userId,String questionId){
        return wrongDao.getWrong(userId,questionId);
    }


    public List<QuestionDemo> getWrongQuestionsDetailByUser(String id) {
        return wrongDao.getWrongQuestionsDetailByUser(id);
    }

    public List<QuestionDemo> searchWrong(String id,String str){
        return wrongDao.searchWrong(id, str);
    }

    public int getUserWrongNum(String id){
        return wrongDao.getUserWrongNum(id);
    }

    public int insertWrong(Wrong wrong){
        return wrongDao.insertWrong(wrong);
    }


    public int deleteWrong(Wrong wrong){
        return wrongDao.deleteWrong(wrong);
    }
}
