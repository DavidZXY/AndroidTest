package com.test.serviceclienttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.test.servicetest.MyAIDLService;

public class MainActivity extends AppCompatActivity {

    private MyAIDLService mAIDLService;

    private static final String TAG = "MainActivity";
    private Button mBindService;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mAIDLService = MyAIDLService.Stub.asInterface(service);
            try {
                int result = mAIDLService.plus(50, 50);
                String str = mAIDLService.toUpperCase("client test");
                Log.d(TAG, "onServiceConnected: result is " + result);
                Log.d(TAG, "onServiceConnected: upper string is " + str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBindService = findViewById(R.id.bind_service);
        mBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("MyAIDLService");
                intent.setPackage("com.test.servicetest");
                bindService(intent, mConnection, BIND_AUTO_CREATE);
            }
        });
    }
}
