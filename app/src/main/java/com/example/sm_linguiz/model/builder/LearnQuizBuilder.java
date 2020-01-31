package com.example.sm_linguiz.model.builder;

import com.example.sm_linguiz.database.Word;
import com.example.sm_linguiz.model.progress.Progress;
import com.example.sm_linguiz.model.proxy.Dictionary;
import com.example.sm_linguiz.model.question.ClosedQuestion;
import com.example.sm_linguiz.model.question.Question;

import java.util.Random;

public class LearnQuizBuilder extends QuizBuilder {
    private boolean firstQuestion;

    public LearnQuizBuilder(Dictionary dictionary, Progress progress) {
        super(dictionary);
        firstQuestion = true;
    }

    @Override
    public void createQuestions(int questionCount) {
        for (int i = 0; i < questionCount; i++) {
            createQuestion();
        }
    }

    @Override
    public void createQuestion() {
        Question question;
        Random random = new Random();
        boolean englishOrPolish = random.nextBoolean();
        Word weakestWord;

        do {
            weakestWord = progress.getWeakestWord(this.dictionary, firstQuestion);
            firstQuestion = false;
        } while (isWordInQuiestions(weakestWord));

        question = new ClosedQuestion(weakestWord, dictionary.getRandomWords(3), englishOrPolish);
        questions.add(question);
    }

    private boolean isWordInQuiestions(Word word) {
        if (this.questions == null) return false;
        for (Question question : this.questions) {
            if (question.getCorrectWord().equals(word)) return true;
        }
        return false;
    }
}
