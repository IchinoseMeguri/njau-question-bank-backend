package com.example.backend.entity;

public class Question {
    private String question_id;
    private String knowledge_id;
    private int question_type;
    private String question_from;
    private int question_score;
    private int question_level;
    private String question_description;
    private String question_answer;
    private String question_answer_detail;

    public int getQuestion_level() {
        return question_level;
    }

    public void setQuestion_level(int question_level) {
        this.question_level = question_level;
    }

    public int getQuestion_score() {
        return question_score;
    }

    public void setQuestion_score(int question_score) {
        this.question_score = question_score;
    }

    public String getQuestion_answer_detail() {
        return question_answer_detail;
    }

    public void setQuestion_answer_detail(String question_answer_detail) {
        this.question_answer_detail = question_answer_detail;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getKnowledge_id() {
        return knowledge_id;
    }

    public void setKnowledge_id(String knowledge_id) {
        this.knowledge_id = knowledge_id;
    }

    public int getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(int question_type) {
        this.question_type = question_type;
    }

    public String getQuestion_from() {
        return question_from;
    }

    public void setQuestion_from(String question_from) {
        this.question_from = question_from;
    }

    public String getQuestion_description() {
        return question_description;
    }

    public void setQuestion_description(String question_description) {
        this.question_description = question_description;
    }

    public String getQuestion_answer() {
        return question_answer;
    }

    public void setQuestion_answer(String question_answer) {
        this.question_answer = question_answer;
    }
}
