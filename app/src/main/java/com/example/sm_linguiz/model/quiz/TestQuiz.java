package com.example.sm_linguiz.model.quiz;

import com.example.sm_linguiz.model.builder.TestQuizBuilder;
import com.example.sm_linguiz.model.proxy.DictionaryProxy;

import java.util.LinkedList;

public class TestQuiz extends Quiz {
    private int score;
    private boolean[] answerCorrectness;// todo use dis

    public TestQuiz(DictionaryProxy dictionaryProxy, int questionCount) {
        super(dictionaryProxy);
        score = 0;

        TestQuizBuilder testQuizBuilder = new TestQuizBuilder(this.dictionaryProxy);
        testQuizBuilder.createQuestions(questionCount);
        this.questions = new LinkedList<>(testQuizBuilder.getQuestions());
        answerCorrectness = new boolean[questionCount];
    }

//    public void updateScore() throws FileNotFoundException {
//        File plik = new File(Objects.requireNonNull(MainLauncher.class.getClassLoader().getResource("ranking")).getFile());
//        Scanner odczyt = new Scanner(plik);
//
//        String[] rankingLevel = new String[6];
//        String[] rankingScore = new String[6];
//
//        String helpLevel;
//        String helpScore;
//
//        for (int i = 0; i < 6; i++) {
//            if (odczyt.hasNextLine()) {
//                helpLevel = odczyt.nextLine();
//                helpScore = odczyt.nextLine();
//                if (helpLevel.equals(dictionaryProxy.getLevel().getName()) && Integer.parseInt(helpScore) < score) {
//                    helpLevel = dictionaryProxy.getLevel().getName();
//                    helpScore = Integer.toString(score);
//                }
//                rankingLevel[i] = helpLevel;
//                rankingScore[i] = helpScore;
//            }
//        }
//        odczyt.close();
//
//        PrintWriter zapis = new PrintWriter(plik);
//
//        for (int i = 0; i < 6; i++) {
//            zapis.println(rankingLevel[i]);
//            zapis.println(rankingScore[i]);
//        }
//        zapis.close();
//
//    } //todo

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore(int value) {
        score += value;
    }

    public void markCurrentQuestionCorrectness(boolean isCorrect) {
        answerCorrectness[this.getCurrentQuestionNumber()] = isCorrect;
    }
}
