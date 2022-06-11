package com.example.codex.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.codex.R;

import java.util.ArrayList;

public class quiz extends AppCompatActivity {

    ArrayList<modelclass> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        list=new ArrayList<>();
        list.add(new modelclass("null"));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(quiz.this, dashboardactivity.class);
                startActivity(intent);
            }
        },1500);


    }
}