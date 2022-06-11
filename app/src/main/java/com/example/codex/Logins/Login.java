package com.example.codex.Logins;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codex.HidekeyInternet.KeyboardHideInternet;
import com.example.codex.databinding.ActivityLoginBinding;
import com.example.codex.homeOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Login extends AppCompatActivity {

    private ActivityLoginBinding binding;

    //object of reusable class
    KeyboardHideInternet keyin = new KeyboardHideInternet();

    //firebase mAuth
     FirebaseAuth mAuth;

//    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //setting full screen hiding status
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //view binding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //instancing mAuth
        mAuth = FirebaseAuth.getInstance();

        //firebase user
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //hiding progressbar
        binding.loginPb.setVisibility(View.GONE);

        //fb auth click listener
        binding.loginActIvFacebook.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this, FacebookAuthActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        //google auth click listener
        binding.loginActIvGoogle.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this, GoogleAuthActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        //going to signup
        binding.loginActBtNewuser.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this, SignUp.class);

            //animation
//            Pair<View , String>[] pairs = new Pair[7];
            Pair<View, String> p1 = Pair.create(binding.loginActIvHealth, "logo_image");
            Pair<View, String> p2 = Pair.create(binding.loginActTvSloganWelcome, "logo_text");
            Pair<View, String> p3 = Pair.create(binding.loginActTvMsg, "logo_desc");
            Pair<View, String> p4 = Pair.create(binding.loginActEtEmail, "email_tran");
            Pair<View, String> p5 = Pair.create(binding.loginActEtPassword, "password_tran");
            Pair<View, String> p6 = Pair.create(binding.loginActBtGo, "button_tran");
            Pair<View, String> p7 = Pair.create(binding.loginActBtNewuser, "login_signup_tran");


            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, p1, p2, p3, p4, p5, p6, p7);

//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent, options.toBundle());

        });

        binding.loginActBtGo.setOnClickListener(view -> loginUser());

        //forgot password
        binding.loginActBtForgotpswd.setOnClickListener(view -> {
            finish();
            startActivity(new Intent(Login.this, ForgotPassword.class));

        });
    }


    //validate email & password
    private Boolean validateEmail() {
        String val = Objects.requireNonNull(binding.loginActEtEmail.getEditText()).getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            binding.loginActEtEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            binding.loginActEtEmail.setError("Invalid Email address");
            return false;
        } else {
            binding.loginActEtEmail.setError(null);
            binding.loginActEtEmail.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validatePassword() {
        String val = Objects.requireNonNull(binding.loginActEtPassword.getEditText()).getText().toString();

        if (val.isEmpty()) {
            binding.loginActEtPassword.setErrorEnabled(true);
            binding.loginActEtPassword.setError("Field cannot be empty");
            return false;
        } else {
            binding.loginActEtPassword.setError(null);
            binding.loginActEtPassword.setErrorEnabled(false);
            return true;
        }

    }

    //performing validation
    public void loginUser() {
        //hide keyboard
        keyin.hideKeyboard(Login.this, binding.loginActIvHealth);

        //checking internet connection from another class method
        if (!keyin.isConnected(Login.this)) {
            //displaying alert if no connection
            keyin.connectionAlert(Login.this);
        } else {
            //showing progressbar
            binding.loginPb.setVisibility(View.VISIBLE);
            //hiding lets go btn
            binding.loginActBtGo.setVisibility(View.GONE);
            if (!validateEmail() | !validatePassword()) {
                //hiding progressbar
                binding.loginPb.setVisibility(View.GONE);
                //showing lets go btn
                binding.loginActBtGo.setVisibility(View.VISIBLE);
            } else {

                signinUser();

            }
        }

    }

    //checking phone and password
    private void signinUser() {
        //getting user entered password and phone
        String userEnteredMail = Objects.requireNonNull(binding.loginActEtEmail.getEditText()).getText().toString().trim();
        String userEnteredPassword = Objects.requireNonNull(binding.loginActEtPassword.getEditText()).getText().toString().trim();

        mAuth.signInWithEmailAndPassword(userEnteredMail, userEnteredPassword).addOnCompleteListener(task -> {
            //if mail password is matches
            if (task.isSuccessful()) {

                    //hiding progressbar
                    binding.loginPb.setVisibility(View.GONE);
                    //showing lets go btn
                    binding.loginActBtGo.setVisibility(View.VISIBLE);
                //if email is verified
                if (checkIfEmailVerified()){

                    Intent intent = new Intent(Login.this,homeOptions.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
//                    finish();
                }//if not verified
                else{
                    Toast.makeText(getApplicationContext(), "Please verify your mail", Toast.LENGTH_SHORT).show();
                    mAuth.signOut();
                }

            }//if not matches
            else {
                Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                //hiding progressbar
                binding.loginPb.setVisibility(View.GONE);
                //showing lets go btn
                binding.loginActBtGo.setVisibility(View.VISIBLE);
            }
        });
    }

    //disables back btn
    @Override
    public void onBackPressed() {
    }
    //checking if the email is verified
    private boolean checkIfEmailVerified(){
        return Objects.requireNonNull(mAuth.getCurrentUser()).isEmailVerified();
    }
}