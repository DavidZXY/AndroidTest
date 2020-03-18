package com.test.daggertest;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    public int userId;
    public String userName;
    public boolean isMale;

//    public Book book;

    private User(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        isMale = in.readInt()==1;
//        book = in.readParcelable(Thread.currentThread().getContextClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(userName);
        dest.writeInt(isMale?1:0);
//        dest.writeParcelable(book);
    }
}
