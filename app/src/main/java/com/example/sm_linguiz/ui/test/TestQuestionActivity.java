package com.example.sm_linguiz.ui.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sm_linguiz.R;
import com.example.sm_linguiz.model.question.ClosedQuestion;
import com.example.sm_linguiz.model.quiz.TestQuiz;
import com.example.sm_linguiz.ui.QuestionActivity;
import com.example.sm_linguiz.ui.TestResultActivity;

import static com.example.sm_linguiz.ui.LevelSelect.QUIZ;

public class TestQuestionActivity extends QuestionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TestQuestionActivity", "onCreate");

        setContentView(R.layout.activity_test_question);

        questionText = findViewById(R.id.test_question_text);

        answers = new Button[]{
                findViewById(R.id.test_answer_a),
                findViewById(R.id.test_answer_b),
                findViewById(R.id.test_answer_c),
                findViewById(R.id.test_answer_d)
        };

        setQuestion();
    }

    private void setQuestion() {
        Log.d("TestQuestionActivity", "setQuestion");

        questionText.setText(quiz.getCurrentQuestion().getQuestionText());

        String[] answerArray = ((ClosedQuestion) this.quiz.getCurrentQuestion()).toAnswerArray();
        int i = 0;

        for (Button answerButton : answers) {
            answerButton.setText(answerArray[i]);
            answerButton.setEnabled(true);
            i++;

            answerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("TestQuestionActivity", "setQuestion->onClick");

                    quiz.setCurrentQuestionUserAnswer((String) ((Button) view).getText());

                    if (isAnswerCorrect(view, quiz)) {
                        ((TestQuiz) quiz).markCurrentQuestionCorrectness(true);
                        ((TestQuiz) quiz).incrementScore(1);
                    }

                    quiz.nextQuestion();

                    if (quiz.getCurrentQuestionNumber() >= quiz.getQuestions().size() - 1) {
                        Intent intent = new Intent(TestQuestionActivity.this, TestResultActivity.class);
                        intent.putExtra(QUIZ, quiz);
                        startActivity(intent);
                    }

                    setQuestion();
                }
            });
        }
    }
}
