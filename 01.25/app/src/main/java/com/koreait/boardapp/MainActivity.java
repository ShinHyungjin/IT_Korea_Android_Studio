package com.koreait.boardapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    HttpManager httpManager;
    Handler handler;
    ListView listView;
    BoardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        httpManager = new HttpManager(this);

        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                Bundle bundle = msg.getData();
                //printData(bundle.getString("data"));
            }
        };


        listView = this.findViewById(R.id.listView);

        adapter = new BoardAdapter(this);
        listView.setAdapter(adapter);

        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        //listView.setAdapter(adapter);

    }
    public void regist(View view) {

    }
    public void getList(View view) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                httpManager.requestByGet("http://192.168.35.161:8888/rest/board");
            }
        };
        thread.start();
    }
}