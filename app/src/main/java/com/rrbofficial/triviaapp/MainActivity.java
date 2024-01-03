package com.rrbofficial.triviaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Binder;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.material.snackbar.Snackbar;
import com.rrbofficial.triviaapp.controller.AppController;
import com.rrbofficial.triviaapp.data.AnswerListAsyncResponce;
import com.rrbofficial.triviaapp.data.Repository;
import com.rrbofficial.triviaapp.databinding.ActivityMainBinding;
import com.rrbofficial.triviaapp.model.Question;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {


    private ActivityMainBinding binding;
    private int currentQuestionIndex =0;
    List <Question> questionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    questionList = new Repository().getQuestion(questionArrayList ->
            {
                updateCounter(questionArrayList);
            } );


       binding.buttonNext.setOnClickListener(v -> {

       currentQuestionIndex =(currentQuestionIndex+1) % questionList.size();
       updateQuestion();

       });

       binding.buttonTrue.setOnClickListener(v -> {
            checkAnswer(true);
       });

        binding.buttonFalse.setOnClickListener(v -> {
            checkAnswer(false);
        });

    }

    private void checkAnswer(boolean userChoseCorrect) {

     boolean answer = questionList.get(currentQuestionIndex).isAnswerTrue();
     int snackMessageId =0;

     if(userChoseCorrect==answer)
     {


         snackMessageId =   R.string.correct;
     }
     else {
         snackMessageId = R.string.incorrect;
     }
        Snackbar.make(binding.cardView,snackMessageId, Snackbar.LENGTH_SHORT).show();
    }

    private void updateCounter(ArrayList<Question> questionArrayList) {
        binding.questionTextView.setText(questionArrayList.get(currentQuestionIndex).getAnswer());
        binding.textViewOutOf.setText(String.format("Question:%d/%d", currentQuestionIndex, questionArrayList.size()));

    }

    private void updateQuestion() {
        String question = questionList.get(currentQuestionIndex).getAnswer();
        binding.questionTextView.setText(question);
        updateCounter((ArrayList<Question>) questionList);
    }

}
