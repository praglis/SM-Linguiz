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

    public void insert(Word word) {
        wordRepository.insert(word);
    }

    public void update(Word word) {
        wordRepository.updade(word);
    }

    public void delete(Word word) {
        wordRepository.delete(word);
    }

//    @Override
//    public Iterator<Word> iterator() {
//        return new Iterator<Word>() {
//            private int before = 0;
//
//            @Override
//            public boolean hasNext() {
//                return before < wordList.size();
//            }
//
//            @Override
//            public Word next() {
//                return wordList.get(before++);
//            }
//
//            @Override
//            public void remove() {
//                for (int i = 0; i <= wordList.size(); i++) {
//                    if (this.before - 1 == i) {
//                        wordList.remove(i);
//                    }
//                }
//            }
//        };
//    }
}
