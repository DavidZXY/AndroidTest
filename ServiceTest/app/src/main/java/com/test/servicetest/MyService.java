package com.test.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private static final String TAG = "MyService";

    MyAIDLService.Stub mBinder = new MyAIDLService.Stub() {
        @Override
        public int plus(int a, int b) throws RemoteException {
            return a + b;
        }

        @Override
        public String toUpperCase(String str) throws RemoteException {
            if (str != null)
                return str.toUpperCase();
            return null;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: executed");
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: executed");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //执行后台任务
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean stopService(Intent name) {
        Log.d(TAG, "stopService: " + name.toString());
        return super.stopService(name);
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: executed");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: executed");
        Log.d(TAG, "onCreate: Thread ID = " + Thread.currentThread().getId());
        Log.d(TAG, "onCreate: Process ID = " + Process.myPid());

        Notification notification = new Notification.Builder(this)
                .setContentTitle("通知标题")
                .setContentText("有通知咳咳")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(PendingIntent.getActivity(this, 0,
                        new Intent(this, MainActivity.class), 0))
                .build();
        startForeground(1, notification);
    }

    static class MyBinder extends Binder {

        public void startDownload() {
            Log.d(TAG, "startDownload: executed");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //开始执行下载任务
                }
            }).start();
        }
    }
}
