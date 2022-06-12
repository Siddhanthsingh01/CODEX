package com.example.codex.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.codex.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class quiz extends AppCompatActivity {

    public static ArrayList<modelclass> list;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        list=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("Question");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    modelclass modelclass=dataSnapshot.getValue(modelclass.class);
                    list.add(modelclass);
                }

                Intent intent=new Intent(quiz.this, dashboardactivity.class);
                startActivity(intent);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        list.add(new modelclass("what is your name","A","B","C","D","B"));
//        list.add(new modelclass("what is your qulification","A","B","C","D","D"));
//        list.add(new modelclass("what is your age","A","B","C","D","A"));
//        list.add(new modelclass("what is your number","A","B","C","D","C"));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

//                Intent intent=new Intent(quiz.this, dashboardactivity.class);
//                startActivity(intent);
            }
        },1500);


    }
}