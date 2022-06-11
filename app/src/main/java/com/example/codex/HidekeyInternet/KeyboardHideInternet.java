package com.example.codex.HidekeyInternet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class KeyboardHideInternet {

    //network connection
    public boolean isConnected(Context context){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to mobile data
                return true;
            }
        } else {
            // not connected to the internet
            return false;
        }
        return false;
    }

    //alert

    public void connectionAlert(Context context){
        // Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Set the message show for the Alert time
        builder.setMessage("Do you want to connect?");

        // Set Alert Title
        builder.setTitle("No Connection!");

        // Set Cancelable false
        // for when the user clicks on the outside
        // the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name
        // OnClickListener method is use of
        // DialogInterface interface.

        builder.setPositiveButton(
                        "Connect",
                (dialog, which) -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        context.startActivity(new Intent(Settings.ACTION_DATA_USAGE_SETTINGS));
                    }else{
                        context.startActivity(new Intent(Settings.ACTION_SETTINGS));
                    }
                });

        // Set the Negative button with No name
        // OnClickListener method is use
        // of DialogInterface interface.
        builder.setNegativeButton(
                        "Later",
                (dialog, which) -> {

                    // If user click no
                    // then dialog box is canceled.
                    dialog.cancel();
                });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();
    }

    //hide keyboard
    public void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(context);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
