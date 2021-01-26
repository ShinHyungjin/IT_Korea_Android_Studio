package com.koreait.webboardclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String TAG = this.getClass().getName();
    ListView listView;
    BoardAdapter boardAdapter;
    HttpManager httpManager;
    Thread thread;
    Handler handler;
    EditText t_title, t_writer, t_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Bundle bundle = msg.getData();
                ArrayList<Board> boardList = bundle.getParcelableArrayList("boardList");

                boardAdapter.list = boardList;
                boardAdapter.notifyDataSetChanged();
            }
        };

        boardAdapter = new BoardAdapter(this);
        httpManager = new HttpManager();

        t_title = findViewById(R.id.t_title);
        t_writer = findViewById(R.id.t_writer);
        t_content = findViewById(R.id.t_content);

        listView = this.findViewById(R.id.listView);
        listView.setAdapter(boardAdapter);
    }

    public void regist(View view) {
        thread = new Thread() {
            @Override
            public void run() {
                JSONObject json = new JSONObject();
                try {
                    json.put("title", t_title.getText());
                    json.put("writer", t_writer.getText());
                    json.put("content", t_content.getText());

                    Log.d(TAG,json.toString());

                    httpManager.requestByPost("http://192.168.35.161:7777/rest/board",json.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();
    }
    public void getList(View view) {
        thread = new Thread() {
            @Override
            public void run() {
                ArrayList<Board> boardList = httpManager.requestByGet("http://192.168.35.161:7777/rest/board");

                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("boardList", boardList);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        };
        thread.start();
    }
}