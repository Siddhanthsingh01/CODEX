package com.example.codex.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.codex.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class wonactivity extends AppCompatActivity {

    CircularProgressBar circularProgressBar;
    TextView resulttext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wonactivity);

        circularProgressBar=findViewById(R.id.circularProgressBar);
        resulttext=findViewById(R.id.resulttext);



    }
}