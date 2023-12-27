package com.example.backend.service;

import com.example.backend.entity.Knowledge;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface KnowledgeService {
    public List<Knowledge> getKnowledgeList();

    public List<String> getKnowledgeIdsBySubject(int id);

    public List<Knowledge> getKnowledgeListBySubject(int id);

    public Knowledge getKnowledgeById(String id);
}
