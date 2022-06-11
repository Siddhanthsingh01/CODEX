package com.example.codex.category;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.codex.HidekeyInternet.KeyboardHideInternet;
import com.example.codex.Slider.SliderImage;
import com.example.codex.c_list_select.c_four;
import com.example.codex.c_list_select.c_one;
import com.example.codex.c_list_select.c_three;
import com.example.codex.c_list_select.c_two;
import com.example.codex.databinding.ActivityClangBinding;
import com.example.codex.databinding.ActivityJavaBinding;
import com.example.codex.java_list_select.java_one;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class clang extends AppCompatActivity {
    private ActivityClangBinding binding;

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


        //view binding
        binding = ActivityClangBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        keyin = new KeyboardHideInternet();



        //java_one catagory card view
        binding.cone.setOnClickListener(view -> {
            if(!keyin.isConnected(clang.this))
                keyin.connectionAlert(clang.this);
            else
                startActivity(new Intent(this, c_one.class));
        });

        //java_one catagory card view
        binding.ctwo.setOnClickListener(view -> {
            if(!keyin.isConnected(clang.this))
                keyin.connectionAlert(clang.this);
            else
                startActivity(new Intent(this, c_two.class));
        });

        binding.cthree.setOnClickListener(view -> {
            if(!keyin.isConnected(clang.this))
                keyin.connectionAlert(clang.this);
            else
                startActivity(new Intent(this, c_three.class));
        });

        binding.cfour.setOnClickListener(view -> {
            if(!keyin.isConnected(clang.this))
                keyin.connectionAlert(clang.this);
            else
                startActivity(new Intent(this, c_four.class));
        });


    }
}