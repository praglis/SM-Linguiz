package com.example.sm_linguiz.model.question;

import com.example.sm_linguiz.database.Word;

import java.util.Random;

public class ClosedQuestion extends Question {
    private Word[] incorrectAnswers;
    private int answerCount;

    public ClosedQuestion(Word correctAnswer, Word[] incorrectAnswers, boolean englishToPolish) {
        super(correctAnswer, englishToPolish);
        this.incorrectAnswers = incorrectAnswers;
        answerCount = this.incorrectAnswers.length + 1;
    }

    public int getAnswerCount(){
        return answerCount;
    }

    public String[] toAnswerArray() {
        Random random = new Random();
        int shift = 0, correctAnswerPlacement = random.nextInt(answerCount);
        String[] answerArray = new String[answerCount];

        for (int i = 0; i < answerCount; i++) {
            if(i != correctAnswerPlacement) answerArray[i] = incorrectAnswers[i + shift].getAppropriateWord(englishToPolish);
            else {
                answerArray[i] = correctAnswer.getAppropriateWord(englishToPolish);
                shift = -1;
            }
        }

        return answerArray;
    }
}
