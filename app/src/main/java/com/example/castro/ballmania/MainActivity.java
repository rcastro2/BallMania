package com.example.castro.ballmania;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private SaverView myScreenSaver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myScreenSaver = new SaverView(this);
        setContentView(myScreenSaver);
        myScreenSaver.setBackgroundColor(Color.WHITE);
    }
}
