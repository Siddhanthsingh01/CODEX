package com.example.codex.c_plus_list_select;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codex.R;
import com.example.codex.compiler.compiler;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class c_plus_two extends AppCompatActivity {

    // creating a variable
    // for PDF view.
    PDFView pdfView;

    // url of our PDF file.
    String pdfurl = "https://firebasestorage.googleapis.com/v0/b/codex-4a5c4.appspot.com/o/c%2B%2B%2FSum%20of%20the%20digits.pdf?alt=media&token=1bd0c730-2c90-43a0-93eb-9d07418c5b3d";

    VideoView videoView;


    // Make sure to use the FloatingActionButton
    // for all the FABs
    FloatingActionButton mAddFab, mAddAlarmFab, mAddPersonFab;

    // These are taken to make visible and invisible along
    // with FABs
    TextView addAlarmActionText, addPersonActionText;

    // to check whether sub FAB buttons are visible or not.
    Boolean isAllFabsVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_plus_two);

        // pdf view.
        pdfView = findViewById(R.id.idPDFView);
        new c_plus_two.RetrivePDFfromUrl().execute(pdfurl);

        //video view
        MediaController mediaController = new MediaController(this);
        videoView = findViewById(R.id.video_view);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/codex-4a5c4.appspot.com/o/file_example_MP4_480_1_5MG.mp4?alt=media&token=5ec602ea-176a-445c-905a-cb3fba116dbb");
        videoView.setVideoURI(uri);
        videoView.start();

        //fab botton
        // Register all the FABs with their IDs
        // This FAB button is the Parent
        mAddFab = findViewById(R.id.add_fab);
        // FAB button
        mAddAlarmFab = findViewById(R.id.add_alarm_fab);
        mAddPersonFab = findViewById(R.id.add_person_fab);

        // Also register the action name text, of all the FABs.
        addAlarmActionText = findViewById(R.id.add_alarm_action_text);
        addPersonActionText = findViewById(R.id.add_person_action_text);

        // Now set all the FABs and all the action name
        // texts as GONE
        mAddAlarmFab.setVisibility(View.GONE);
        mAddPersonFab.setVisibility(View.GONE);
        addAlarmActionText.setVisibility(View.GONE);
        addPersonActionText.setVisibility(View.GONE);

        // make the boolean variable as false, as all the
        // action name texts and all the sub FABs are invisible
        isAllFabsVisible = false;

        // We will make all the FABs and action name texts
        // visible only when Parent FAB button is clicked So
        // we have to handle the Parent FAB button first, by
        // using setOnClickListener you can see below
        mAddFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isAllFabsVisible) {

                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs VISIBLE.
                            mAddAlarmFab.show();
                            mAddPersonFab.show();
                            addAlarmActionText.setVisibility(View.VISIBLE);
                            addPersonActionText.setVisibility(View.VISIBLE);

                            // make the boolean variable true as
                            // we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible = true;
                        } else {

                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs GONE.
                            mAddAlarmFab.hide();
                            mAddPersonFab.hide();
                            addAlarmActionText.setVisibility(View.GONE);
                            addPersonActionText.setVisibility(View.GONE);

                            // make the boolean variable false
                            // as we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible = false;
                        }
                    }
                });

        // below is the sample action to handle add person
        // FAB. Here it shows simple Toast msg. The Toast
        // will be shown only when they are visible and only
        // when user clicks on them
        mAddPersonFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(c_plus_two.this, compiler.class);
                        intent.putExtra("url","https://www.w3schools.com/cpp/trycpp.asp?filename=demo_compiler");
                        startActivity(intent);
                    }
                });

        // below is the sample action to handle add alarm
        // FAB. Here it shows simple Toast msg The Toast
        // will be shown only when they are visible and only
        // when user clicks on them
        mAddAlarmFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(c_plus_two.this, "Alarm Added", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    class RetrivePDFfromUrl extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            // we are using inputstream
            // for getting out PDF.
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                // below is the step where we are
                // creating our connection.
                HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    // response is success.
                    // we are getting input stream from url
                    // and storing it in our variable.
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                // this is the method
                // to handle errors.
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            // after the execution of our async
            // task we are loading our pdf in our pdf view.
            pdfView.fromStream(inputStream).load();
        }
    }
}


