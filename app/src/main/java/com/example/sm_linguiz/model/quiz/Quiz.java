package com.example.sm_linguiz.model.quiz;

import com.example.sm_linguiz.model.proxy.DictionaryProxy;
import com.example.sm_linguiz.model.question.Question;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public abstract class Quiz implements Serializable {
    protected LinkedList<Question> questions;
    protected DictionaryProxy dictionaryProxy;
    protected int currentQuestionNumber;

    public Quiz(DictionaryProxy dictionaryProxy) {
        this.dictionaryProxy = dictionaryProxy;
        currentQuestionNumber = 0;
    }

    public int getCurrentQuestionNumber() {
        return currentQuestionNumber;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public DictionaryProxy getDictionaryProxy() {
        return dictionaryProxy;
    }

    public Question getCurrentQuestion() {
        return questions.get(currentQuestionNumber);
    }

    public void nextQuestion() {
        currentQuestionNumber++;
    }
}
