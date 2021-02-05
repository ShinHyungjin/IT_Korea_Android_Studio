package com.koreait.testapp.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.koreait.testapp.R;


public class PagerActivity extends AppCompatActivity {
    ViewPager viewPager;
    MyViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        adapter = new MyViewPagerAdapter(this.getSupportFragmentManager(), 0); // 초기엔 빨간색

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
    }

    public void showPage(View view) {
        switch(view.getId()) {
            case R.id.bt_red:flowPage(0);break;
            case R.id.bt_blue:flowPage(1);break;
            case R.id.bt_yellow:flowPage(2);break;
        }
    }
    public void flowPage(int index) {
        viewPager.setCurrentItem(index,true);
    }

}