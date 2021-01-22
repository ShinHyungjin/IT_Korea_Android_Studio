package com.study.app0122;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Telephony;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG = this.getClass().getName();
    ViewGroup wrapper;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wrapper = (ViewGroup) this.findViewById(R.id.wrapper);

        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                Bundle bundle = msg.getData();
                printData(bundle.getString("data"));
            }
        };

        /*
        LayoutInflater layoutInflater = this.getLayoutInflater();

        for (int i = 0; i < 5; i++) {
            ViewGroup root_wrapper = (ViewGroup) layoutInflater.inflate(R.layout.profile_item, wrapper);
            Log.d(TAG, "Activity의 root LinearLayout의 자식 수는 " + root_wrapper.getChildCount());

            ViewGroup profile_root = (ViewGroup) root_wrapper.getChildAt(i);
            Log.d(TAG, "profile xml의 root LinearLayout의 자식 수는 " + profile_root.getChildCount());

            ViewGroup text_root = (ViewGroup) profile_root.getChildAt(1);
            Log.d(TAG, "profile xml의 LinearLayout의 text 자식 수는 " + text_root.getChildCount());

            TextView t_id = (TextView) text_root.getChildAt(0);
            TextView t_pass = (TextView) text_root.getChildAt(1);
            TextView t_name = (TextView) text_root.getChildAt(2);

            Member member = new Member();
            member.setM_id("Batman " + i);
            member.setM_pass("1234*** " + i);
            member.setM_name("배트맨 " + i);

            t_id.setText(member.getM_id());
            t_pass.setText(member.getM_pass());
            t_name.setText(member.getM_name());

        }
        */
    }

    public void printData(String data) {
        LayoutInflater layoutInflater = this.getLayoutInflater();
        Log.d(TAG, "핸들러로부터 받은 데이터는 " + data);
        Member member = null;
        List<Member> memberList = new ArrayList<Member>();
        try {
            JSONArray jsonArray = new JSONArray(data);
            Log.d(TAG,"JsonArray는 " + jsonArray.length());
            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject json = (JSONObject)jsonArray.get(i);
                member = new Member();
                member.setM_id((String)json.get("m_id"));
                member.setM_pass((String)json.get("m_pass"));
                member.setM_name((String)json.get("m_name"));
                memberList.add(member);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < memberList.size(); i++) {
            member = memberList.get(i);
            ViewGroup root_wrapper = (ViewGroup) layoutInflater.inflate(R.layout.profile_item, wrapper);
            Log.d(TAG, "Activity의 root LinearLayout의 자식 수는 " + root_wrapper.getChildCount());

            ViewGroup profile_root = (ViewGroup) root_wrapper.getChildAt(i);
            Log.d(TAG, "profile xml의 root LinearLayout의 자식 수는 " + profile_root.getChildCount());

            ViewGroup text_root = (ViewGroup) profile_root.getChildAt(1);
            Log.d(TAG, "profile xml의 LinearLayout의 text 자식 수는 " + text_root.getChildCount());

            TextView t_id = (TextView) text_root.getChildAt(0);
            TextView t_pass = (TextView) text_root.getChildAt(1);
            TextView t_name = (TextView) text_root.getChildAt(2);

            t_id.setText(member.getM_id());
            t_pass.setText(member.getM_pass());
            t_name.setText(member.getM_name());

        }
    }
    public void loadData(View view) {
        ConnectManager connectManager = new ConnectManager(MainActivity.this,"http://192.168.35.161:8888/rest/member",null);
        connectManager.start();
    }
}