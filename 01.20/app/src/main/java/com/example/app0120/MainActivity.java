package com.example.app0120;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private String TAG = this.getClass().getName();
    WebConnector webConnector = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);

    }
    public void regist(View view){
        Log.d("해당 메소드를 호출하는 클래스는" + TAG,"안녕하쇼");
        webConnector = new WebConnector();
        webConnector.start();

    }
}