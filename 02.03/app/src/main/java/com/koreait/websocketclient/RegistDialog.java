package com.koreait.websocketclient;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

public class RegistDialog extends Dialog {
    MainActivity mainActivity;
    Button bt_regist;
    EditText t_title,t_writer,t_content;

    public RegistDialog(@NonNull Context context) {
        super(context);
        this.mainActivity = (MainActivity)context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_regist);
        t_title = findViewById(R.id.t_title);
        t_writer = findViewById(R.id.t_writer);
        t_content = findViewById(R.id.t_content);

        bt_regist = findViewById(R.id.bt_regist);

        bt_regist.setOnClickListener(e-> {
            regist();
        });
    }
    public void regist() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Board board = new Board();
                board.setTitle(t_title.getText().toString());
                board.setWriter(t_writer.getText().toString());
                board.setContent(t_content.getText().toString());
                try {
                    mainActivity.boardDAO.insert(board);
                }catch (BoardUpdateException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }
}
