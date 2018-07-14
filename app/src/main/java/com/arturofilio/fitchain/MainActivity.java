package com.arturofilio.fitchain;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.arturofilio.fitchain.Utils.UniversalImageLoader;
import com.arturofilio.fitchain.models.Exercise;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");

        initImageLoader();

        init();
    }

    /**
     * initialize the first fragment (ViewExerciseFragment)
     */
    private void init() {
        ViewExercisesFragment fragment = new ViewExercisesFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void initImageLoader() {
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(MainActivity.this);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }

}
