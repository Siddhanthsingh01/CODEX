package com.example.codex.Logins;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codex.HidekeyInternet.KeyboardHideInternet;
import com.example.codex.databinding.ActivitySignUpBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class SignUp extends AppCompatActivity {
    private ActivitySignUpBinding binding;

    //firebase realtime db
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    //object of reusable class
    KeyboardHideInternet keyin = new KeyboardHideInternet();

    //firebase auth
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting full screen hiding status
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //view binding
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //instancing mAuth
        mAuth = FirebaseAuth.getInstance();

        //back to login calling startLogin
        binding.signupActBtExistinguser.setOnClickListener(view -> startLogin());

        //hiding progress bar
        binding.signupActPb.setVisibility(View.GONE);

        //text watcher
        binding.compPass2.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Objects.requireNonNull(binding.compPass1.getText()).toString().equals(Objects.requireNonNull(binding.compPass2.getText()).toString())) {
                    binding.signupEtConfirmpassword.setError("");
                } else {
                    binding.signupEtConfirmpassword.setError("Password does not match");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //on click signup btn
        binding.signupActBtGo.setOnClickListener(view -> registerUser());

        //referencing & instancing realtime db
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");


    }

    //starting login with animation
    private void startLogin() {
        Intent intent = new Intent(SignUp.this, Login.class);
        //animation
//        Pair<View, String>[] pairs = new  Pair[7];

        Pair<View, String> p1 = Pair.create(binding.signupActIvHealth, "logo_image");
        Pair<View, String> p2 = Pair.create(binding.signupActTvSloganWelcome, "logo_text");
        Pair<View, String> p3 = Pair.create(binding.signupActTvMsg, "logo_desc");
        Pair<View, String> p4 = Pair.create(binding.signupEtEmail, "email_tran");
        Pair<View, String> p5 = Pair.create(binding.signupEtPassword, "password_tran");
        Pair<View, String> p6 = Pair.create(binding.signupActBtGo, "button_tran");
        Pair<View, String> p7 = Pair.create(binding.signupActBtExistinguser, "login_signup_tran");

//        pairs[0] =  Pair.create(binding.signupActIvHealth, "logo_image");
//        pairs[1] =  Pair.create(binding.signupActTvSloganWelcome, "logo_text");
//        pairs[2] =  Pair.create(binding.signupActTvMsg, "logo_desc");
//        pairs[3] =  Pair.create(binding.signupEtEmail, "email_tran");
//        pairs[4] =  Pair.create(binding.signupEtPassword, "password_tran");
//        pairs[5] =  Pair.create(binding.signupActBtGo, "button_tran");
//        pairs[6] =  Pair.create(binding.signupActBtExistinguser, "login_signup_tran");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, p1,p2,p3,p4,p5,p6,p7);

        finish();

        startActivity(intent, options.toBundle());
//        finish();
    }

    //validating fields
    private Boolean validateName() {
        String val = Objects.requireNonNull(binding.signupEtName.getEditText()).getText().toString();

        if (val.isEmpty()) {
            binding.signupEtName.setErrorEnabled(true);
            binding.signupEtName.setError("Field cannot be empty");
            return false;
        } else {
            binding.signupEtName.setError(null);
            binding.signupEtName.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validateEmail() {
        String val = Objects.requireNonNull(binding.signupEtEmail.getEditText()).getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            binding.signupEtEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            binding.signupEtEmail.setError("Invalid Email address");
            return false;
        } else {
            binding.signupEtEmail.setError(null);
            binding.signupEtEmail.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validatePhone() {
        String val = Objects.requireNonNull(binding.signupEtPhone.getEditText()).getText().toString();

        if (val.isEmpty()) {
            binding.signupEtPhone.setError("Field cannot be empty");
            return false;
        } else {
            binding.signupEtPhone.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = Objects.requireNonNull(binding.signupEtPassword.getEditText()).getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])"+ //at least 1 digit
                //"(?=.*[a-z])"+ //at least 1 lower case letter
                //"(?=.*[A-Z])"+ //at least 1 upper case letter
                "(?=.*[a-zA-Z])" + //any letter
                "(?=.*[@#$%^&+=])" + //at least 1 special char
                "(?=\\S+$)" + //no white spaces
                ".{6,}" + //at least 6 chars
                "$";

        if (val.isEmpty()) {
            binding.signupEtPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            binding.signupEtPassword.setError("Password is too weak");
            return false;
        } else {
            binding.signupEtPassword.setError(null);
            return true;
        }

    }

    private Boolean confirmPassword() {
        if (Objects.requireNonNull(binding.compPass1.getText()).toString().equals(Objects.requireNonNull(binding.compPass2.getText()).toString())) {
            Objects.requireNonNull(binding.signupEtConfirmpassword).setError("");
            return true;
        }else{
            binding.signupEtConfirmpassword.setError("Password does not match");
            return false;
        }
    }


    public void registerUser() {

        //hide keyboard
        keyin.hideKeyboard(SignUp.this, binding.signupActIvHealth);

        //checking internet connection from another class method
        if (!keyin.isConnected(SignUp.this)) {
            //displaying alert if no connection
            keyin.connectionAlert(SignUp.this);
        } else {

            if (!validateName() | !validateEmail() | !validatePhone() | !validatePassword() | !confirmPassword()) {
                return;
            }

            //progressive bar visible
            binding.signupActPb.setVisibility(View.VISIBLE);
            //go btn disable
            binding.signupActBtGo.setVisibility(View.GONE);

            String name = Objects.requireNonNull(binding.signupEtName.getEditText()).getText().toString();
            String email = Objects.requireNonNull(binding.signupEtEmail.getEditText()).getText().toString();
            String countryCode = binding.signupCcp.getSelectedCountryCodeWithPlus();
            String phone = countryCode + Objects.requireNonNull(binding.signupEtPhone.getEditText()).getText().toString();
            String password = Objects.requireNonNull(binding.signupEtPassword.getEditText()).getText().toString();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    assert user != null;
                    user.sendEmailVerification().addOnCompleteListener(task1 -> {

                        if (task1.isSuccessful()) {
                            //progressive bar gone
                            binding.signupActPb.setVisibility(View.GONE);
                            //go btn visible
                            binding.signupActBtGo.setVisibility(View.VISIBLE);

                            //setting values to helper class
                            UserHelperClass helperClass = new UserHelperClass(name, email, phone);

                            //settings key inside users using mAuth and setting key value the created class
                            reference.child(Objects.requireNonNull(mAuth.getUid())).setValue(helperClass);
                            Toast.makeText(getApplicationContext(), "Mail verification link has been sent to your mail", Toast.LENGTH_SHORT).show();

                            //account created now sign out and go to login activity
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(this, Login.class));

                        } else {
                            Toast.makeText(getApplicationContext(), Objects.requireNonNull(task1.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    });

                } else {
                    Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    //progressive bar gone
                    binding.signupActPb.setVisibility(View.GONE);
                    //go btn visible
                    binding.signupActBtGo.setVisibility(View.VISIBLE);
                }
            });
        }
    }


    //disables back btn
    @Override
    public void onBackPressed() {

    }
}