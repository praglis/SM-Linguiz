package com.example.sm_linguiz.model.proxy;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sm_linguiz.database.Word;
import com.example.sm_linguiz.database.WordRepository;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DictionaryViewModel extends AndroidViewModel implements Iterable {
    private WordRepository wordRepository;
    private LiveData<List<Word>> wordList;

    public DictionaryViewModel(@NonNull Application application, String level) {
        super(application);
        wordRepository = new WordRepository(application);
        wordList = wordRepository.findAllWordsFromLevel(level);
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

    @Override
    public List<Word> getWordList() {
        return wordList;
    }






    @Override
    public Iterator<Word> iterator() {
        return new Iterator<Word>() {
            private int before = 0;

            @Override
            public boolean hasNext() {
                return before < wordList.size();
            }

            @Override
            public Word next() {
                return wordList.get(before++);
            }

            @Override
            public void remove() {
                for (int i = 0; i <= wordList.size(); i++) {
                    if (this.before - 1 == i) {
                        wordList.remove(i);
                    }
                }
            }
        };
    }
}
