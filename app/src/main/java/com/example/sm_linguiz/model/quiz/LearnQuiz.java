package com.example.sm_linguiz.model.quiz;

import com.example.sm_linguiz.model.builder.LearnQuizBuilder;
import com.example.sm_linguiz.model.progress.Progress;
import com.example.sm_linguiz.model.proxy.DictionaryProxy;

import java.io.Serializable;

public class LearnQuiz extends Quiz implements Serializable {
    public LearnQuiz(DictionaryProxy dictionaryProxy, int questionsCount) {
        super(dictionaryProxy);

        LearnQuizBuilder learnQuizBuilder = new LearnQuizBuilder(this.dictionaryProxy, Progress.getInstance());
        learnQuizBuilder.createQuestions(questionsCount);
        this.questions = learnQuizBuilder.getQuestions();
    }
}
