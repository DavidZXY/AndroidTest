package com.test.wakeupapptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button mWakeUpButton;
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }

    private void initEvent() {
        mWakeUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                ComponentName componentName = new ComponentName("com.sinosoft.cs", "com.sinosoft.cs.main.MainActivity");
//                intent.setComponent(componentName);
//
//                //agentCode/businum/userNo/
//                Bundle bundle = new Bundle();
//                bundle.putString("username", mUsernameEditText.getText().toString());
//                bundle.putString("password", mPasswordEditText.getText().toString());
//                intent.putExtras(bundle);
//
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
                wakeUp("0007", "SGD201912051000290", "zhouxinyu");
            }
        });
    }

    private void initView() {
        mUsernameEditText = findViewById(R.id.username_edit_text);
        mPasswordEditText = findViewById(R.id.password_edit_text);
        mWakeUpButton  = findViewById(R.id.wake_up_button);
    }

    /**
     * 唤醒中诚信托App
     * @param agentCode 客户编号
     * @param businessNumber 预约编号
     * @param userNo 登录名
     */
    private void wakeUp(String agentCode, String businessNumber, String userNo) {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.sinosoft.cs", "com.sinosoft.cs.main.MainActivity");
        intent.setComponent(componentName);

        Bundle bundle = new Bundle();
        bundle.putString("agentCode", agentCode);
        bundle.putString("businum", businessNumber);
        bundle.putString("userNo", userNo);
        intent.putExtras(bundle);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
