package com.koreait.websocketclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String TAG = this.getClass().getName();
    //웹소켓 객체 선언
    MyWebSocketClient myWebSocketClient;
    BoardDAO boardDAO;
    ListView listView;
    BoardAdapter boardAdapter;
    Handler handler;
    DetailDialog detailDialog;
    RegistDialog registDialog;
    Button bt_regist;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_regist = findViewById(R.id.bt_regist);
        listView = findViewById(R.id.listView);

        boardAdapter  = new BoardAdapter(this);
        listView.setAdapter(boardAdapter); //리스트뷰와 어댑터 연결!!!


        handler = new Handler(Looper.getMainLooper()){
            //쓰레드들의 부탁을 받아, 대신 UI제어!!
            public void handleMessage(@NonNull Message message) {
                Bundle bundle = message.getData();
                ArrayList boardList = (ArrayList)bundle.get("boardList");

                System.out.println("어댑터 호출");
                boardAdapter.boardList=boardList; //어댑터의 List값을 변경
                boardAdapter.notifyDataSetChanged();//어댑터 갱신...
                listView.invalidate();//리스트뷰 갱신
            }
        };

        boardDAO = new BoardDAO(this);
        createSocket();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "parent : " + adapterView);
                Log.d(TAG, "view : " + view);
                Log.d(TAG, "position : " + i);
                Log.d(TAG, "id : " + l);

                getDetail((int)l);
            }
        });
        getList();
    }

    //앱이 가동됨과 동시에 웹소켓서버와 접속 시도
    public void createSocket(){
        try {
            myWebSocketClient = new MyWebSocketClient(new URI("ws://192.168.35.161:9999"), this);
            myWebSocketClient.connect();//접속!!
            getList();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    //앱이 가동됨과 동시에 웹서버에서 목록 가져오기!!
    // 1.쓰레드+핸들러 ,  2.AsyncTask(Depreacted...)
    public void getList(){
        Thread thread = new Thread(){
            public void run() {
                boardDAO.selectAll();
            }
        };
        thread.start();
    }
    public void getDetail(int board_id) {
        detailDialog = new DetailDialog(this);
        detailDialog.show();

        for(Board board : boardAdapter.boardList) {
            if(board.getBoard_id() == board_id) {
                detailDialog.setData(board);
                break;
            }
        }
    }
    public void regist(View view) {
        registDialog = new RegistDialog(this);
        registDialog.show();

    }
}





