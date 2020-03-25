package com.test.roomtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        processExtraData();
    }


    private void processExtraData(){
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String userCode = bundle.getString("userCode");
            String businessNumber = bundle.getString("businessNumber");
            String userName = bundle.getString("userName");
            Toast.makeText(this, userName + " " + userCode + " " + businessNumber, Toast.LENGTH_SHORT).show();
            System.out.println(userName + " " + userCode + " " + businessNumber);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Room" + " " + getTaskId(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Toast.makeText(this, "Room onNewIntent", Toast.LENGTH_SHORT).show();
        processExtraData();
    }

}
