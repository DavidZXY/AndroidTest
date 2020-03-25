package com.test.wakeupapptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author DavidZXY
 */
public class MainActivity extends AppCompatActivity {

    private Button mWakeUpButton;
    private EditText mUsernameEditText;
    private EditText mBusinessNumberEditText;
    private EditText mUserCodeEditText;

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
                String userName = mUsernameEditText.getText().toString();
                String userCode = mUserCodeEditText.getText().toString();
                String businessNumber = mBusinessNumberEditText.getText().toString();
                wakeUp(userName, userCode, businessNumber);
            }
        });
    }

    private void initView() {
        mUsernameEditText = findViewById(R.id.username_edit_text);
        mBusinessNumberEditText = findViewById(R.id.business_number_edit_text);
        mUserCodeEditText = findViewById(R.id.user_code_edit_text);

        mWakeUpButton  = findViewById(R.id.wake_up_button);
    }

    /**
     * 唤醒中诚信托App
     * @param userCode 客户编号
     * @param businessNumber 预约编号
     * @param userName 登录名
     */
    private void wakeUp(String userName, String userCode, String businessNumber) {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.test.roomtest", "com.test.roomtest.MainActivity");
        intent.setComponent(componentName);

        Bundle bundle = new Bundle();
        bundle.putString("userCode", userCode);
        bundle.putString("businessNumber", businessNumber);
        bundle.putString("userName", userName);
        intent.putExtras(bundle);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "WakeUp" + "  " + getTaskId(), Toast.LENGTH_SHORT).show();
    }
}
