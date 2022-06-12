package com.example.codex.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.codex.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class wonactivity extends AppCompatActivity {

    CircularProgressBar circularProgressBar;
    TextView resulttext;
    int correct,wrong;
    LinearLayout btnshare;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wonactivity);

        correct=getIntent().getIntExtra("correct",0);
        wrong=getIntent().getIntExtra("wrong",0);

        circularProgressBar=findViewById(R.id.circularProgressBar);
        resulttext=findViewById(R.id.resulttext);
        btnshare=findViewById(R.id.btnshare);

        circularProgressBar.setProgress(correct);
        resulttext.setText(correct+"/20");

        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }
}