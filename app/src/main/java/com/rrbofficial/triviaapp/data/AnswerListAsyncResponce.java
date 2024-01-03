package com.rrbofficial.triviaapp.data;

import com.rrbofficial.triviaapp.model.Question;

import java.util.ArrayList;

public interface AnswerListAsyncResponce {
    void processFinished(ArrayList<Question> questionArrayList);

}
