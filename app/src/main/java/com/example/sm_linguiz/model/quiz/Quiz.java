package com.example.sm_linguiz.model.quiz;

import com.example.sm_linguiz.database.Word;
import com.example.sm_linguiz.model.proxy.DictionaryProxy;
import com.example.sm_linguiz.model.question.Question;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public abstract class Quiz implements Serializable {
    protected LinkedList<Question> questions;
    protected LinkedList<Word> words;
    protected int currentQuestionNumber;

    public Quiz(List<Word> words) {
        this.words = new LinkedList<Word>(words);
        currentQuestionNumber = 0;
    }

    public int getCurrentQuestionNumber() {
        return currentQuestionNumber;
    }

    public LinkedList<Question> getQuestions() {
        return questions;
    }

    public LinkedList<Word> getDictionaryProxy() {
        return words;
    }

    public Question getCurrentQuestion() {
        return questions.get(currentQuestionNumber);
    }

    public void nextQuestion() {
        currentQuestionNumber++;
    }
}
