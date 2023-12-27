package com.example.backend.dao;

import com.example.backend.entity.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao {
    @Select("select * from question")
    public List<Question> getQuestionList();

    @Select("select * from question where knowledge_id=#{id}")
    public List<Question> getQuestionListByKnowledge(int id);

    @Select("select * from question where question_id=#{id}")
    public Question getQuestionById(String id);

    @Select("select * from question where question_description like #{str}")
    public List<Question> searchQuestion(String str);

    @Select("select count(*) from question where knowledge_id=#{id}")
    public int getQuestionNumByKnowledge(int id);

    @Insert("insert into question(knowledge_id,question_type,question_from,question_score," +
            "question_level,question_description,question_answer,question_answer_detail) " +
            "values(#{knowledge_id},#{question_type},#{question_from},#{question_score}," +
            "#{question_level},#{question_description},#{question_answer},#{question_answer_detail})")
    public int insertQuestion(Question question);

    @Delete("delete from question where question_id=#{id}")
    public int deleteQuestion(String id);

    @Update("update question set knowledge_id=#{knowledge_id},question_type=#{question_type}," +
            "question_from=#{question_from},question_score=#{question_score},question_level=#{question_level}" +
            "question_description=#{question_description},question_answer=#{question_answer}," +
            "question_answer_detail=#{question_answer_detail} where question_id=#{question_id}")
    public int updateQuestion(Question question);

    @Select("<script>" +
            "select * from question where knowledge_id in " +
            "<foreach item='item' index='index' collection='knowledgeIds' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    public List<Question> getQuestionsByKnowledgeIds(List<String> knowledgeIds);

    @Select("<script>" +
            "select * from question where question_type=#{type} and knowledge_id in " +
            "<foreach item='item' index='index' collection='knowledgeIds' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    public List<Question> getQuestionByKnowledgeIdsAndType(int type,List<String> knowledgeIds);

    @Select("<script>" +
            "select * from question where question_score=#{score} and knowledge_id in " +
            "<foreach item='item' index='index' collection='knowledgeIds' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    public List<Question> getQuestionByKnowledgeIdsAndScore(int score,List<String> knowledgeIds);
}
