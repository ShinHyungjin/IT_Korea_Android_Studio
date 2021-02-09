package com.koreait.actionbarapp;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    MyViewPagerAdapter myViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        myViewPagerAdapter = new MyViewPagerAdapter(this.getSupportFragmentManager(), 0);
        viewPager.setAdapter(myViewPagerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = this.getMenuInflater();
        menuInflater.inflate(R.menu.navi,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String msg = "";
        switch(item.getItemId()) {
            case android.R.id.home : msg="Home"; break;
            case R.id.mp3 : showPage(0); break;
            case R.id.chat : showPage(1); break;
            case R.id.gallery : showPage(2); break;
            case R.id.setting : showPage(3); break;
        }
        //Toast.makeText(this,"msg : " + msg, Toast.LENGTH_SHORT).show();
        return true;
    }

    public void showPage(int position) {
        viewPager.setCurrentItem(position,true);
    }
}