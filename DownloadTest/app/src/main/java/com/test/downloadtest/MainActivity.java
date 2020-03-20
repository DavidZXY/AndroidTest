package com.test.downloadtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.test.downloadtest.bean.DownloadFileInfo;
import com.test.downloadtest.service.DownloadService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DavidZXY
 * @date 2019/12/21
 */
public class MainActivity extends AppCompatActivity {

    private List<DownloadFileInfo> mFileInfoList;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        initEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }

    private void initData() {

        mFileInfoList = new ArrayList<>();

        DownloadFileInfo mDownloadFileInfo = new DownloadFileInfo(0,
                "http://file.mukewang.com/apk/app/109/imooc7.2.810102001android.apk?version=1574827098",
                "imooc7.2.810102001android.apk", 0, 0);

        mFileInfoList.add(mDownloadFileInfo);
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new FileDownloadRecycleAdapter(this, mFileInfoList));
    }

    private void initEvent() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(DownloadService.ACTION_UPDATE);
        registerReceiver(mBroadcastReceiver, filter);
    }

    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (DownloadService.ACTION_UPDATE.equals(intent.getAction())) {
                int progress = intent.getIntExtra("progress", 0);
                LogUtils.i("MainActivity", "onReceive: " + progress + "%");
//                mProgressBar.setProgress(progress);
            }
        }
    };

}
