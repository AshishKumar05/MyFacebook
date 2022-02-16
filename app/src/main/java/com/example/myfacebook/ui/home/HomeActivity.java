package com.example.myfacebook.ui.home;

import android.content.Intent;
import android.os.Bundle;

import com.example.myfacebook.R;
import com.example.myfacebook.databinding.ActivityHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding activityHomeBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(activityHomeBinding.getRoot());
        init();

       /* HomeActivityPagerAdapter homeActivityPagerAdapter = new HomeActivityPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(homeActivityPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager); */
    }

    private void init() {
        Intent intent = getIntent();
        HomeActivityPagerAdapter pagerAdapter = new HomeActivityPagerAdapter(this,getSupportFragmentManager());
        activityHomeBinding.pager.setAdapter(pagerAdapter);
        activityHomeBinding.tabLayout.setupWithViewPager(activityHomeBinding.pager);
    }
}