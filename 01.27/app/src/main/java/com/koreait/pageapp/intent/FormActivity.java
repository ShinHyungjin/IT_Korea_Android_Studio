package com.koreait.pageapp.intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.koreait.pageapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FormActivity extends AppCompatActivity implements View.OnClickListener{
    String TAG = this.getClass().getName();
    Button bt_send,bt_dial,bt_receive;
    Member member = null;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        bt_send = findViewById(R.id.bt_send);
        bt_dial = findViewById(R.id.bt_dial);
        bt_receive = findViewById(R.id.bt_receive);

        bt_send.setOnClickListener(this);
        bt_dial.setOnClickListener(this);
        bt_receive.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && requestCode == this.RESULT_OK) { //내가보낸 요청코드와, 상대의 응답코드가 일치 = 통신 성공
            Bundle bundle = data.getBundleExtra("member");
            Member member = bundle.getParcelable("member");

            EditText t_id = findViewById(R.id.t_id);
            EditText t_pass = findViewById(R.id.t_pass);
            EditText t_name = findViewById(R.id.t_name);

            t_id.setText(member.getM_id());
            t_pass.setText(member.getM_pass());
            t_name.setText(member.getM_name());
        }
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG,"나 누름?" + v.getId());
        if(v.getId() == R.id.bt_send) {
            send();
        } else if(v.getId() == R.id.bt_receive) {
            sendAndGet();
        }else if(v.getId() == R.id.bt_dial) {
            callPhone();
        }
    }

    public void send() {
        Intent intent = new Intent(FormActivity.this, ReceiveActivity.class);

        EditText t_id = findViewById(R.id.t_id);
        EditText t_pass = findViewById(R.id.t_pass);
        EditText t_name = findViewById(R.id.t_name);

        member = new Member();
        member.setM_id(t_id.getText().toString());
        member.setM_pass(t_pass.getText().toString());
        member.setM_name(t_name.getText().toString());

        Bundle bundle = new Bundle();
        bundle.putParcelable("member", member);

        intent.putExtra("member", bundle);
        startActivity(intent);
    }
    public void sendAndGet() {
        Intent intent = new Intent(FormActivity.this, ResultActivity.class);

        EditText t_id = findViewById(R.id.t_id);
        EditText t_pass = findViewById(R.id.t_pass);
        EditText t_name = findViewById(R.id.t_name);

        member = new Member();
        member.setM_id(t_id.getText().toString());
        member.setM_pass(t_pass.getText().toString());
        member.setM_name(t_name.getText().toString());

        Bundle bundle = new Bundle();
        bundle.putParcelable("member", member);

        intent.putExtra("member", bundle);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void callPhone() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01053009672"));
        startActivity(intent);
    }
}