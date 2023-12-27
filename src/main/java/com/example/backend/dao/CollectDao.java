package com.example.backend.dao;

import com.example.backend.entity.Collect;
import com.example.backend.entity.QuestionDemo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectDao {
    @Select("select * from collect")
    public List<Collect> getCollectList();

    @Select("select * from collect where collect_user_id=#{id}")
    public List<Collect> getCollectListByUser(String id);

    @Select("select * from collect where collect_user_id=#{userId} and collect_question_id=#{questionId}")
    public Collect getCollectByUserAndQuestion(String userId,String questionId);

    @Select("select * from collect,subject,knowledge,question where " +
            "subject.subject_id=knowledge.subject_id and " +
            "knowledge.knowledge_id=question.knowledge_id and " +
            "question.question_id=collect.collect_question_id and " +
            "collect.collect_user_id=#{id} order by " +
            "subject.subject_id,knowledge.knowledge_id")
    public List<QuestionDemo> getCollectQuestionsDetailByUser(String id);

    @Select("select * from collect,subject,knowledge,question where " +
            "subject.subject_id=knowledge.subject_id and " +
            "knowledge.knowledge_id=question.knowledge_id and " +
            "question.question_id=collect.collect_question_id and " +
            "collect.collect_user_id=#{id} and " +
            "question.question_description like #{str} order by " +
            "subject.subject_id,knowledge.knowledge_id")
    public List<QuestionDemo> searchCollect(String id,String str);

    @Select("select count(*) from collect where collect_question_id=#{id}")
    public int getCollectUserNum(String id);

    @Insert("insert into collect values(#{collect_user_id},#{collect_question_id})")
    public int insertCollect(Collect collect);

    @Delete("delete from collect where collect_user_id=#{collect_user_id} " +
            "and collect_question_id=#{collect_question_id}")
    public int deleteCollect(Collect collect);
}
