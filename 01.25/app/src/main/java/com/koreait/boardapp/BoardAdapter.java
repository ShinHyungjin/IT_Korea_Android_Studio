package com.koreait.boardapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BoardAdapter extends BaseAdapter {
    String TAG = this.getClass().getName();
    List<Board> data = new ArrayList<Board>();
    MainActivity mainActivity;
    View view;
    LayoutInflater layoutInflater;

    public BoardAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
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

            Log.d(TAG, "data "+position+"번째 : " + data.get(position).getBoard_id());
            Log.d(TAG,"parentView : " + parentView);

            view = parentView;

        } else {
            view = convertView;
        }
        ViewGroup linear = (ViewGroup)view;
        ViewGroup linear2 = (ViewGroup)linear.getChildAt(1);

        TextView t_title = (TextView) linear2.getChildAt(0);
        TextView t_writer = (TextView) linear2.getChildAt(1);

        t_title.setText(data.get(position).getTitle());
        t_writer.setText(data.get(position).getWriter());

        Log.d(TAG, position + " : " + convertView);
        //Log.d(TAG,position+"번째 아이템 " + view);
        return view;
    }
}
