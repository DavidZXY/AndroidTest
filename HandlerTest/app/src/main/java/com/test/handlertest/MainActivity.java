package com.test.handlertest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MyHandler().sendEmptyMessage(0);
    }

    public static class MyHandler extends Handler {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0:
                    Message message = obtainMessage();
                    message.what = 1;
                    message.obj = 2;
                    sendMessage(message);
                    break;
                case 1:
                    Log.d(TAG, "handleMessage: " + obtainMessage().obj);
                    break;
                case 2:
                    break;
            }
        }
    }
}
