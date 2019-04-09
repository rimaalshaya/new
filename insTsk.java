package com.example.lockerapp;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class insTsk extends AppCompatActivity {

    private EditText etPhone , etEmail , etCredit ;
    private MyDBHandler dbHandler;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ins_tsk);


    }

    public void addButtonClicked(View v){


        String phoneStr = etPhone.getText().toString();
        String emailStr = etEmail.getText().toString();
        String creditStr = etCredit.getText().toString();

        final int min = 10;
        final int max = 100;
        final int random = new Random().nextInt((max - min) + 1) + min;


        if (phoneStr.isEmpty() || emailStr.isEmpty()|| creditStr.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "PLease Fill missing data ", Toast.LENGTH_LONG).show();
            return;
        }


        db.execSQL("insert into "+ dbHandler.TABLE_NAME + "("+ dbHandler.COLUMN_PHONE + "," + dbHandler.COLUMN_CREDIT + "," + dbHandler.COLUMN_EMAIL + ") VALUES (?,?,?)", new String [] {phoneStr, emailStr, creditStr});


        String tstMsg ="Your account is inserted , " + " Your Locker number is " + random;
        Toast.makeText(getApplicationContext(), tstMsg, Toast.LENGTH_LONG).show();
        Intent i = new Intent (getApplicationContext(), home.class);
        startActivity(i);
        dbHandler.close();
        finish();

        etPhone.setText("");
        etCredit.setText("");
        etEmail.setText("");


    }


}