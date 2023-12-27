package com.example.backend.entity;

import java.util.ArrayList;
import java.util.List;

public class SubjectDemo {
    private int subject_id;
    private String subject_name;
    private int question_num;
    private List<KnowledgeDemo> knowledge;

    public SubjectDemo(int subject_id, String subject_name) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.question_num = 0;
        this.knowledge = new ArrayList<>();
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public int getQuestion_num() {
        return question_num;
    }

    public void setQuestion_num(int question_num) {
        this.question_num = question_num;
    }

    public List<KnowledgeDemo> getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(List<KnowledgeDemo> knowledge) {
        this.knowledge = knowledge;
    }

    public void addKnowledgeToList(KnowledgeDemo knowledgeDemo) {
        this.knowledge.add(knowledgeDemo);
        this.question_num += knowledgeDemo.getQuestion_num();
    }
}
