package com.example.backend.dao;

import com.example.backend.entity.Knowledge;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KnowledgeDao {
    @Select("select * from knowledge")
    public List<Knowledge> getKnowledgeList();

    @Select("select knowledge_id from knowledge where subject_id=#{id}")
    public List<String> getKnowledgeIdsBySubject(int id);

    @Select("select * from knowledge where subject_id=#{id}")
    public List<Knowledge> getKnowledgeListBySubject(int id);

    @Select("select * from knowledge where knowledge_id=#{id}")
    public Knowledge getKnowledgeById(String id);
}
