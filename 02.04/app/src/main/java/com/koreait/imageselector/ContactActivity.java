package com.koreait.imageselector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

public class ContactActivity extends AppCompatActivity {
    String TAG = this.getClass().getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


    }
    public void openContact(View view) {
        Cursor cursor = this.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,null,null,null);

        while(cursor.moveToNext()) {
            Log.d(TAG, "전화번호는 " + cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }
    }
}