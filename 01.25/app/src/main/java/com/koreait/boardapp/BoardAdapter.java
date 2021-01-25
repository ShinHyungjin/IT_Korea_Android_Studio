package com.koreait.boardapp;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class BoardAdapter extends BaseAdapter {
    String TAG = this.getClass().getName();
    List<String> data = new ArrayList<String>();
    MainActivity mainActivity;
    View view;
    LayoutInflater layoutInflater;

    public BoardAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        data.add("사과");
        data.add("바나나");
        data.add("딸기");
        data.add("수박");
        data.add("파인애플");
        data.add("멜론");
        data.add("오렌지");
        data.add("귤");
        data.add("키위");
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //getView는 getCount 만큼 호출된다. 리스트뷰에 각 칸을 차지하게 될 View를 반환한다

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            layoutInflater = mainActivity.getLayoutInflater();
            View parentView = layoutInflater.inflate(R.layout.board_item, parent, false);

            Log.d(TAG,"parentView : " + parentView);
            view = parentView;
        } else {
            view = convertView;
        }

        //Log.d(TAG, position + " : " + convertView);
        //Log.d(TAG,position+"번째 아이템 " + view);
        return view;
    }
}
