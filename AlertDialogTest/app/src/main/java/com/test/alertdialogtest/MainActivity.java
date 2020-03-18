package com.test.alertdialogtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author DavidZXY
 */
public class MainActivity extends AppCompatActivity {

    private Button customAlertButton;
    private Button systemAlertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }

    private void initEvent() {
        customAlertButton.setOnClickListener((view) -> {
                    View alertView = LayoutInflater.from(this).inflate(R.layout.alert_dialog_join_in, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    AlertDialog dialog = builder.create();
                    Button cancelButton = alertView.findViewById(R.id.button_cancel);
                    Button confirmButton = alertView.findViewById(R.id.button_confirm);
                    cancelButton.setOnClickListener(v -> {
                        dialog.dismiss();
                    });
                    confirmButton.setOnClickListener(v -> {
                        Toast.makeText(MainActivity.this, "点击确认", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    });

                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog.show();
                    dialog.setContentView(alertView);

                }
        );

        systemAlertButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setPositiveButton("确定", null)
                    .setMessage("some message");
            builder.create().show();
        });
    }

    private void initView() {
        customAlertButton = findViewById(R.id.custom_alert_button);
        systemAlertButton = findViewById(R.id.system_alert_button);
    }


}
