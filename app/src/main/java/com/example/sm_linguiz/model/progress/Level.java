package com.example.sm_linguiz.model.progress;

import com.example.sm_linguiz.database.Word;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Level implements Serializable {
    String name;
    HashMap<Word, Integer> map;
    public static String[] levels = new String[]{"A1", "A2", "B1", "B2", "C1", "C2"};

    public Level(String name) {
        this.name = name;
        map = new LinkedHashMap<>();
    }

    public int toInteger() {
        char letter = name.charAt(0);
        int letterValue = 6, number = name.charAt(1) - '0';
        switch (letter) {
            case 'A':
                letterValue = -1;
                break;
            case 'B':
                letterValue = 1;
                break;
            case 'C':
                letterValue = 3;
                break;
        }
        return letterValue + number;
    }
}
