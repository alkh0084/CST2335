package com.example.myself.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
    protected static final String LoginActivity = "LoginActivity";
    EditText editText;
    Button button;
    @Override
    protected void onResume(){
        super.onResume();
        Log.i("LoginActivity", "In OnResume()");
        editText=(EditText) findViewById(R.id.emailLogin);
        button = (Button)findViewById(R.id.button);
        saveInfo();
        Intent intent=new Intent(LoginActivity.this, StartActivity.class);
        startActivity(intent);

    }
    public void saveInfo(){
        SharedPreferences sharedPreferences = getSharedPreferences("usersEmail", Context.MODE_PRIVATE);
        sharedPreferences.getString("DefaultEmail","email@domain.com");
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("Username",editText.getText().toString());
        editor.apply();
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i("LoginActivity", "In onStart()");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i("LoginActivity", "In onPause()");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i("LoginActivity", "In onStop()");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("LoginActivity", "In onDestroy()");
    }


}
