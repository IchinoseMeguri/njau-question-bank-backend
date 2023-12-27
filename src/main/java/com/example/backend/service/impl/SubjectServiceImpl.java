package com.example.backend.service.impl;

import com.example.backend.dao.SubjectDao;
import com.example.backend.entity.Subject;
import com.example.backend.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SubjectService")
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectDao subjectDao;
    public List<Subject> getSubjectList(){
        return subjectDao.getSubjectList();
    }

    public String getSubjectNameById(int id){
        return subjectDao.getSubjectNameById(id);
    }
}
