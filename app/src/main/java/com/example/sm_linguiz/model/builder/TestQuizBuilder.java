package com.example.sm_linguiz.model.builder;

import com.example.sm_linguiz.model.proxy.DictionaryProxy;
import com.example.sm_linguiz.model.question.ClosedQuestion;
import com.example.sm_linguiz.model.question.OpenedQuestion;
import com.example.sm_linguiz.model.question.Question;

import java.util.Random;

public class TestQuizBuilder extends QuizBuilder {

    public TestQuizBuilder(DictionaryProxy dictionaryProxy) {
        super(dictionaryProxy);
    }

    @Override
    public void createQuestions() {
        int questionCount = 10;
        for (int i = 0; i < questionCount; i++) {
            createQuestion();
        }
    }

    @Override
    public void createQuestion() {
        Question question;
        Random random = new Random();
        boolean englishOrPolish = random.nextBoolean();
        boolean openedOrClosed = random.nextBoolean();
        if (openedOrClosed) {
            question = new OpenedQuestion(dictionaryProxy.getRandomWord(), englishOrPolish);
        } else {
            question = new ClosedQuestion(dictionaryProxy.getRandomWord(), dictionaryProxy.getRandomWords(3), englishOrPolish);
        }
        questions.add(question);
    }

}
