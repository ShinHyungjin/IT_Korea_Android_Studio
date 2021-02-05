package com.koreait.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.koreait.testapp.fragment.BlueFragment;
import com.koreait.testapp.fragment.RedFragment;
import com.koreait.testapp.fragment.YellowFragment;

public class MainActivity extends AppCompatActivity {
    Fragment[] fragments=new Fragment[3];
    FragmentManager fragmentManager=this.getSupportFragmentManager();
    int current=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        fragments[0] = new RedFragment();
        fragments[1] = new BlueFragment();
        fragments[2] = new YellowFragment();

        showView(2);
    }

    public void showPage(View view){
        int index=0;
        switch (view.getId()){
            case R.id.bt_red:index=0;break;
            case R.id.bt_blue:index=1;break;
            case R.id.bt_yellow:index=2;break;
        }
        showView(index);
    }
    public void showView(int index){
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        fragmentTransaction.remove(fragments[current]);
        fragmentTransaction.add(R.id.fragment_container, fragments[index]);
        fragmentTransaction.commit();
        current=index;
    }
}