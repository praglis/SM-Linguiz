package com.example.sm_linguiz.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word")
public class Word implements Comparable<Word> {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String englishWord;
    private String polishWord;
    private int skill;
    private String level;

    public Word(String englishWord, String polishWord, String level) {
        this.englishWord = englishWord;
        this.polishWord = polishWord;
        skill = 0;
        this.level = level;
    }

    public void setId(int id) {
        this.id = id;
    }

    void setSkill(int skill) {
        this.skill = skill;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public int getSkill() {
        return skill;
    }

    public String getLevel() {
        return level;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getPolishWord() {
        return polishWord;
    }

    public void setPolishWord(String polishWord) {
        this.polishWord = polishWord;
    }

    public String getAppropriateWord(boolean english) {
        return english ? englishWord : polishWord;
    }

    @Override
    public String toString() {
        return englishWord + "=" + polishWord;
    }

    @Override
    public boolean equals(Object obj) {
        super.equals(obj);
        return this.englishWord.equals(((Word) obj).englishWord);
    }

    @Override
    public int hashCode() {
        int result;
        result = englishWord.hashCode() / 11;
        return result;
    }

    @Override
    public int compareTo(Word o) {
        return Integer.compare(this.skill, o.skill);
    }
}
