package com.test.datepickerdialog.widget;


public class DateBean {

    private int year;
    private int moth;
    private int day;
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMoth() {
        return moth;
    }

    public void setMoth(int moth) {
        this.moth = moth;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }


    private String makeZero(int m){

         if(m>9){
             return  ""+m;
         }
        if(m>0){
            return "0"+m;
        }
        return ""+m;
    }
}
