package com.example.sm_linguiz.model.progress;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.sm_linguiz.database.Word;
import com.example.sm_linguiz.model.proxy.DictionaryProxy;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Progress {
    private static final Progress instance = new Progress();
    private Level[] levels;
    private int accessedWordNumber;

    private Progress() {
        levels = new Level[]{
                new Level("A1"),
                new Level("A2"),
                new Level("B1"),
                new Level("B2"),
                new Level("C1"),
                new Level("C2")
        };

//        for (Level level : levels) {
//            DictionaryProxy dictionaryProxy = new DictionaryProxy(level);
//            for (Word word : dictionaryProxy.getWordList()) {
//                updateProgressLevel(level, word, 0);
//            }
//        }
//        try {
//            loadProgress();
//        } catch (IOException e) {
//            initializeProgress();
//
//            String fileSeparator = System.getProperty("file.separator");
////            File progressFile = new File(
////                    Objects.requireNonNull(MainLauncher.class.getClassLoader().getResource("progress")).getFile());
//
//            try {
////                progressFile.createNewFile();
//                saveProgress();
//            } catch (IOException ignore) {
//            }
//            if (this.levels[0] == null) {
//                initializeProgress();
//            }
//        } //todo

        accessedWordNumber = 0;
    }

    public static Progress getInstance() {
        return Progress.instance;
    }

//    @RequiresApi(api = Build.VERSION_CODES.N)
//    private void initializeProgress() {
//        for (Level level : levels) {
//            DictionaryProxy dictionaryProxy = new DictionaryProxy(level);
//            for (Word word : dictionaryProxy.getWordList()) {
//                updateProgressLevel(level, word, 0);
//            }
//        }
//    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateProgressLevel(Level level, Word word, int value) {
        Integer val = levels[level.toInteger()].map.putIfAbsent(word, value);
        if (val != null) {
            int oldValue = levels[level.toInteger()].map.get(word);
            levels[level.toInteger()].map.replace(word, oldValue + value);
        }
    }

//    public void resetLevelProgress(Level level) {
//        level.setMap(null);
//        level.setMap(new LinkedHashMap<>());
//    }//todo

//    public void resetWholeProgress() {
//        for (Level level : levels) {
//            resetLevelProgress(level);
//        }
//    } //todo

//    public void saveProgress() throws IOException {
//        String fileSeparator = System.getProperty("file.separator");
//        FileWriter writer = new FileWriter(new File(
//                Objects.requireNonNull(MainLauncher.class.getClassLoader().getResource("progress")).getFile()), false);
//        for (Level level : levels) {
//            writer.write("$ " + level.name + "\n");
//
//            HashSet entrySet = new HashSet<>(level.getMap().entrySet());
//            ArrayList<Map.Entry<Word, Integer>> entryList = new ArrayList<>(entrySet);
//            //entryList.sort(Map.Entry.comparingByValue());
//
//            for (int j = 0; j < level.map.size(); j++) {
//                writer.write(entryList.get(j).getKey() + ":" + entryList.get(j).getValue() + "\n");
//            }
//        }
//        writer.close();
//    } //todo

//    public void loadProgress() throws IOException {
//        String fileSeparator = System.getProperty("file.separator");
//        Scanner scanner = new Scanner(new FileReader(new File(
//                Objects.requireNonNull(MainLauncher.class.getClassLoader().getResource("progress")).getFile())));
//        while (scanner.hasNextLine()) {
//            String[] levelLine = scanner.nextLine().split(" ");
//            Level level = new Level(levelLine[1]);
//            while (scanner.hasNextLine() && !scanner.hasNext("\\$")) {
//                String line = scanner.nextLine();
//                String[] columns = line.split("[=:]");
//                updateProgressLevel(level, new Word(columns[0], columns[1]), Integer.parseInt(columns[2]));
//            }
//        }
//        scanner.close();


    public Word getWeakestWord(DictionaryProxy dictionaryProxy, boolean firstWord) {
        if (firstWord) accessedWordNumber = 0;
        else accessedWordNumber++;

        List<Word> wordList = new LinkedList<>(dictionaryProxy.getWordList());
        Collections.sort(wordList);

        return wordList.get(accessedWordNumber % wordList.size());
    }
}