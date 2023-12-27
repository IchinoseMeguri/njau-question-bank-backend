package com.example.backend.dao;

import com.example.backend.entity.Subject;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectDao {
    @Select("select * from subject")
    public List<Subject> getSubjectList();

    @Select("select subject_name from subject where subject_id=#{id}")
    public String getSubjectNameById(int id);
}
