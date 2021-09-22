package com.example.news_kor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem home, science, health, tech, enter, sports;
    PagerAdapter pagerAdapter;
    Toolbar toolbar;
    ImageButton virusBtn;

    //News API에서 가져온 API key 등록
    String newsApi = "bef70722d1874f65a72dcb962491fc8c";

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
        virusBtn = findViewById(R.id.virusBtn);

        virusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CovidTracker.class);
                startActivity(intent);
            }
        });

        //구현한 View가 출력될 컨테이너 및 탭 레이아웃 연결
        ViewPager viewPager = findViewById(R.id.fragmentContainer);
        tabLayout = findViewById(R.id.newsMenu);

        //Adapter 통해 6개의 프래그먼트 가져오도록 설정
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), 6);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //선택된 탭 위치 값에 해당하는 항목 가져오기
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0 || tab.getPosition() == 1 || tab.getPosition() == 3 || tab.getPosition() == 4 || tab.getPosition() == 5) {
                    //tab의 위치 값에 따라 pagerAdapter가 갱신
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}