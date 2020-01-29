package com.example.sm_linguiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sm_linguiz.model.quiz.Quiz;

public abstract class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected boolean isAnswerCorrect(View button, Quiz quiz) {
        return ((Button) button).getText().equals(quiz.getCurrentQuestion().getCorrectAnswer());
    }
}
