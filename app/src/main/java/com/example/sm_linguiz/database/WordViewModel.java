package com.example.sm_linguiz.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository wordRepository;

    private LiveData<List<Word>> words;

    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
        words = wordRepository.findAllWords();
    }

    public LiveData<List<Word>> findAll() {
        return words;
    }

    public void insert(Word word) {
        wordRepository.insert(word);
    }

    public void update(Word word) {
        wordRepository.updade(word);
    }

    public void delete(Word word) {
        wordRepository.delete(word);
    }
}
