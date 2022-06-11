package com.example.codex.Logins;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.codex.HidekeyInternet.KeyboardHideInternet;
import com.example.codex.databinding.ActivityForgotPasswordBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;


public class ForgotPassword extends AppCompatActivity {

   private ActivityForgotPasswordBinding binding;

   private KeyboardHideInternet keyin;

   private FirebaseAuth mAuth;
//   private FirebaseDatabase firebaseDatabase;
//   private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //view binding
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //instancing keyin
        keyin = new KeyboardHideInternet();

        //auth
        mAuth = FirebaseAuth.getInstance();
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("users");

        binding.forgotpassActBtSendlink.setOnClickListener(view -> {
            if (!validateEmail()){
                return;
            }else{
                sendLink();
            }
        });
    }



    //email validate
    private Boolean validateEmail() {
        String val = Objects.requireNonNull(binding.forgotpassActEtEmail.getEditText()).getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            binding.forgotpassActEtEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            binding.forgotpassActEtEmail.setError("Invalid Email address");
            return false;
        } else {
            binding.forgotpassActEtEmail.setError(null);
            binding.forgotpassActEtEmail.setErrorEnabled(false);
            return true;
        }

    }

    private void sendLink() {
        //hide keyboard
        keyin.hideKeyboard(ForgotPassword.this, binding.forgotpassActIvHealth);

        //checking internet connection from another class method
        if (!keyin.isConnected(ForgotPassword.this)){
            //displaying alert if no connection
            keyin.connectionAlert(ForgotPassword.this);
        }
        else{
            mAuth.sendPasswordResetEmail(Objects.requireNonNull(binding.forgotpassActEtEmail
                    .getEditText()).getText().toString()).addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Reset password sent to"+binding.forgotpassActEtEmail
                                    .getEditText().getText().toString(), Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(ForgotPassword.this,Login.class));

                        }
                        else {
                            Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    //disables back btn
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Login.class));
    }

}