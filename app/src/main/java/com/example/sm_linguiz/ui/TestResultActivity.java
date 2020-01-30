package com.example.sm_linguiz.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sm_linguiz.R;
import com.example.sm_linguiz.database.Word;
import com.example.sm_linguiz.model.quiz.TestQuiz;

import java.util.LinkedList;
import java.util.List;

import static com.example.sm_linguiz.ui.LevelSelect.QUIZ;

public class TestResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);
    }

    @Override
    protected void onStart() {
        setupResultListView();
        super.onStart();
    }

    private void setupResultListView() {
        TestQuiz testQuiz = (TestQuiz) getIntent().getSerializableExtra(QUIZ);
        List<Word> words = new LinkedList<>(testQuiz.getWords());

        TextView testScore = findViewById(R.id.overall_result_text);
        testScore.setText(getString(R.string.overall_result) + " " + testQuiz.getScore());
        RecyclerView recyclerView = findViewById(R.id.result_recyclerview);

        final ResultAdapter adapter = new ResultAdapter();
        adapter.setResults(words, testQuiz);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        Log.d("TestResultActivity", "onBackPressed Called");
        Intent intent = new Intent(TestResultActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private class ResultHolder extends RecyclerView.ViewHolder {

        private TextView correctWordTextView;
        private TextView userAnswerTextView;
        private ImageView resultIcon;
        private CardView resultListItem;

        public ResultHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.result_list_item, parent, false));

            correctWordTextView = itemView.findViewById(R.id.result_correct_word);
            userAnswerTextView = itemView.findViewById(R.id.result_your_word);
            resultIcon = itemView.findViewById(R.id.result_icon);
            resultListItem = itemView.findViewById(R.id.result_list_item);
        }

        public void bind(Word correctWord, String userAnswer, boolean isCorrect) {
            if (correctWord != null && correctWord.getEnglishWord() != null && correctWord.getPolishWord() != null) {
                correctWordTextView.setText(correctWord.getEnglishWord() + " - " + correctWord.getPolishWord());
                if (isCorrect) {
                    resultIcon.setImageResource(R.drawable.ic_good_result);
                } else {
                    resultIcon.setImageResource(R.drawable.ic_bad_result);
                    userAnswerTextView.setText(getString(R.string.your_answer) + " " + userAnswer);
                }
            }
        }
    }

    private class ResultAdapter extends RecyclerView.Adapter<ResultHolder> {

        private List<Word> results;
        private TestQuiz testQuiz;

        @NonNull
        @Override
        public ResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ResultHolder(getLayoutInflater(), parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ResultHolder holder, int position) {
            if (results != null) {
                Word correctWord = results.get(position);
                String userAnswer = testQuiz.getUserAnswer(position);
                boolean isCorrect = testQuiz.getAnswerCorrectness()[position];

                holder.bind(correctWord, userAnswer, isCorrect);
            } else {
                Log.d("MainActivity", "No results");
            }
        }

        @Override
        public int getItemCount() {
            if (results != null) {
                return results.size();
            } else {
                return 0;
            }
        }

        void setResults(List<Word> results, TestQuiz quiz) {
            this.testQuiz = quiz;
            this.results = results;
            notifyDataSetChanged();
        }
    }
}
