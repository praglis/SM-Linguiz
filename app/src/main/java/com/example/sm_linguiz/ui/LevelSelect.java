package com.example.sm_linguiz.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.sm_linguiz.R;
import com.example.sm_linguiz.database.DictionaryViewModel;
import com.example.sm_linguiz.database.Word;
import com.example.sm_linguiz.model.proxy.DictionaryProxy;
import com.example.sm_linguiz.model.quiz.LearnQuiz;
import com.example.sm_linguiz.model.quiz.Quiz;
import com.example.sm_linguiz.model.quiz.TestQuiz;
import com.example.sm_linguiz.ui.learn.LearnQuestionActivity;
import com.example.sm_linguiz.ui.test.TestQuestionActivity;

import java.util.List;

import static com.example.sm_linguiz.ui.MainActivity.LEARN_OR_TEST;

public class LevelSelect extends AppCompatActivity {
    public static final String SELECTED_LEVEL = "selectedLevel";
    public static final String QUIZ = "quiz";
    public static final String IS_ANSWER_CORRECT = "is_correct";
    public static final int CORRECT_ANSWER_REQUEST_CODE = 2;
    private Button backButton;
    private Button[] levelButtons;
    private DictionaryViewModel dictionaryViewModel;
    private DictionaryProxy dictionaryProxy;
    private static final int QUESTION_COUNT = 10;
    private Context context;
    static boolean isDataLoaded;
    Quiz quiz;
    static boolean isQuestionLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LevelSelect", "onCreate");

        setContentView(R.layout.activity_level_select);

        context = this;

        backButton = findViewById(R.id.back_button);

        levelButtons = new Button[]{
                findViewById(R.id.level_a1),
                findViewById(R.id.level_a2),
                findViewById(R.id.level_b1),
                findViewById(R.id.level_b2),
                findViewById(R.id.level_c1),
                findViewById(R.id.level_c2)
        };

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LevelSelect", "onCreate->onClick(back)");
                Intent intent = new Intent(LevelSelect.this, MainActivity.class);
                startActivity(intent);
            }
        });

        for (Button levelButton : levelButtons) {
            levelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("LevelSelect", "onCreate->onClick(answer)");

                    String selectedLevel = (String) ((Button) view).getText();

                    boolean learnOrTest = getIntent().getBooleanExtra(LEARN_OR_TEST, true);
                    dictionaryProxy = new DictionaryProxy(selectedLevel);

                    isDataLoaded = dictionaryProxy.getWordList().size() >= 250;

                    dictionaryViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(DictionaryViewModel.class);
                    dictionaryViewModel.findAllByLevel(selectedLevel).observe((LifecycleOwner) context, new Observer<List<Word>>() {
                        @Override
                        public void onChanged(@Nullable final List<Word> words) {
                            Log.d("LevelSelect", "onCreate->onClick(answer)->onChange");

                            dictionaryProxy.updateWordList(words);

                            if (dictionaryProxy.getWordList().size() < 250 && !isDataLoaded)
                                return;//todo ok number?
                            isDataLoaded = true;
                            Log.d("LevelSelect", "after if 250");

                            if (learnOrTest) {
                                Log.d("LevelSelect", "after if learnOrTest == true");
                                quiz = new LearnQuiz(dictionaryProxy, QUESTION_COUNT);
                                if (!isQuestionLoaded) {
                                    Log.d("LevelSelect", "after if isQuestionLoaded != true");
                                    isQuestionLoaded = true;
                                    Intent intent = new Intent(LevelSelect.this, LearnQuestionActivity.class);
                                    intent.putExtra(QUIZ, quiz);
                                    startActivity(intent);
                                }
                            } else {
                                Log.d("LevelSelect", "after if learnOrTest == false");
                                quiz = new TestQuiz(dictionaryProxy, QUESTION_COUNT);
                                if (!isQuestionLoaded) {
                                    Log.d("LevelSelect", "after if isQuestionLoaded != true");

                                    isQuestionLoaded = true;
                                    Intent intent = new Intent(LevelSelect.this, TestQuestionActivity.class);
                                    intent.putExtra(QUIZ, quiz);
                                    startActivity(intent);
                                }
                            }
                        }
                    });
                }
            });
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        isQuestionLoaded = false;
        isDataLoaded = false;
    }
}
