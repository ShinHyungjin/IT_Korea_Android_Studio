package com.koreait.webboardclient;

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
    List<Board> list = new ArrayList<Board>();
    LayoutInflater layoutInflater;
    MainActivity mainActivity;
    View view;

    public BoardAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        layoutInflater = this.mainActivity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView != null) {
            view = convertView;
        }else {
            view = layoutInflater.inflate(R.layout.board_item,parent,false);
            /*
            ViewGroup viewGroup = (ViewGroup)view;
            ViewGroup viewGroup1 = (ViewGroup)viewGroup.getChildAt(1);
            TextView t_title = (TextView)viewGroup1.getChildAt(0);
            TextView t_writer = (TextView)viewGroup1.getChildAt(1);

            t_title.setText(list.get(position).getTitle());
            t_writer.setText(list.get(position).getWriter());
             */
        }
        Board board = list.get(position);
        TextView tv_title = view.findViewById(R.id.item_title);
        TextView tv_writer = view.findViewById(R.id.item_writer);
        tv_title.setText(board.getTitle() + "(조회:"+board.getHit()+")");
        tv_writer.setText(board.getWriter()+"("+board.getRegdate()+")");
        return view;
    }
}
