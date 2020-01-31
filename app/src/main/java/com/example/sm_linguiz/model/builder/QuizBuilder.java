package com.example.sm_linguiz.model.builder;

import com.example.sm_linguiz.model.progress.Progress;
import com.example.sm_linguiz.model.proxy.Dictionary;
import com.example.sm_linguiz.model.question.Question;

import java.util.LinkedList;
import java.util.List;

abstract class QuizBuilder {
    LinkedList<Question> questions;
    protected Dictionary dictionary;
    Progress progress;

    QuizBuilder(Dictionary dictionary) {
        this.questions = new LinkedList<>();
        this.dictionary = dictionary;
        this.progress = Progress.getInstance();
    }

    public abstract void createQuestions(int questionCount);

    public abstract void createQuestion();

    public List<Question> getQuestions() {
        return this.questions;
    }
}
