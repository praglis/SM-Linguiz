package com.example.sm_linguiz.model.question;

import com.example.sm_linguiz.database.Word;

public class OpenedQuestion extends Question {
    public OpenedQuestion(Word correctWord, boolean englishToPolish) {
        super(correctWord, englishToPolish);
    }
}
