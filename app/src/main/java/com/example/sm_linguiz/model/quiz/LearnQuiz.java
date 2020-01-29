package com.example.sm_linguiz.model.quiz;

import com.example.sm_linguiz.database.Word;
import com.example.sm_linguiz.model.builder.LearnQuizBuilder;
import com.example.sm_linguiz.model.progress.Progress;
import com.example.sm_linguiz.model.proxy.DictionaryProxy;

import java.util.List;

public class LearnQuiz extends Quiz {
    public LearnQuiz(List<Word> words, int questionsCount) {
        super(words);

        LearnQuizBuilder learnQuizBuilder = new LearnQuizBuilder(this.words, Progress.getInstance());
        learnQuizBuilder.createQuestions();
        this.questions = learnQuizBuilder.getQuestions();
    }
}
