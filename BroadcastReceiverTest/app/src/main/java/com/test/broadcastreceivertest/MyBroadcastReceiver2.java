package com.test.broadcastreceivertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getExtras().get("msg").toString();
        String resultData = getResultData();
        Toast.makeText(context, message + "\n" + resultData, Toast.LENGTH_SHORT).show();
    }
}
