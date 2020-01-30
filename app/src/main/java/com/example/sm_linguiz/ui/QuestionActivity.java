package com.example.sm_linguiz.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sm_linguiz.model.quiz.Quiz;

import static com.example.sm_linguiz.ui.LevelSelect.QUIZ;

public abstract class QuestionActivity extends AppCompatActivity {
    protected TextView questionText;
    protected Button[] answers;

    protected Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("QuestionActivity", "onCreate");

        this.quiz = (Quiz) getIntent().getSerializableExtra(QUIZ);
    }

    protected boolean isAnswerCorrect(View button, Quiz quiz) {
        Log.d("QuestionActivity", "isAnswerCorrect");

        return ((Button) button).getText().equals(quiz.getCurrentQuestion().getCorrectAnswer());
    }
}
