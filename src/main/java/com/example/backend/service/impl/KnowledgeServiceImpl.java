package com.example.backend.service.impl;

import com.example.backend.dao.KnowledgeDao;
import com.example.backend.entity.Knowledge;
import com.example.backend.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("KnowledgeService")
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    KnowledgeDao knowledgeDao;

    public List<Knowledge> getKnowledgeList(){
        return knowledgeDao.getKnowledgeList();
    }

    public List<String> getKnowledgeIdsBySubject(int id){
        return knowledgeDao.getKnowledgeIdsBySubject(id);
    }
    public List<Knowledge> getKnowledgeListBySubject(int id){
        return knowledgeDao.getKnowledgeListBySubject(id);
    }
    public Knowledge getKnowledgeById(String id){
        return knowledgeDao.getKnowledgeById(id);
    }
}
