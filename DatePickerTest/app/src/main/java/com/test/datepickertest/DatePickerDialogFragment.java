package com.test.datepickertest;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * @author DavidZXY
 * @date 2020/1/16
 */
public class DatePickerDialogFragment extends DialogFragment {

    private DatePicker mDatePicker;
    private Button mCancelButton;
    private Button mConfirmButton;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_date_picker_dialog, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initEvent();
    }

    private void initView(View view) {
        mDatePicker = view.findViewById(R.id.date_picker);
        mCancelButton = view.findViewById(R.id.cancel_button);
        mConfirmButton = view.findViewById(R.id.confirm_button);
    }

    private void initEvent() {
        mConfirmButton.setOnClickListener(v -> {
            if (mDateSetListener != null) {
                mDatePicker.clearFocus();
                mDateSetListener.onDateSet(mDatePicker, mDatePicker.getYear(),
                        mDatePicker.getMonth(), mDatePicker.getDayOfMonth());
            }
            dismiss();
        });
        mCancelButton.setOnClickListener(v -> dismiss());
    }

    public void setDateSetListener(DatePickerDialog.OnDateSetListener listener) {
        mDateSetListener = listener;
    }
}
