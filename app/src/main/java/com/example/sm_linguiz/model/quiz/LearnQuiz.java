package com.example.sm_linguiz.model.quiz;

import com.example.sm_linguiz.model.builder.LearnQuizBuilder;
import com.example.sm_linguiz.model.progress.Progress;
import com.example.sm_linguiz.model.proxy.Dictionary;

import java.io.Serializable;
import java.util.LinkedList;

public class LearnQuiz extends Quiz implements Serializable {
    public LearnQuiz(Dictionary dictionary, int questionsCount) {
        super(dictionary);

        LearnQuizBuilder learnQuizBuilder = new LearnQuizBuilder(this.dictionary, Progress.getInstance());
        learnQuizBuilder.createQuestions(questionsCount);
        this.questions = new LinkedList<>(learnQuizBuilder.getQuestions());
    }
}
