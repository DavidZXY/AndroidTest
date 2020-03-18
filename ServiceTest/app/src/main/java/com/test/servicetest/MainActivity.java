package com.test.servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Button mStartServiceButton;
    private Button mStopServiceButton;
    private Button mBindServiceButton;
    private Button mUnBindServiceButton;

    private MyAIDLService mAIDLService;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mAIDLService = MyAIDLService.Stub.asInterface(service);

            try {
                int result = mAIDLService.plus(3, 5);
                String str = mAIDLService.toUpperCase("hello world");
                Log.d(TAG, "onServiceConnected: result is " + result);
                Log.d(TAG, "onServiceConnected: upper string is " + str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "onServiceConnected: " + name.toString());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: " + name.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Thread ID = " + Thread.currentThread().getId());
        Log.d(TAG, "onCreate: Process ID = " + Process.myPid());
        mStartServiceButton = findViewById(R.id.start_service_button);
        mStopServiceButton = findViewById(R.id.stop_service_button);
        mBindServiceButton = findViewById(R.id.bind_service_button);
        mUnBindServiceButton = findViewById(R.id.unbind_service_button);
        mStartServiceButton.setOnClickListener(this);
        mStopServiceButton.setOnClickListener(this);
        mBindServiceButton.setOnClickListener(this);
        mUnBindServiceButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service_button:
                startService(new Intent(this, MyService.class));
                break;
            case R.id.stop_service_button:
                stopService(new Intent(this, MyService.class));
                break;
            case R.id.bind_service_button:
                bindService(new Intent(this, MyService.class), connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service_button:
                unbindService(connection);
                break;
            default:
                break;
        }
    }
}
