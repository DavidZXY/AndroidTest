package com.test.datepickerdialog.widget;

import android.content.Context;

import com.test.datepickerdialog.R;

import java.util.Date;

/**
 * Created by codbking on 2016/8/10.
 */
class DatePicker extends BaseWheelPick {

    private static final String TAG = "WheelPicker";

    private WheelView yearView;
    private WheelView monthView;
    private WheelView dayView;

    private Integer[] yearArr, mothArr, dayArr, hourArr, minutArr;
    private DatePickerHelper datePicker;

    public DateType type = DateType.TYPE_ALL;

    //开始时间
    private Date startDate = new Date();
    //年分限制，默认上下5年
    private int yearLimt = 5;

    private OnChangeLisener onChangeLisener;
    private int selectDay;

    //选择时间回调
    public void setOnChangeLisener(OnChangeLisener onChangeLisener) {
        this.onChangeLisener = onChangeLisener;
    }

    public DatePicker(Context context, DateType type) {
        super(context);
        if(this.type!=null){
            this.type = type;
        }
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setYearLimt(int yearLimt) {
        this.yearLimt = yearLimt;
    }

    //初始化值
    public void init() {
        this.dayView = (WheelView) findViewById(R.id.day);
        this.monthView = (WheelView) findViewById(R.id.month);
        this.yearView = (WheelView) findViewById(R.id.year);

//        this.dayView.setVisibility(VISIBLE);
//        this.monthView.setVisibility(VISIBLE);
//        this.yearView.setVisibility(VISIBLE);


        datePicker = new DatePickerHelper();
        datePicker.setStartDate(startDate, yearLimt);

        dayArr = datePicker.genDay();
        yearArr = datePicker.genYear();
        mothArr = datePicker.genMonth();
        hourArr = datePicker.genHour();
        minutArr = datePicker.genMinut();


        setWheelListener(yearView, yearArr, false);
        setWheelListener(monthView, mothArr, true);
        setWheelListener(dayView, dayArr, true);

        yearView.setCurrentItem(datePicker.findIndextByValue(datePicker.getToady(DatePickerHelper.Type.YEAR), yearArr));
        monthView.setCurrentItem(datePicker.findIndextByValue(datePicker.getToady(DatePickerHelper.Type.MOTH), mothArr));
        dayView.setCurrentItem(datePicker.findIndextByValue(datePicker.getToady(DatePickerHelper.Type.DAY), dayArr));
    }


    protected String[] convertData(WheelView wheelView, Integer[] data) {
        if (wheelView == yearView) {
            return datePicker.getDisplayValue(data, "");
        } else if (wheelView == monthView) {
            return datePicker.getDisplayValue(data, "");
        } else if (wheelView == dayView) {
            return datePicker.getDisplayValue(data, "");
        }
        return new String[0];
    }

    @Override
    protected int getLayout() {
        return R.layout.cbk_wheel_picker;
    }

    @Override
    protected int getItemHeight() {
        return dayView.getItemHeight();
    }


    @Override
    protected void setData(Object[] datas) {
    }

    private void setChangeDaySelect(int year, int moth) {
        dayArr = datePicker.genDay(year, moth);
        WheelGeneralAdapter adapter= (WheelGeneralAdapter) dayView.getViewAdapter();
        adapter.setData(convertData(dayView,  dayArr));

        int indxt = datePicker.findIndextByValue(selectDay, dayArr);
        if (indxt == -1) {
            dayView.setCurrentItem(0);
        } else {
            dayView.setCurrentItem(indxt);
        }
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {

        int year = yearArr[yearView.getCurrentItem()];
        int moth = mothArr[monthView.getCurrentItem()];
        int day = dayArr[dayView.getCurrentItem()];

        if (wheel == yearView || wheel == monthView) {
            setChangeDaySelect(year, moth);
        } else {
            selectDay = day;
        }

        if (onChangeLisener != null) {
            onChangeLisener.onChanged(DateUtils.getDate(year, moth, day));
        }

    }

    @Override
    public void onScrollingStarted(WheelView wheel) {
    }

    @Override
    public void onScrollingFinished(WheelView wheel) {
    }


    //获取选中日期
    public Date getSelectDate() {

        int year = yearArr[yearView.getCurrentItem()];
        int moth = mothArr[monthView.getCurrentItem()];
        int day = dayArr[dayView.getCurrentItem()];

        return DateUtils.getDate(year, moth, day);

    }



}
