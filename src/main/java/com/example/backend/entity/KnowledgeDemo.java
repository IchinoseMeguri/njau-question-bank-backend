package com.example.backend.entity;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeDemo {
    private String knowledge_id;
    private String knowledge_name;
    private int question_num;
    private List<Question> questions;

    public KnowledgeDemo(String knowledge_id, String knowledge_name, int question_num) {
        this.knowledge_id = knowledge_id;
        this.knowledge_name = knowledge_name;
        this.question_num = question_num;
        this.questions=new ArrayList<>();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getKnowledge_id() {
        return knowledge_id;
    }

    public void setKnowledge_id(String knowledge_id) {
        this.knowledge_id = knowledge_id;
    }

    public String getKnowledge_name() {
        return knowledge_name;
    }

    public void setKnowledge_name(String knowledge_name) {
        this.knowledge_name = knowledge_name;
    }

    public int getQuestion_num() {
        return question_num;
    }

    public void setQuestion_num(int question_num) {
        this.question_num = question_num;
    }

    public void addQuestionToList(Question q){
        this.questions.add(q);
        this.question_num++;
    }
}
