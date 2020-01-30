package com.example.sm_linguiz.ui.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.example.sm_linguiz.R;
import com.example.sm_linguiz.ui.LevelSelect;

import static com.example.sm_linguiz.ui.MainActivity.LEARN_OR_TEST;

public class TestFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d("TestFragment", "onCreateView");

        TestViewModel testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        View root = inflater.inflate(R.layout.fragment_test, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        testViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button learnButton = root.findViewById(R.id.test_button);
        learnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TestFragment", "onClick");

                Intent intent = new Intent(getActivity(), LevelSelect.class);
                intent.putExtra(LEARN_OR_TEST, false);
                startActivity(intent);
            }
        });

        return root;
    }
}