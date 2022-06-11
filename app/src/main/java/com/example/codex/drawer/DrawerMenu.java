package com.example.codex.drawer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.codex.R;

public class DrawerMenu{

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
    public void navigateMenu(MenuItem item, Context context){

        switch (item.getItemId()) {
            case R.id.nav_home1:
                Toast.makeText(context, "Home1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_home2:
                Toast.makeText(context, "Home2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_home3:
                Toast.makeText(context, "Home3", Toast.LENGTH_SHORT).show();
                break;


            case R.id.nav_login:
                Toast.makeText(context, "login", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_forgot:
                Toast.makeText(context, "forgot", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.nav_logout:
//                FirebaseAuth.getInstance().signOut();
//                //finishing received activity
//                callerActivity.finish();
//                break;


            case R.id.nav_cat1:
                Toast.makeText(context, "cat1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_cat2:
                Toast.makeText(context, "cat2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_cat3:
                Toast.makeText(context, "cat3", Toast.LENGTH_SHORT).show();
                break;


            case R.id.nav_share:
                Toast.makeText(context, "shared", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_rate_us:
                Toast.makeText(context, "rate", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
