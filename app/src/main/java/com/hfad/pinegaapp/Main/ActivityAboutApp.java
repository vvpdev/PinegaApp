package com.hfad.pinegaapp.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.hfad.pinegaapp.R;

public class ActivityAboutApp extends AppCompatActivity {



    // тулбар
    public Toolbar toolbar_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);


        // Находим тулбар
        toolbar_main = (Toolbar) findViewById(R.id.toolbar);

        // добавляем поддержку ActionBar
        setSupportActionBar(toolbar_main);

        // кнопка назад в тулбаре
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




    }
}
