package com.example.codex.drawer;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.codex.Logins.ForgotPassword;
import com.example.codex.MainActivity;
import com.example.codex.R;
import com.example.codex.category.clang;
import com.example.codex.compiler.compiler;
import com.example.codex.homeOptions;
import com.example.codex.java_list_select.java_one;
import com.example.codex.quiz.dashboardactivity;
import com.google.firebase.auth.FirebaseAuth;

public class DrawerMenu {

    //animating drawer var
    static final float END_SCALE = 0.7f;


    //animating the drawer
    public void animateNavigationDrawer(DrawerLayout drawerLayout, View contentView, View menuTrigger) {

        //binding.drawerLayout.setScrimColor(getResources().getColor(R.color.back_ground));

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                //scale the view based on current slide offset
                final float diffScaleOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaleOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                //Translate the view, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaleOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                menuTrigger.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                menuTrigger.setVisibility(View.VISIBLE);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    public void navigateMenu(MenuItem item, Context context) {

        switch (item.getItemId()) {

            case R.id.javacomiler:

                break;

            case R.id.nav_forgot:

                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                //finishing received activity
                break;


        }
    }

}
