package com.example.myself.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class ListItemsActivity extends Activity {
    protected static final String ListItemsActivity = "ListItemsActivity";

    @Override
    protected void onResume(){
        super.onResume();

        Log.i("ListItemsActivity", "In OnResume()");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i("ListItemsActivity", "In onStart()");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i("ListItemsActivity", "In onPause()");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i("ListItemsActivity", "In onStop()");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("ListItemsActivity", "In onDestroy()");
    }

}
