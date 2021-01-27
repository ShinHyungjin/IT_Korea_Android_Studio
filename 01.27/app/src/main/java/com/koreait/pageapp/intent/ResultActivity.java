package com.koreait.pageapp.intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.koreait.pageapp.R;

public class ResultActivity extends AppCompatActivity {
    EditText t_id, t_pass, t_name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        t_id = findViewById(R.id.t_id);
        t_pass = findViewById(R.id.t_pass);
        t_name = findViewById(R.id.t_name);

        Intent intent = getIntent();
    }

    public void close(View view) {
        send();
    }
    public void send() {
        Intent intent = new Intent();

        Member member = new Member();
        member.setM_id(t_id.getText().toString());
        member.setM_pass(t_pass.getText().toString());
        member.setM_name(t_name.getText().toString());

        Bundle bundle = new Bundle();
        bundle.putParcelable("member", member);

        intent.putExtra("member", bundle);
        setResult(this.RESULT_OK, intent);
        finish();
    }
}
