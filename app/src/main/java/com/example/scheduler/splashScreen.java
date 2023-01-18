package com.example.scheduler;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class splashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent intent=new Intent(splashScreen.this,MainActivity.class);
               startActivity(intent);
               finish();
           }
       },2000);


    }
}
