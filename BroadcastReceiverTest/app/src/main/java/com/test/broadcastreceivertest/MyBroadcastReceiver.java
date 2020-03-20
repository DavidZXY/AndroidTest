package com.test.broadcastreceivertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "MyBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getExtras().get("msg").toString();
        setResultData(TAG + "发来的结果");
        Toast.makeText(context, message + "\n" + intent.getAction().toString(), Toast.LENGTH_SHORT).show();
        abortBroadcast();
    }
}
