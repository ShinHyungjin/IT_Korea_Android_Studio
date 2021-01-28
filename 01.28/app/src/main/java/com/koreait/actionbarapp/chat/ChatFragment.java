package com.koreait.actionbarapp.chat;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.koreait.actionbarapp.R;

import java.io.IOException;
import java.net.Socket;

public class ChatFragment extends Fragment {
    Socket socket;
    EditText t_ip, t_port, t_input;
    TextView t_log;
    Thread thread;
    ChatThread chatThread;
    Button bt_send;
    Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        t_log = (TextView) view.findViewById(R.id.t_log);
        t_ip = (EditText) view.findViewById(R.id.t_ip);
        t_port = (EditText) view.findViewById(R.id.t_port);
        t_input = (EditText) view.findViewById(R.id.t_input);

        bt_send = (Button) view.findViewById(R.id.bt_send);
        Button bt_connect = (Button) view.findViewById(R.id.bt_connect);

        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message message) {
                Bundle bundle = message.getData();
                String msg = bundle.getString("msg");
                t_log.append(msg+"\n");
            }
        };

        bt_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectServer();
            }
        });
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });

        return view;
    }

    public void connectServer() {
        thread = new Thread() {
            @Override
            public void run() {
                try {
                    socket = new Socket(t_ip.getText().toString(), Integer.parseInt(t_port.getText().toString()));
                    chatThread = new ChatThread(socket, ChatFragment.this);
                    chatThread.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();
    }

    public void send() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                chatThread.send(t_input.getText().toString());
            }
        };
        thread.start();
    }
}
