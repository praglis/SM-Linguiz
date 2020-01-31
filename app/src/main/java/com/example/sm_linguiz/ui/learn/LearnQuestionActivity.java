package com.example.sm_linguiz.ui.learn;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.example.sm_linguiz.R;
import com.example.sm_linguiz.database.DictionaryViewModel;
import com.example.sm_linguiz.database.Word;
import com.example.sm_linguiz.model.question.ClosedQuestion;
import com.example.sm_linguiz.ui.MainActivity;
import com.example.sm_linguiz.ui.QuestionActivity;

import java.util.Objects;

public class LearnQuestionActivity extends QuestionActivity {
    TextView responseText;
    Button nextButton;

    boolean hasUserAnswered;
    DictionaryViewModel dictionaryViewModel;

    private SensorManager mSensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;

    private final SensorEventListener mSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            if (mAccel > 12) {
                Toast.makeText(getApplicationContext(), getString(R.string.question_skipped), Toast.LENGTH_SHORT).show();
                quiz.nextQuestion();

                if (quiz.getCurrentQuestionNumber() >= quiz.getQuestions().size()) {
                    Intent intent = new Intent(LearnQuestionActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    setQuestion();
                }
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
    @Override
    protected void onResume() {
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }
    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LearnQuestionActivity", "onCreate[PR]");

        setContentView(R.layout.activity_learn_question);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(mSensorManager).registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

        hasUserAnswered = false;
        //dictionaryViewModel = (DictionaryViewModel) getIntent().getSerializableExtra(DICTIONARY_VIEW_MODEL);
        dictionaryViewModel = new ViewModelProvider(this).get(DictionaryViewModel.class);


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

                if (quiz.getCurrentQuestionNumber() >= quiz.getQuestions().size()) {
                    Intent intent = new Intent(LearnQuestionActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    setQuestion();
                }
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
                    Word answeredWord = quiz.getCurrentQuestion().getCorrectWord();
                    if (isAnswerCorrect(view, quiz)) {
                        responseText.setText(R.string.correct_answer);
                        answeredWord.changeSkill(1);

                    } else {
                        String message = getString(R.string.incorrect_answer) + " " + quiz.getCurrentQuestion().getCorrectAnswer();
                        responseText.setText(message);
                        answeredWord.changeSkill(-1);
                    }
                    dictionaryViewModel.update(answeredWord);
                }
            });
        }
    }
}
