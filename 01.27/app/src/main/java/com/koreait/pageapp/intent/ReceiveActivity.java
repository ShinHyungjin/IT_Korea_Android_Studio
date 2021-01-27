package com.koreait.pageapp.intent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.koreait.pageapp.R;

import java.util.ArrayList;

public class ReceiveActivity extends AppCompatActivity {
    String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("member");
        Member member = bundle.getParcelable("member");

        EditText t_id = findViewById(R.id.t_id);
        EditText t_pass = findViewById(R.id.t_pass);
        EditText t_name = findViewById(R.id.t_name);

        t_id.setText(member.getM_id());
        t_pass.setText(member.getM_pass());
        t_name.setText(member.getM_name());

     }
     public void close(View view) {
        this.finish();
     }
}