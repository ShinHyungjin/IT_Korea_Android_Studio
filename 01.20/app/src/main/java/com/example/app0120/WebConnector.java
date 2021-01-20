package com.example.app0120;

import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebConnector extends Thread{
    URL url;
    HttpURLConnection con;
    String TAG = this.getClass().getName();

    public void getData() {
        try {
            url = new URL("http://192.168.35.161:8888/rest/member"); // 노트북 또는 pc의 ip주소
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");

            int code = con.getResponseCode();
            Log.d(TAG, "서버로 부터 받은 http 응답 코드 : " + code);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        getData();
    }
}
