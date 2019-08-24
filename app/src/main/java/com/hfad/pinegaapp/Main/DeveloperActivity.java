package com.hfad.pinegaapp.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.hfad.pinegaapp.R;

public class DeveloperActivity extends AppCompatActivity {


    public Toolbar toolbar_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        toolbar_main = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }
}
