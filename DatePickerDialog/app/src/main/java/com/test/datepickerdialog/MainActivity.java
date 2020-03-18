package com.test.datepickerdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.test.datepickerdialog.widget.DatePickDialog;
import com.test.datepickerdialog.widget.DateType;

public class MainActivity extends AppCompatActivity {

    private Button mShowTimePickerDialogButton;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShowTimePickerDialogButton = findViewById(R.id.show_time_picker_dialog_button);
        mTextView = findViewById(R.id.edit_view);

        mShowTimePickerDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickDialog(DateType.TYPE_YMD);
            }
        });
    }

    private void showDatePickDialog(DateType type) {
        DatePickDialog dialog = new DatePickDialog(this);
        dialog.setEdit(R.id.edit_view);
        //设置上下年分限制
        dialog.setYearLimt(100);
        //设置类型
        dialog.setType(type);
        //设置选择回调
        dialog.setOnChangeLisener(null);
        //设置点击确定按钮回调
        dialog.setOnSureLisener(null);
        dialog.show();
    }
}
