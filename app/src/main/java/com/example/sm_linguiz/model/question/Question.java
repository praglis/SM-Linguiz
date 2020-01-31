package com.example.sm_linguiz.model.question;

import com.example.sm_linguiz.database.Word;

import java.io.Serializable;

public abstract class Question implements Serializable {
    Word correctAnswer;
    boolean englishToPolish;
    String userAnswer;

    public Question(Word correctWord, boolean englishToPolish) {
        this.correctAnswer = correctWord;
        this.englishToPolish = englishToPolish;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getQuestionText() {
        return !englishToPolish ? this.correctAnswer.getEnglishWord() : this.correctAnswer.getPolishWord();
    }

    public String getCorrectAnswer() {
        return correctAnswer.getAppropriateWord(englishToPolish);
    }

    public Word getCorrectWord() {
        return correctAnswer;
    }

    public boolean isEnglishToPolish() {
        return englishToPolish;
    }
}

