package com.test.downloadtest.databse;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.test.downloadtest.bean.DownloadThreadInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DavidZXY
 * @date 2019/12/23
 */
public class ThreadDAOImpl implements ThreadDAO {

    private DataBaseHelper mDataBaseHelper;

    public ThreadDAOImpl(Context context) {
        mDataBaseHelper = new DataBaseHelper(context);
    }

    @Override
    public void insertThread(DownloadThreadInfo threadInfo) {
        SQLiteDatabase database = mDataBaseHelper.getWritableDatabase();
        database.execSQL("insert into thread_info(thread_id, url, start, end, progress) values(?, ?, ?, ?, ?)",
                new Object[]{threadInfo.getId(), threadInfo.getUrl(), threadInfo.getStart(),
                        threadInfo.getEnd(), threadInfo.getProgress()});
        database.close();
    }

    @Override
    public void deleteThread(String url, int threadId) {
        SQLiteDatabase database = mDataBaseHelper.getWritableDatabase();
        database.execSQL("delete from thread_info where url = ? and thread_id = ?",
                new Object[]{url, threadId});
        database.close();
    }

    @Override
    public void updateThread(String url, int threadId, int progress) {
        SQLiteDatabase database = mDataBaseHelper.getWritableDatabase();
        database.execSQL("update thread_info set progress = ? where url = ? and thread_id = ?",
                new Object[]{progress, url, threadId});
        database.close();
    }

    @Override
    public List<DownloadThreadInfo> searchThreads(String url) {
        SQLiteDatabase database = mDataBaseHelper.getWritableDatabase();
        List<DownloadThreadInfo> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from thread_info where url = ?", new String[]{url});
        while (cursor.moveToNext()) {
            DownloadThreadInfo threadInfo = new DownloadThreadInfo();
            threadInfo.setId(cursor.getInt(cursor.getColumnIndex("thread_id")));
            threadInfo.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            threadInfo.setStart(cursor.getInt(cursor.getColumnIndex("start")));
            threadInfo.setEnd(cursor.getInt(cursor.getColumnIndex("end")));
            threadInfo.setProgress(cursor.getInt(cursor.getColumnIndex("progress")));
            list.add(threadInfo);
        }
        cursor.close();
        database.close();
        return list;
    }

    @Override
    public boolean isExists(String url, int threadId) {
        SQLiteDatabase database = mDataBaseHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from thread_info where url = ? and thread_id = ?", new String[]{url, threadId + ""});
        boolean isExists = cursor.moveToNext();
        cursor.close();
        database.close();
        return isExists;
    }
}
