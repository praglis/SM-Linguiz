package com.example.sm_linguiz.model.quiz;

import com.example.sm_linguiz.model.proxy.Dictionary;
import com.example.sm_linguiz.model.question.Question;

import java.io.Serializable;
import java.util.List;

public abstract class Quiz implements Serializable {
    List<Question> questions;
    protected Dictionary dictionary;
    private int currentQuestionNumber;

    Quiz(Dictionary dictionary) {
        this.dictionary = dictionary;
        currentQuestionNumber = 0;
    }

    public void setCurrentQuestionUserAnswer(String currentQuestionUserAnswer) {
        questions.get(currentQuestionNumber).setUserAnswer(currentQuestionUserAnswer);
    }

    public String getUserAnswer(int questionNumber){
        return questions.get(questionNumber).getUserAnswer();
    }

    public int getCurrentQuestionNumber() {
        return currentQuestionNumber;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public Question getCurrentQuestion() {
        return questions.get(currentQuestionNumber);
    }

    public void nextQuestion() {
        currentQuestionNumber++;
    }
}
