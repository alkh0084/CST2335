package com.example.myself.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends Activity {
    protected static final String ListItemsActivity = "ListItemsActivity";
    ImageButton imageButton;
    RadioButton radioButton;
    CheckBox checkBox;
    Switch switcher;

    static final int REQUEST_IMAGE_CAPTURE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ListItemsActivity", "In onCreate()");
        setContentView(R.layout.activity_list_items);
        imageButton = (ImageButton)findViewById(R.id.imageButton);
        switcher =(Switch)findViewById(R.id.switcher);
        checkBox = (CheckBox)findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
                builder.setMessage(R.string.dialog_message).setTitle(R.string.dialoge_title).
                        setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("Response", "Here is my response");
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();

                        // User clicked OK button
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                              // onResume(); // User cancelled the dialog
                                checkBox.setChecked(false);
                                dialog.cancel();

                            }
                        });


                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
                onActivityResult(50,50, takePictureIntent);

            }

        });
        switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // TODO Auto-generated method stub


            if(isChecked)
            {
                Toast.makeText(ListItemsActivity.this, "Switch is ON", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(ListItemsActivity.this, "Switch is OFF", Toast.LENGTH_SHORT).show();
            }
        }
    });

}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageButton.setImageBitmap(imageBitmap);
        }
    }
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
