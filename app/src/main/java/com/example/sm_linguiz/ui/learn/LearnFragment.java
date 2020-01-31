package com.example.sm_linguiz.ui.learn;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.sm_linguiz.R;
import com.example.sm_linguiz.ui.LevelSelect;

import static com.example.sm_linguiz.ui.MainActivity.LEARN_OR_TEST;

public class LearnFragment extends Fragment {
    public static final String LEARN_LENGTH = "learnLength";
    EditText learnLengthEditText;
    TextView a1SkillPointsText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d("LearnFragment", "onCreateView");
        View root = inflater.inflate(R.layout.fragment_learn, container, false);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        learnLengthEditText = getView().findViewById(R.id.learn_length_edit);
        learnLengthEditText.setText(R.string.defaultLearnLength);

//        a1SkillPointsText = getView().findViewById(R.id.skill_points_a1);
//        a1SkillPointsText.setText(MainActivity.skillSums[0]);
    }

    @Override
    public void onResume() {
        super.onResume();

        Button learnButton = getView().findViewById(R.id.learn_button);
        learnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LearnFragment", "onClick");

                int learnLength = 10;
                if (learnLengthEditText.getText() != null)
                    learnLength = Integer.parseInt(learnLengthEditText.getText().toString());

                Intent intent = new Intent(getActivity(), LevelSelect.class);
                intent.putExtra(LEARN_LENGTH, learnLength);
                intent.putExtra(LEARN_OR_TEST, true);
                startActivity(intent);
            }
        });
    }
}