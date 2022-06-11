package com.example.codex.compiler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.codex.R;

public class compiler extends AppCompatActivity {

    private WebView myWebView;
    String url = "https://google.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compiler);


        myWebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings;
        webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        myWebView.loadUrl(url);
        myWebView.setWebViewClient(new WebViewClient());



    }
}


