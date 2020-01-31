package com.example.sm_linguiz.ui.learn;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LearnViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LearnViewModel() {
        Log.d("LearnViewModel", "LearnViewModel");

        mText = new MutableLiveData<>();
        mText.setValue("Number of questions in a learn session:");
    }

    LiveData<String> getText() {
        return mText;
    }
}