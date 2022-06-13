package com.example.codex.quiz;

import static com.facebook.FacebookSdk.getApplicationContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codex.BuildConfig;
import com.example.codex.R;
import com.example.codex.homeOptions;
import com.example.codex.java_list_select.java_one;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class wonactivity extends AppCompatActivity {

    CircularProgressBar circularProgressBar;
    TextView resulttext;
    int correct, wrong;
    LinearLayout btnshare;
    ImageView icback;
    TextView exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wonactivity);

        icback = findViewById(R.id.icback);
        exit = findViewById(R.id.exit);

        icback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(wonactivity.this, homeOptions.class);
                startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                finish();

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(wonactivity.this, homeOptions.class);
                startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                finish();
            }
        });


        correct = getIntent().getIntExtra("correct", 0);
        wrong = getIntent().getIntExtra("wrong", 0);

        circularProgressBar = findViewById(R.id.circularProgressBar);
        resulttext = findViewById(R.id.resulttext);
        btnshare = findViewById(R.id.btnshare);

        circularProgressBar.setProgress(correct);
        resulttext.setText(correct + "/10");

        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(
                        android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(
                        android.content.Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=scr.android.rajputana.dipresultofficial");
                startActivity(Intent.createChooser(
                        i,
                        "share via"));

                Toast.makeText(getApplicationContext(),"you click on menu share", Toast.LENGTH_SHORT).show();

            }
        });


    }
}