package com.example.app0121;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Spinner spinner;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        //listView = this.findViewById(R.id.listview);
        //spinner = this.findViewById(R.id.spinner);
        gridView = this.findViewById(R.id.gridview);

        String [] fruit = {"딸기","사과","배","멜론","바나나","포도","복숭아","수박","참외"};
        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,fruit);
        ArrayAdapter adapter2 = new ArrayAdapter(this, R.layout.fruit_item,fruit);


        //listView.setAdapter(adapter);
        //spinner.setAdapter(adapter);
        gridView.setAdapter(adapter2);

    }
}