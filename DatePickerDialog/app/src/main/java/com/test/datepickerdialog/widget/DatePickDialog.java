package com.test.datepickerdialog.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.test.datepickerdialog.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * Created by codbking on 2016/8/11.
 */
public class DatePickDialog extends Dialog implements OnChangeLisener, OnSureLisener {

    //    private TextView titleTv;
    private FrameLayout wheelLayout;
    private TextView cancel;
    private int edit;
    private TextView sure;
    private EditText tv_detail_info_effectiveDate;
//    private TextView messgeTv;

    private String title;
    private String format;
    private DateType type = DateType.TYPE_ALL;

    //开始时间
    private Date startDate = new Date();
    //年分限制，默认上下5年
    private int yearLimt = 100;

    private OnChangeLisener onChangeLisener;

    private OnSureLisener onSureLisener;

    private DatePicker mDatePicker;

    public void setEdit(int i){
        this.edit=i;
    }

    //设置标题
    public void setTitle(String title) {
        this.title = title;
    }

    //设置模式
    public void setType(DateType type) {
        this.type = type;
    }

    //设置选择日期显示格式，设置显示message,不设置不显示message
    public void setMessageFormat(String format) {
        this.format = format;
    }

    //设置开始时间
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    //设置年份限制，上下年份
    public void setYearLimt(int yearLimt) {
        this.yearLimt = yearLimt;
    }

    //设置选择回调
    public void setOnChangeLisener(OnChangeLisener onChangeLisener) {
        this.onChangeLisener = onChangeLisener;
    }

    //设置点击确定按钮，回调
    public void setOnSureLisener(OnSureLisener onSureLisener) {
        this.onSureLisener = onSureLisener;
    }

    public DatePickDialog(Context context) {
        super(context, R.style.dialog_style);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cbk_dialog_pick_time);

        initView();
        initParas();
    }

    private DatePicker getDatePicker() {
        DatePicker picker = new DatePicker(getContext(), type);
        picker.setStartDate(startDate);
        picker.setYearLimt(yearLimt);
        picker.setOnChangeLisener(this);
        picker.init();
        return picker;
    }

    private void initView() {
        this.sure = (TextView) findViewById(R.id.sure);
        this.cancel = (TextView) findViewById(R.id.cancel);
        this.wheelLayout = (FrameLayout) findViewById(R.id.wheelLayout);

        mDatePicker = getDatePicker();
        this.wheelLayout.addView(mDatePicker);

        this.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        this.sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Long current = mDatePicker.getSelectDate().getTime();
                String validateYMD = fetchYMD(current);
                Log.d("选中的时间", validateYMD);
                tv_detail_info_effectiveDate = getCurrentActivity().findViewById(edit);
                tv_detail_info_effectiveDate.setText(validateYMD);
            }
        });
    }

    public static Activity getCurrentActivity() {
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(
                    null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(activityThread);
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Activity activity = (Activity) activityField.get(activityRecord);
                    return activity;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    //下面是处理时间戳为yyyy年MM月dd日格式
    public String fetchYMD(Long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        String result = formatter.format(time);
        return result;
    }


    private void initParas() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = DateUtils.getScreenWidth(getContext());
        getWindow().setAttributes(params);
    }

    @Override
    public void onChanged(Date date) {

        if (onChangeLisener != null) {
            onChangeLisener.onChanged(date);
        }

        if (!TextUtils.isEmpty(format)) {
            String messge = "";
            try {
                messge = new SimpleDateFormat(format).format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onSure(Date date) {
        date = getDatePicker().getSelectDate();
    }

}
