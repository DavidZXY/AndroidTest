package com.test.calltest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";
    private boolean isQuitOnRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.i(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i(TAG, "onResume: ");
        if (isQuitOnRunning) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setMessage("该视频录制过程已经中断，请重新录制")
                    .setCancelable(false)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            builder.create().show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.i(TAG, "onPause: ");
        isQuitOnRunning = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.i(TAG, "onDestroy: ");
    }
}
