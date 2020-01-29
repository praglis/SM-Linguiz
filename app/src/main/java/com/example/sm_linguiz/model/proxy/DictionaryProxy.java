package com.example.sm_linguiz.model.proxy;

import com.example.sm_linguiz.database.Word;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class DictionaryProxy implements Serializable {
    private String level;
    private List<Word> wordList;

    public DictionaryProxy(String level, List<Word> wordList) {
        this.level = level;
        this.wordList = new LinkedList<>(wordList);
    }

    public List<Word> getWordList() {
        return this.wordList;
    }

    public String getLevel() {
        return level;
    }

    public Word getRandomWord() {
        HashSet<Word> set = new HashSet<>(this.getWordList());
        int size = set.size();
        int item = new Random().nextInt(size);
        int i = 0;
        for (Word word : set) {
            if (i == item) return word;
            i++;
        }
        return null;
    }

    public Word[] getRandomWords(int wordsNumber) {
        Word[] randomWords = new Word[wordsNumber];
        for (int j = 0; j < wordsNumber; j++) {
            randomWords[j] = this.getRandomWord();
        }
        return randomWords;
    }

//    public Iterator<Word> iterator(){
//        if (this.dictionaryViewModel == null) this.dictionaryViewModel = new DictionaryViewModel(this.level);
//        return dictionaryViewModel.iterator();
//    }
}
