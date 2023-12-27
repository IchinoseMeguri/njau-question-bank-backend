package com.example.backend.service;

import com.example.backend.entity.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubjectService {
    public List<Subject> getSubjectList();

    public String getSubjectNameById(int id);
}
