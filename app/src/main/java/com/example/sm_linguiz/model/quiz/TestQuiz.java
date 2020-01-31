package com.example.sm_linguiz.model.quiz;

import com.example.sm_linguiz.database.Word;
import com.example.sm_linguiz.model.builder.TestQuizBuilder;
import com.example.sm_linguiz.model.proxy.Dictionary;
import com.example.sm_linguiz.model.question.Question;

import java.util.LinkedList;
import java.util.List;

public class TestQuiz extends Quiz {
    private int score;
    private boolean[] answerCorrectness;

    public TestQuiz(Dictionary dictionary, int questionCount) {
        super(dictionary);
        score = 0;

        TestQuizBuilder testQuizBuilder = new TestQuizBuilder(this.dictionary);
        testQuizBuilder.createQuestions(questionCount);
        this.questions = new LinkedList<>(testQuizBuilder.getQuestions());
        answerCorrectness = new boolean[questionCount];
    }

    public boolean[] getAnswerCorrectness() {
        return answerCorrectness;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore(int value) {
        score += value;
    }

    public void markCurrentQuestionCorrectness(boolean isCorrect) {
        answerCorrectness[this.getCurrentQuestionNumber()] = isCorrect;
    }

    public List<Word> getWords() {
        List<Word> words = new LinkedList<>();

        for (Question question : this.questions) {
            words.add(question.getCorrectWord());
        }

        return words;
    }
}
