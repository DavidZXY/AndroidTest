package com.test.datepickertest;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView mDateTextView;

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
        mDateTextView = findViewById(R.id.date_text);
    }

    private void initEvent() {
        mDateTextView.setOnClickListener(v -> {
            DatePickerDialogFragment dialogFragment = new DatePickerDialogFragment();
            dialogFragment.setDateSetListener((DatePicker view, int year, int month, int dayOfMonth) -> {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                mDateTextView.setText(simpleDateFormat.format(calendar.getTime()));
            });
            dialogFragment.show(getSupportFragmentManager(), "DatePickerDialogFragment");
        });
    }
}
