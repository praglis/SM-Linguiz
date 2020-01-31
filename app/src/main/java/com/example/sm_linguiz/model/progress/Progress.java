package com.example.sm_linguiz.model.progress;

import com.example.sm_linguiz.database.Word;
import com.example.sm_linguiz.model.proxy.Dictionary;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Progress {
    private static final Progress instance = new Progress();
    private int accessedWordNumber;

    private Progress() {
        accessedWordNumber = 0;
    }

    public static Progress getInstance() {
        return Progress.instance;
    }

    public Word getWeakestWord(Dictionary dictionary, boolean firstWord) {
        if (firstWord) accessedWordNumber = 0;
        else accessedWordNumber++;

        List<Word> wordList = new LinkedList<>(dictionary.getWordList());
        Collections.sort(wordList);

        return wordList.get(accessedWordNumber % wordList.size());
    }
}