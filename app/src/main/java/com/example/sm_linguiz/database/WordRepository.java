package com.example.sm_linguiz.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private WordDao wordDao;
    private LiveData<List<Word>> words;

    public WordRepository(Application application) {
        WordDatabase database = WordDatabase.getDatabase(application);
        wordDao = database.wordDao();

    }

    LiveData<List<Word>> findAllWords() {
        words = wordDao.findAll();
        return words;
    }

    public LiveData<List<Word>> findAllWordsFromLevel(String level) {
        words = wordDao.findWordsFromLevel(level);
        return words;
    }

    void insert(final Word word) {
        WordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                wordDao.insert((word));
            }
        });
    }

    void updade(final Word word) {
        WordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                wordDao.update(word);
            }
        });
    }

    void delete(final Word word) {
        WordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                wordDao.delete(word);
            }
        });
    }
}
