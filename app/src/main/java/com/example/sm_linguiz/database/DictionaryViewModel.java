package com.example.sm_linguiz.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DictionaryViewModel extends AndroidViewModel {
    private WordRepository wordRepository;
    private LiveData<List<Word>> wordList;

    public DictionaryViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
    }

    public LiveData<List<Word>> findAll() {
        return wordList;
    }

    public LiveData<List<Word>> findAllByLevel(String level) {
        wordList = wordRepository.findAllWordsFromLevel(level);
        return wordList;
    }

    public LiveData<Integer> getSkillSumByLevel(String level) {
        return wordRepository.getPointsForLevel(level);
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
