package com.rrbofficial.triviaapp.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.rrbofficial.triviaapp.controller.AppController;
import com.rrbofficial.triviaapp.model.Question;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    ArrayList <Question> questionsArrayList = new ArrayList<>();
    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";


    public List<Question> getQuestion (final  AnswerListAsyncResponce callBack)
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length() ; i++) {

                    try {
                        Question question = new Question(response.getJSONArray(i).get(0).toString(),response.getJSONArray(i).getBoolean(1) );

                        // Add Questions to arraylist

                        questionsArrayList.add(question);

                        Log.d("Hello", "getQuestion: "+questionsArrayList);


                        // here we print the each responce from LOG.
                        Log.d("Repo", "onCreate " + response.getJSONArray(i).get(0));
                        Log.d("Repo", "onCreate " + response.getJSONArray(i).getBoolean(1));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

                if(null != callBack) callBack.processFinished(questionsArrayList);

            }
        } , new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

    return questionsArrayList;
    }
}
