package com.example.phisicscatalog.parserXml;

import java.util.List;

public class QuestionsInfoModel {
    private String question;
    private List<String> answerList;
    private int rightAnswer;

    public QuestionsInfoModel(String question, List<String> answerList, int rightAnswer) {
        this.question = question;
        this.answerList = answerList;
        this.rightAnswer = rightAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswerList() {
        return answerList;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }
}
