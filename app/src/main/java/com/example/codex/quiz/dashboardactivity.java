package com.example.codex.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;

import com.example.codex.R;
import com.example.codex.homeOptions;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

public class dashboardactivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    int timerValue=20;
    RoundedHorizontalProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        progressBar=findViewById(R.id.quiz_timer);

        countDownTimer=new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerValue=timerValue-1;
                progressBar.setProgress(timerValue);

            }

            @Override
            public void onFinish() {

                Dialog dialog=new Dialog(dashboardactivity.this);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.time_out_dialog);

                dialog.findViewById(R.id.timeout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(dashboardactivity.this, homeOptions.class);
                        startActivity(intent);
                    }
                });

                dialog.show();

            }
        }.start();


    }
}