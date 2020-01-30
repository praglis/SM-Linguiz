package com.example.sm_linguiz.ui.learn;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sm_linguiz.R;
import com.example.sm_linguiz.model.question.ClosedQuestion;
import com.example.sm_linguiz.ui.MainActivity;
import com.example.sm_linguiz.ui.QuestionActivity;

public class LearnQuestionActivity extends QuestionActivity {
    TextView responseText;
    Button nextButton;

    boolean hasUserAnswered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LearnQuestionActivity", "onCreate");

        setContentView(R.layout.activity_learn_question);

        hasUserAnswered = false;

        questionText = findViewById(R.id.learn_question_text);
        responseText = findViewById(R.id.learn_question_response);
        nextButton = findViewById(R.id.learn_next);

        answers = new Button[]{
                findViewById(R.id.learn_answer_a),
                findViewById(R.id.learn_answer_b),
                findViewById(R.id.learn_answer_c),
                findViewById(R.id.learn_answer_d)
        };

        setQuestion();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                quiz.nextQuestion();

                if (quiz.getCurrentQuestionNumber() >= quiz.getQuestions().size() - 1) {
                    Intent intent = new Intent(LearnQuestionActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                setQuestion();
            }
        });
    }

    private void setQuestion() {
        questionText.setText(quiz.getCurrentQuestion().getQuestionText());
        responseText.setText("");

        String[] answerArray = ((ClosedQuestion) this.quiz.getCurrentQuestion()).toAnswerArray();
        int i = 0;

        for (Button answerButton : answers) {
            answerButton.setText(answerArray[i]);
            answerButton.setEnabled(true);
            i++;

            answerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hasUserAnswered = true;
                    for (Button answerButton : answers) {
                        answerButton.setEnabled(false);
                    }

                    if (isAnswerCorrect(view, quiz)) {
                        responseText.setText(R.string.correct_answer);
                    } else {
                        String message = getString(R.string.incorrect_answer) + " " + quiz.getCurrentQuestion().getCorrectAnswer();
                        responseText.setText(message);
                    }
                }
            });
        }
    }
}
