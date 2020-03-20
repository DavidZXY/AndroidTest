package com.test.downloadtest.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.PathUtils;
import com.test.downloadtest.bean.DownloadFileInfo;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

/**
 * @author DavidZXY
 * @date 2019/12/21
 */
public class DownloadService extends Service {
    public static final String DOWNLOAD_PATH = PathUtils.getExternalStoragePath() + "/downloads/";
    public static final String ACTION_START = "ACTION_START";
    public static final String ACTION_STOP = "ACTION_STOP";
    public static final String ACTION_UPDATE = "ACTION_UPDATE";
    public static final int MESSAGE_INIT = 0;
    private static final String TAG = "DownloadService";
    private DownloadTask mDownloadTask = null;

    NetworkUtils.OnNetworkStatusChangedListener mNetworkStatusChangedListener = new NetworkUtils.OnNetworkStatusChangedListener() {
        @Override
        public void onDisconnected() {
            Toast.makeText(DownloadService.this, "网络连接中断", Toast.LENGTH_SHORT).show();
            if (mDownloadTask != null) {
                mDownloadTask.isPause = true;
            }
        }

        @Override
        public void onConnected(NetworkUtils.NetworkType networkType) {
            LogUtils.i(TAG, "onConnected: " + networkType.name());
            switch (networkType) {
                case NETWORK_4G:
                    Toast.makeText(DownloadService.this, "连接到4G网络", Toast.LENGTH_SHORT).show();
                    break;
                case NETWORK_WIFI:
                    Toast.makeText(DownloadService.this, "连接到WIFI", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            if (mDownloadTask != null) {
                mDownloadTask.isPause = false;
            }
        }
    };

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == MESSAGE_INIT) {
                DownloadFileInfo fileInfo = (DownloadFileInfo) msg.obj;
                LogUtils.i(TAG, "Init", fileInfo);
                // 启动下载任务
                mDownloadTask = new DownloadTask(DownloadService.this, fileInfo);
                mDownloadTask.download();
            }
        }
    };

    public DownloadService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkUtils.registerNetworkStatusChangedListener(mNetworkStatusChangedListener);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (ACTION_START.equals(intent.getAction())) {
            DownloadFileInfo fileInfo = (DownloadFileInfo) intent.getSerializableExtra("fileInfo");
            LogUtils.i(TAG, "onStartCommand: ", ACTION_START, fileInfo != null ? fileInfo.toString() : null);
            new InitThread(fileInfo).start();
        } else if (ACTION_STOP.equals(intent.getAction())) {
            DownloadFileInfo fileInfo = (DownloadFileInfo) intent.getSerializableExtra("fileInfo");
            LogUtils.i(TAG, "onStartCommand: ", ACTION_STOP, fileInfo != null ? fileInfo.toString() : null);
            if (mDownloadTask != null) {
                mDownloadTask.isPause = true;
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        mHandler = null;
        NetworkUtils.unregisterNetworkStatusChangedListener(mNetworkStatusChangedListener);
    }

    class InitThread extends Thread {

        private DownloadFileInfo mFileInfo;

        InitThread(DownloadFileInfo downloadFileInfo) {
            mFileInfo = downloadFileInfo;
        }

        @Override
        public void run() {
            HttpURLConnection connection = null;
            RandomAccessFile randomAccessFile = null;
            try {
                // 连接网络文件
                URL url = new URL(mFileInfo.getUrl());
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(3000);
                connection.setRequestMethod("GET");

                int length = -1;
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    // 获得文件长度
                    length = connection.getContentLength();
                }
                if (length <= 0) {
                    return;
                }

                File dir = new File(DOWNLOAD_PATH);
                FileUtils.createOrExistsDir(dir);
                // 在本地创建文件
                File file = new File(dir, mFileInfo.getFileName());
                FileUtils.createOrExistsFile(file);
                randomAccessFile = new RandomAccessFile(file, "rwd");
                // 设置文件长度
                randomAccessFile.setLength(length);
                mFileInfo.setLength(length);
                mHandler.obtainMessage(MESSAGE_INIT, mFileInfo).sendToTarget();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    Objects.requireNonNull(randomAccessFile).close();
                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
