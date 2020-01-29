package com.example.sm_linguiz.model.builder;

import com.example.sm_linguiz.database.Word;
import com.example.sm_linguiz.model.progress.Progress;
import com.example.sm_linguiz.model.proxy.DictionaryProxy;
import com.example.sm_linguiz.model.question.ClosedQuestion;
import com.example.sm_linguiz.model.question.OpenedQuestion;
import com.example.sm_linguiz.model.question.Question;

import java.util.Random;

public class LearnQuizBuilder extends QuizBuilder {
    boolean firstQuestion;

    public LearnQuizBuilder(DictionaryProxy dictionaryProxy, Progress progress) {
        super(dictionaryProxy);
        firstQuestion = true;
    }

    @Override
    public void createQuestions() {
        int questionCount = 20;
        for (int i = 0; i < questionCount; i++) {
            createQuestion();
        }
    }

    @Override
    public void createQuestion() {
        Question question;
        Random random = new Random();
        boolean englishOrPolish = random.nextBoolean();
        boolean openedOrClosed = random.nextBoolean();
        Word weakestWord;

        do {
            weakestWord = progress.getWeakestWord(this.dictionaryProxy.getLevel(), firstQuestion);
            firstQuestion = false;
        } while (isWordInQuiestions(weakestWord));


        if (openedOrClosed) {
            question = new OpenedQuestion(weakestWord, englishOrPolish);
        } else {
            question = new ClosedQuestion(weakestWord, dictionaryProxy.getRandomWords(3), englishOrPolish);
        }
        questions.add(question);
    }

    private boolean isWordInQuiestions(Word word) {
        if (this.questions == null) return false;
        for (Question question : this.questions) {
            if (question.getCorrectWord() == word) return true;
        }
        return false;
    }
}
