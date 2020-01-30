package com.example.sm_linguiz.model.question;

import com.example.sm_linguiz.database.Word;

import java.io.Serializable;

public class OpenedQuestion extends Question implements Serializable {
    public OpenedQuestion(Word correctWord, boolean englishToPolish) {
        super(correctWord, englishToPolish);
    }
}
