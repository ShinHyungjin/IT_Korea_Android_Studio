package com.koreait.webboardclient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BoardConverter<T> {
    ArrayList<T> list = null;

    public ArrayList<T> getConvertedData(String jsonString) {
        list = new ArrayList<T>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                Board board = new Board();

                board.setBoard_id(jsonObject.getInt("board_id"));
                board.setTitle(jsonObject.getString("title"));
                board.setWriter(jsonObject.getString("writer"));
                board.setContent(jsonObject.getString("content"));
                board.setRegdate(jsonObject.getString("regdate"));
                board.setHit(jsonObject.getInt("hit"));

                list.add((T)board);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}
