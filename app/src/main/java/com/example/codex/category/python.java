package com.example.codex.category;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.codex.HidekeyInternet.KeyboardHideInternet;
import com.example.codex.R;
import com.example.codex.Slider.SliderImage;
import com.example.codex.databinding.ActivityJavaBinding;
import com.example.codex.databinding.ActivityPythonBinding;
import com.example.codex.java_list_select.java_one;
import com.example.codex.python_list_select.python_four;
import com.example.codex.python_list_select.python_one;
import com.example.codex.python_list_select.python_three;
import com.example.codex.python_list_select.python_two;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class python extends AppCompatActivity {
    private ActivityPythonBinding binding;

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
        binding = ActivityPythonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        keyin = new KeyboardHideInternet();


        //on clicking logout image
        binding.pythonone.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            //finishing received activity
            finish();
        });

        //java_one catagory card view
        binding.pythonone.setOnClickListener(view -> {
            if(!keyin.isConnected(python.this))
                keyin.connectionAlert(python.this);
            else
                startActivity(new Intent(this, python_one.class));
        });

        //java_one catagory card view
        binding.pythontwo.setOnClickListener(view -> {
            if(!keyin.isConnected(python.this))
                keyin.connectionAlert(python.this);
            else
                startActivity(new Intent(this, python_two.class));
        });

        //java_one catagory card view
        binding.pythonthree.setOnClickListener(view -> {
            if(!keyin.isConnected(python.this))
                keyin.connectionAlert(python.this);
            else
                startActivity(new Intent(this, python_three.class));
        });

        //java_one catagory card view
        binding.pythonfour.setOnClickListener(view -> {
            if(!keyin.isConnected(python.this))
                keyin.connectionAlert(python.this);
            else
                startActivity(new Intent(this, python_four.class));
        });



    }
}