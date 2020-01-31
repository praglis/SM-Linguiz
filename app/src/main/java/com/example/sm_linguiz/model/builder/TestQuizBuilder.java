package com.example.sm_linguiz.model.builder;

import com.example.sm_linguiz.model.proxy.Dictionary;
import com.example.sm_linguiz.model.question.ClosedQuestion;
import com.example.sm_linguiz.model.question.Question;

import java.util.Random;

public class TestQuizBuilder extends QuizBuilder {

    public TestQuizBuilder(Dictionary dictionary) {
        super(dictionary);
    }

    @Override
    public void createQuestions(int questionCount) {
        for (int i = 0; i < questionCount; i++) {
            createQuestion();
        }
    }

    @Override
    public void createQuestion() {
        Question question;
        Random random = new Random();
        boolean englishOrPolish = random.nextBoolean();
        question = new ClosedQuestion(dictionary.getRandomWord(), dictionary.getRandomWords(3), englishOrPolish);
        questions.add(question);
    }

}
