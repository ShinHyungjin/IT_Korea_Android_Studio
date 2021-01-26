package com.koreait.webboardclient;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HttpManager {
    String TAG = this.getClass().getName();
    HttpURLConnection con;
    URL url;
    BoardConverter<Board> boardConverter;


    StringBuilder sb = new StringBuilder();

    int code = 0;

    public HttpManager() {
        boardConverter = new BoardConverter<Board>();
    }

    public ArrayList<Board> requestByGet(String requestUrl) {
        BufferedReader br = null;
        ArrayList<Board> boardList = new ArrayList<Board>();
        try {
            url = new URL(requestUrl);
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));

            String data = null;
            while(true) {
                data = br.readLine();
                if(data == null) {
                    break;
                }
                sb.append(data);
            }

            Log.d(TAG, "서버로부터의 응답 데이터는 " + sb.toString());
            boardList = boardConverter.getConvertedData(sb.toString());
            Log.d(TAG, "컨버트 된 리스트의 사이즈는 " + boardList.size());

            code = con.getResponseCode();

            Log.d(TAG,"서버로부터의 응답코드 " + code);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return boardList;
    }

    public void requestByPost(String requestUrl, String param) {
        BufferedWriter bw = null;
        try {
            url = new URL(requestUrl);
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","application/json;charset=utf-8");
            con.setDoOutput(true);

            bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(),"UTF-8"));
            bw.write(param);
            bw.flush();

            code = con.getResponseCode();

            Log.d(TAG,"서버로부터의 응답코드 " + code);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}