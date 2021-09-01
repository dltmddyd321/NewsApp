package com.example.news_kor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem home, science, health, tech, enter, sports;
    PagerAdapter pagerAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        //toolbar를 Activity의 App Bar로 설정
        setSupportActionBar(toolbar);

        home = findViewById(R.id.home);
        science = findViewById(R.id.science);
        health = findViewById(R.id.health);
        tech = findViewById(R.id.technology);
        enter = findViewById(R.id.entertainment);
        sports = findViewById(R.id.sports);

        ViewPager2 viewPager2 = findViewById(R.id.fragmentContainer);
        tabLayout = findViewById(R.id.newsMenu);
    }
}