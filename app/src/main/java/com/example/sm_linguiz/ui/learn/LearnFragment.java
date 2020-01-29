package com.example.sm_linguiz.ui.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sm_linguiz.LevelSelect;
import com.example.sm_linguiz.R;

import static com.example.sm_linguiz.MainActivity.LEARN_OR_TEST;

public class LearnFragment extends Fragment {

    private LearnViewModel learnViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        learnViewModel =
                ViewModelProviders.of(this).get(LearnViewModel.class);
        View root = inflater.inflate(R.layout.fragment_learn, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        learnViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button learnButton = root.findViewById(R.id.learn_button);
        learnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LevelSelect.class);
                intent.putExtra(LEARN_OR_TEST, true);
                startActivity(intent);
            }
        });

        return root;
    }
}