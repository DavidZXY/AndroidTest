package com.test.androidutilcodetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.TimeUtils;

import java.io.File;
import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {

    private Button mRenameButton;
    private Button mCreateButton;
    private Button mTimeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        initEvent();
    }

    private void initData() {

    }

    private void initView() {
        mRenameButton = findViewById(R.id.rename_button);
        mCreateButton = findViewById(R.id.create_button);
        mTimeButton = findViewById(R.id.time_button);
    }

    private void initEvent() {
        mRenameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FileUtils.rename(PathUtils.getExternalAppCachePath() +  "/" + "abc.txt", "qwe.txt");
                FileUtils.createFileByDeleteOldFile(PathUtils.getExternalAppCachePath() +  "/" + "abc.txt");
            }
        });

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(PathUtils.getExternalAppCachePath(), "abc.txt");
                FileUtils.createOrExistsFile(file);
                File file2 = new File(PathUtils.getExternalAppCachePath(), "qwe.txt");
                FileUtils.createOrExistsFile(file2);
            }
        });

        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] now = TimeUtils.getNowString().split(" ");
                Toast.makeText(MainActivity.this, "date=" + now[0] + "\n" + "time=" + now[1], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
