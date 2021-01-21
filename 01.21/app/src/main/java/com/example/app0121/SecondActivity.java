package com.example.app0121;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        Button button = new Button(this);
        button.setText("나 버튼");

        setContentView(button);
        */
        setContentView(R.layout.custom);

    }
}