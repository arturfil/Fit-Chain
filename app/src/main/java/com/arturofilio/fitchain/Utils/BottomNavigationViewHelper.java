package com.arturofilio.fitchain.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.arturofilio.fitchain.MainActivity;
import com.arturofilio.fitchain.ProfileActivity;
import com.arturofilio.fitchain.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx) {
        Log.d(TAG, "setupBottomNavigationView: setting bottom menu bar");

    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx view) {
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.ic_house:
                        Intent intent1 = new Intent(context, MainActivity.class);
                        context.startActivity(intent1);
                        break;

                    case R.id.ic_alert:
                        Intent intent2 = new Intent(context, MainActivity.class);
                        context.startActivity(intent2);
                        break;

                    case R.id.ic_profile:
                        Intent intent3 = new Intent(context, ProfileActivity.class);
                        context.startActivity(intent3);
                        break;
                }


                return false;
            }
        });
    }

}
