package com.test.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView mStringListView;
    private List<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            dataList.add("asdddwwwaassdaaadaw" + i);
        }
    }

    private void initView() {
        mStringListView = findViewById(R.id.activity_string_list_view);
        ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.item_activity_string_list, dataList);
        mStringListView.setAdapter(adapter);
        mStringListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
