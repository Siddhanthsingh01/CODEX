package com.example.codex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.codex.Logins.Login;
import com.example.codex.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    //variables for animation
    Animation topAnim, bottomAnim;
    private ActivityMainBinding binding;

    //firebase auth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setting full screen hiding status
        getWindow().setStatusBarColor(getColor(R.color.black_coral));
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //view binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //instancing mAuth
        mAuth = FirebaseAuth.getInstance();


        //animation
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //start animation
        binding.mainActIvStart.setAnimation(topAnim);
        binding.mainActTvName.setAnimation(bottomAnim);
        binding.mainActTvSlogan.setAnimation(bottomAnim);


    }
    //disables back btn
    @Override
    public void onBackPressed() {

    }
    //checking user logged in or logged out
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        //if user is logged out then go to login activity
        int SPLASH_SCREEN = 4000;
        if (user == null){
            //to animate and go in 5sec
            new Handler(getMainLooper()).postDelayed(() -> {
                Intent intent = new Intent(MainActivity.this, Login.class);


                Pair<View, String> p1 =  Pair.create(binding.mainActIvStart, "logo_image");
                Pair<View, String> p2 =  Pair.create(binding.mainActTvName, "logo_text");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, p1,p2);

                startActivity(intent, options.toBundle());

//            finish();
            }, SPLASH_SCREEN);
        }

        //else user is not logged out then go to home
        else{
            //to animate and go in 5sec
            new Handler(getMainLooper()).postDelayed(() -> {
                Intent intent = new Intent(MainActivity.this, homeOptions.class);
                startActivity(intent);
//            finish();
            }, SPLASH_SCREEN);
        }
    }
}