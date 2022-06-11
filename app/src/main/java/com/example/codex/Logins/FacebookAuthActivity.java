package com.example.codex.Logins;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.codex.homeOptions;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.Objects;

public class FacebookAuthActivity extends Login {

    CallbackManager callbackManager;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Facebook Sign In...");
        progressDialog.show();


        callbackManager = CallbackManager.Factory.create();


        LoginManager.getInstance().logInWithReadPermissions(this,Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));

//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("phone"));


        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handleFacebookAccessToken(loginResult.getAccessToken());
                        progressDialog.dismiss();

                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());


                mAuth.signInWithCredential(credential)
                        .addOnCompleteListener(FacebookAuthActivity.this, task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                progressDialog.dismiss();
                                updateUI(Objects.requireNonNull(user));
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(FacebookAuthActivity.this, Objects.requireNonNull(task.getException()).getMessage(),
                                        Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                            }
                        }).addOnFailureListener(FacebookAuthActivity.this, e -> {
                    Toast.makeText(FacebookAuthActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                });
            }



    private void updateUI(FirebaseUser user) {

        Intent intent = new Intent(this, homeOptions.class);

        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {

                // Name, email address, and profile photo Url
                String name = profile.getDisplayName();
                String email = profile.getEmail();

                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();
                // UID specific to the provider
                String prov_uid = profile.getUid();

                //because we will receive 2 results one from firebase and another from fb or any provider
                if (providerId != "firebase"){
                    UserHelperClass helperClass = new UserHelperClass(name, email, providerId, prov_uid);

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
                    databaseReference.child(Objects.requireNonNull(mAuth.getUid())).setValue(helperClass);
                }

            }
        }

        startActivity(intent);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser firebaseUser = mAuth.getCurrentUser();
//        if (firebaseUser !=null){
//            updateUI(firebaseUser);
//        }
//    }
}