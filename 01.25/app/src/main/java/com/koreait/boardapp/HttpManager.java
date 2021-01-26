package com.koreait.boardapp;
/*
자바도  html문서처럼, 웹서버와 http통신이 가능하다...
*/

import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpManager {
    String TAG=this.getClass().getName();
    URL url;
    HttpURLConnection con; //http통신을 위한 객체(헤더+바디를 구성하여 서버와 데이터를 주고받는
    // stateless 한 통신)
    Handler handler;
    MainActivity mainActivity;
    ArrayList<Board> boardList;

    //이 객체를 생성하는 者는 주소와 제이슨 데이터를넘겨야 한다
    public HttpManager(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.d(TAG,"메인 가기 전");
                Bundle bundle = msg.getData();
                List<Board> boardList2 = (List)bundle.getSerializable("boardList");

                mainActivity.adapter.data = boardList2;
                mainActivity.adapter.notifyDataSetChanged();
            }
        };

    }

    public int requestByGet(String requestUrl){ //Get방식으로 요청을 시도하는 메서드
        BufferedReader buffr=null;
        int code=0;
        try{
            url = new URL(requestUrl);//요청 주소
            con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            Log.d(TAG,"접속성공!");

            //서버로부터 응답 데이터 가져오기
            buffr=new BufferedReader(new InputStreamReader(con.getInputStream())); //바이트 기반 기반 스트림

            StringBuilder sb = new StringBuilder(); //문자열을 누적할 객체
            String data=null;
            while(true){
                data=buffr.readLine(); //한줄 읽어들인다...
                if(data==null)break; //읽어들일 데이터가 없다면 무한루프 종료
                sb.append(data);//읽어들인 문자열을 누적시키자
            }
            Log.d(TAG,"서버가 보낸 응답데이터는 : "+sb.toString());

            code=con.getResponseCode(); //서버로부터 받은 응답코드 반환 ( 이 시점에 이미 서버에 요청을 완료 후 응답도 받은 상태)
            Log.d(TAG,"서버로부터 받은 응답코드는 "+code);
            boardList = new ArrayList<Board>();

            try {
                JSONArray jsonArray = new JSONArray(sb.toString());
                for(int i=0; i<jsonArray.length(); i++) {
                    JSONObject json = (JSONObject) jsonArray.get(i);
                    Board board = new Board();
                    board.setBoard_id(json.getInt("board_id"));
                    board.setTitle(json.getString("title"));
                    board.setWriter(json.getString("writer"));
                    board.setContent(json.getString("content"));
                    board.setHit(json.getInt("hit"));
                    boardList.add(board);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Message message = new Message();
            Bundle bundle = new Bundle();
            //bundle.putParcelable("boardList",(Parcelable)boardList);
            bundle.putSerializable("boardList", boardList);
            message.setData(bundle);
            handler.sendMessage(message);

        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(buffr!=null){
                try{
                    buffr.close();
                }catch(IOException e){
                }
            }
        }
        return code;
    }

    //Post방식의 요청을 시도하되, JSON데이터를 전송하겠다!!!
    public int requestByPost(String requestUrl, String data){
        BufferedWriter buffw=null; //버퍼처리된 문자기반 스트림
        int code=0; //서버의 응답 코드

        try{
            url = new URL(requestUrl);//요청 주소
            con=(HttpURLConnection)url.openConnection();
            //데이터형식을 헤더에 첨가해줘야, 서버측에서 제이슨데이터가 전송되어 온것임을 안다..이게 바로 HTTP프로토콜간의 약속이다
            con.setRequestProperty("Content-Type","application/json;charset=utf-8");
            con.setRequestMethod("POST");
            con.setDoOutput(true);//서버에 데이터를 출력하기 위해 필요한 옵션!!
            //요청을 떠나기 전에, 준비할게 잇다면 여기서 준비하자!!, json 문자열을 준비하자!!
            //JSON 객체 자체가 아닌 문자열로 준비하는 이유는?
			/*
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			sb.append("\"m_id\":\"batman\",");
			sb.append("\"m_pass\":\"1234\",");
			sb.append("\"m_name\":\"배트맨\"");
			sb.append("}");
			*/

            //실행중인 프로그램에서 서버로 데이터를 보내야 하므로, 출력스트림으로 처리하자!!
            buffw=new BufferedWriter(new OutputStreamWriter(con.getOutputStream(),"UTF-8")); //한글 인코딩 처리해야 함
            buffw.write(data);
            buffw.flush();

            code=con.getResponseCode(); //요청 + 응답이 발생
            System.out.println("서버로 부터 받은 응답 코드는 "+code);

        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(buffw!=null){
                try{
                    buffw.close();
                }catch(IOException e){
                }
            }
        }
        return code; //응답코드 반환
    }

}