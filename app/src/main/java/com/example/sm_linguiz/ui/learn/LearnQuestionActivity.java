package com.example.sm_linguiz.ui.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sm_linguiz.QuestionActivity;
import com.example.sm_linguiz.R;
import com.example.sm_linguiz.model.question.ClosedQuestion;
import com.example.sm_linguiz.model.quiz.Quiz;

import static com.example.sm_linguiz.LevelSelect.IS_ANSWER_CORRECT;
import static com.example.sm_linguiz.LevelSelect.QUIZ;
import static com.example.sm_linguiz.LevelSelect.SELECTED_LEVEL;

public class LearnQuestionActivity extends QuestionActivity {
    TextView questionText;
    TextView responseText;
    Button[] answers;

    Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_question);

        String level = getIntent().getStringExtra(SELECTED_LEVEL);
        this.quiz = (Quiz) getIntent().getSerializableExtra(QUIZ);

        questionText = findViewById(R.id.learn_question_text);
        responseText = findViewById(R.id.learn_question_response);


        questionText.setText(quiz.getCurrentQuestion().getQuestionText());


        answers = new Button[]{
                findViewById(R.id.learn_answer_a),
                findViewById(R.id.learn_answer_b),
                findViewById(R.id.learn_answer_c),
                findViewById(R.id.learn_answer_d)
        };

        String[] answerArray = ((ClosedQuestion) this.quiz.getCurrentQuestion()).toAnswerArray();
        int i = 0;

        for (Button answerButton : answers) {
            answerButton.setText(answerArray[i]);
            i++;

            answerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent replyIntent = new Intent();

                    if (isAnswerCorrect(view, quiz)) {
                        replyIntent.putExtra(IS_ANSWER_CORRECT, true);
                        setResult(RESULT_OK, replyIntent);

                        responseText.setText(R.string.correct_answer);

                    } else {
                        replyIntent.putExtra(IS_ANSWER_CORRECT, false);
                        setResult(RESULT_OK, replyIntent);

                        responseText.setText(R.string.incorrect_answer);
                    }

                    finish();
                }
            });
        }
    }
}
