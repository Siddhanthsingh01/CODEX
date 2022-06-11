package com.example.codex.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.codex.HidekeyInternet.KeyboardHideInternet;
import com.example.codex.R;
import com.example.codex.Slider.SliderImage;
import com.example.codex.databinding.ActivityJavaBinding;
import com.example.codex.homeOptions;
import com.example.codex.java_list_select.java_four;
import com.example.codex.java_list_select.java_one;
import com.example.codex.java_list_select.java_three;
import com.example.codex.java_list_select.java_two;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class java extends AppCompatActivity {
    private ActivityJavaBinding binding;

    ScrollView contentView;

    private TextView name_tv;
    private TextView email_tv;

    //slider
    private ViewPager2 viewPager2;
    private List<SliderImage> imageList;
    private final Handler sliderHandler = new Handler();
    KeyboardHideInternet keyin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);


        //view binding
        binding = ActivityJavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        keyin = new KeyboardHideInternet();




        //java_one catagory card view
        binding.javaone.setOnClickListener(view -> {
            if(!keyin.isConnected(java.this))
                keyin.connectionAlert(java.this);
            else
                startActivity(new Intent(this, java_one.class));

        });

        binding.javatwo.setOnClickListener(view -> {
            if(!keyin.isConnected(java.this))
                keyin.connectionAlert(java.this);
            else
                startActivity(new Intent(this, java_two.class));
        });

        binding.javathree.setOnClickListener(view -> {
            if(!keyin.isConnected(java.this))
                keyin.connectionAlert(java.this);
            else
                startActivity(new Intent(this, java_three.class));
        });

        binding.javafour.setOnClickListener(view -> {
            if(!keyin.isConnected(java.this))
                keyin.connectionAlert(java.this);
            else
                startActivity(new Intent(this, java_four.class));
        });



    }
}