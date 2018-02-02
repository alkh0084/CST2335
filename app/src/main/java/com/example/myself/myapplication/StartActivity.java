package com.example.myself.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class StartActivity extends AppCompatActivity {
    protected static final String StartActivity = "StartActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("StartActivity", "In onCreate()");
        setContentView(R.layout.activity_start);
    }
    @Override
    protected void onResume(){
        super.onResume();

        Log.i("StartActivity", "In OnResume()");
        }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i("StartActivity", "In onStart()");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i("StartActivity", "In onPause()");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i("StartActivity", "In onStop()");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("StartActivity", "In onDestroy()");
    }


}
