package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private Button generateWeatherData;
    private EditText zipcodeField;
    public static  ListView list;
    private String zip = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        list = findViewById(R.id.list_id);
        generateWeatherData = findViewById(R.id.button);
        zipcodeField = findViewById(R.id.editTextTextPersonName2);
        zip = zipcodeField.getText().toString();
        zipcodeField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 5){
                    zip = s.toString();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        generateWeatherData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotSynchronous asyncTask = new NotSynchronous();
                if(zip == null) {
                    (Toast.makeText(getApplicationContext(), "Please enter a valid zip code", Toast.LENGTH_LONG)).show();
                }
                else if(asyncTask.getStatus()!= AsyncTask.Status.RUNNING)
                    asyncTask.execute(zip);

            }
        });

    }
}