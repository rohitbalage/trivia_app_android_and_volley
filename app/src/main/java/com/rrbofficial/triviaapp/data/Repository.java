package com.rrbofficial.triviaapp.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.rrbofficial.triviaapp.controller.AppController;
import com.rrbofficial.triviaapp.model.Question;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    ArrayList <Question> questionsArrayList = new ArrayList<>();
    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";


    public List<Question> getQuestion ()
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Repo", "onCreate " + response.toString());
            }
        } , new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

    return null;
    }
}
