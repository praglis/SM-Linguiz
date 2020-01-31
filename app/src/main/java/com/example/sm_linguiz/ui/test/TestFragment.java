package com.example.sm_linguiz.ui.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.sm_linguiz.R;
import com.example.sm_linguiz.ui.LevelSelect;

import static com.example.sm_linguiz.ui.MainActivity.LEARN_OR_TEST;

public class TestFragment extends Fragment {
    public static final String TEST_LENGTH = "testLength";
    private Spinner spinner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d("TestFragment", "onCreateView");

        View root = inflater.inflate(R.layout.fragment_test, container, false);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        spinner = (Spinner) getView().findViewById(R.id.test_length_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.test_lengths_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();


        Button learnButton = getView().findViewById(R.id.test_button);
        learnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TestFragment", "onClick");

                int testLength = Integer.parseInt(spinner.getSelectedItem().toString());

                Intent intent = new Intent(getActivity(), LevelSelect.class);
                intent.putExtra(TEST_LENGTH, testLength);
                intent.putExtra(LEARN_OR_TEST, false);
                startActivity(intent);
            }
        });


    }
}