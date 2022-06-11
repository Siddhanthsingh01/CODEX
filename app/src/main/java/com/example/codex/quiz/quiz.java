package com.example.codex.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.codex.R;

import java.util.ArrayList;

public class quiz extends AppCompatActivity {

    public static ArrayList<modelclass> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        list=new ArrayList<>();
        list.add(new modelclass("what is your name","A","B","C","D","B"));
        list.add(new modelclass("what is your qulification","A","B","C","D","Ans"));
        list.add(new modelclass("what is your age","A","B","C","D","Ans"));
        list.add(new modelclass("what is your num","A","B","C","D","Ans"));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(quiz.this, dashboardactivity.class);
                startActivity(intent);
            }
        },1500);


    }
}