package com.example.codex.category;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.codex.HidekeyInternet.KeyboardHideInternet;
import com.example.codex.Slider.SliderImage;
import com.example.codex.c_plus_list_select.c_plus_four;
import com.example.codex.c_plus_list_select.c_plus_one;
import com.example.codex.c_plus_list_select.c_plus_three;
import com.example.codex.c_plus_list_select.c_plus_two;
import com.example.codex.databinding.ActivityCPlusBinding;
import com.example.codex.databinding.ActivityJavaBinding;
import com.example.codex.java_list_select.java_one;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class c_plus extends AppCompatActivity {
    private ActivityCPlusBinding binding;

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
        binding = ActivityCPlusBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        keyin = new KeyboardHideInternet();


        //on clicking logout image


        //java_one catagory card view
        binding.cplusone.setOnClickListener(view -> {
            if(!keyin.isConnected(c_plus.this))
                keyin.connectionAlert(c_plus.this);
            else
                startActivity(new Intent(this, c_plus_one.class));
        });

        binding.cplustwo.setOnClickListener(view -> {
            if(!keyin.isConnected(c_plus.this))
                keyin.connectionAlert(c_plus.this);
            else
                startActivity(new Intent(this, c_plus_two.class));
        });

        binding.cplusthree.setOnClickListener(view -> {
            if(!keyin.isConnected(c_plus.this))
                keyin.connectionAlert(c_plus.this);
            else
                startActivity(new Intent(this, c_plus_three.class));
        });

        binding.cplusfour.setOnClickListener(view -> {
            if(!keyin.isConnected(c_plus.this))
                keyin.connectionAlert(c_plus.this);
            else
                startActivity(new Intent(this, c_plus_four.class));
        });


    }
}