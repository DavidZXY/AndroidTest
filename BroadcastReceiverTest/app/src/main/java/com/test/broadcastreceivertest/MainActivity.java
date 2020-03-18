package com.test.broadcastreceivertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProvider;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mSendBroadcastButton;
    Button mSendOrderBroadcastButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSendBroadcastButton = findViewById(R.id.send_broadcast_button);
        mSendOrderBroadcastButton = findViewById(R.id.send_order_broadcast_button);

        mSendBroadcastButton.setOnClickListener(this);
        mSendOrderBroadcastButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.send_broadcast_button:
                Intent intent = new Intent("com.test.broadcastreceivertest.MyBroadcastReceiver");
                intent.putExtra("msg", "send normal broadcast");
                sendBroadcast(intent);
                break;
            case R.id.send_order_broadcast_button:
                Intent intent1 = new Intent("com.test.broadcastreceivertest.MyBroadcastReceiver");
                intent1.putExtra("msg", "send order broadcast");
                sendOrderedBroadcast(intent1, null);
                break;
            default:
                break;
        }
    }
}
