package com.example.myfacebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myfacebook.databinding.ActivityMainBinding;
import com.example.myfacebook.ui.home.HomeActivity;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}