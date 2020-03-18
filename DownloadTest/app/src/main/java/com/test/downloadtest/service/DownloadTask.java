package com.test.downloadtest.service;

import android.content.Context;
import android.content.Intent;

import com.blankj.utilcode.util.LogUtils;
import com.test.downloadtest.bean.DownloadFileInfo;
import com.test.downloadtest.bean.DownloadThreadInfo;
import com.test.downloadtest.databse.ThreadDAO;
import com.test.downloadtest.databse.ThreadDAOImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

/**
 * @author DavidZXY
 * @date 2019/12/23
 */
class DownloadTask {

    private Context mContext;
    private DownloadFileInfo mFileInfo;
    private ThreadDAO mThreadDAO;
    private int mProgress = 0;
    boolean isPause = false;

    DownloadTask(Context context, DownloadFileInfo downloadFileInfo) {
        mContext = context;
        mFileInfo = downloadFileInfo;
        mThreadDAO = new ThreadDAOImpl(mContext);
    }

    void download() {
        List<DownloadThreadInfo> list = mThreadDAO.searchThreads(mFileInfo.getUrl());
        DownloadThreadInfo threadInfo;
        if (list.size() == 0) {
            threadInfo = new DownloadThreadInfo(0, mFileInfo.getUrl(), 0, mFileInfo.getLength(), 0);
        } else {
            threadInfo = list.get(0);
        }
        new DownloadThread(threadInfo).start();
    }

    /**
     * 下载线程
     */
    class DownloadThread extends Thread {

        private DownloadThreadInfo mThreadInfo;

        DownloadThread(DownloadThreadInfo downloadThreadInfo) {
            mThreadInfo = downloadThreadInfo;
        }

        @Override
        public void run() {
            // 向数据库插入线程信息
            if (!mThreadDAO.isExists(mThreadInfo.getUrl(), mThreadInfo.getId())) {
                mThreadDAO.insertThread(mThreadInfo);
            }
            HttpURLConnection connection = null;
            RandomAccessFile randomAccessFile = null;
            InputStream inputStream = null;
            try {
                URL url = new URL(mThreadInfo.getUrl());
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(3000);
                connection.setRequestMethod("GET");
                // 设置下载位置
                int start = mThreadInfo.getStart() + mThreadInfo.getProgress();
                connection.setRequestProperty("Range", "bytes=" + start + "-" + mThreadInfo.getEnd());
                // 设置文件写入位置
                File file = new File(DownloadService.DOWNLOAD_PATH, mFileInfo.getFileName());
                randomAccessFile = new RandomAccessFile(file, "rwd");
                randomAccessFile.seek(start);

                Intent intent = new Intent(DownloadService.ACTION_UPDATE);
                mProgress += mThreadInfo.getProgress();
                // 开始下载
                if (connection.getResponseCode() == HttpURLConnection.HTTP_PARTIAL ||
                        connection.getResponseCode() == HttpURLConnection.HTTP_OK ) {
                    // 读取数据
                    inputStream = connection.getInputStream();
                    byte[] buffer = new byte[1024 * 4];
                    int len;
                    long time = System.currentTimeMillis();
                    while ((len = inputStream.read(buffer)) != -1) {
                        // 写入文件
                        randomAccessFile.write(buffer, 0, len);
                        mProgress += len;
                        // 把下载进度发送给Activity
                        if (System.currentTimeMillis() - time > 500) {
                            time = System.currentTimeMillis();
                            intent.putExtra("progress", (int)(mProgress / (mFileInfo.getLength() + 0.0) * 100));
                            mContext.sendBroadcast(intent);
                        }
                        // 下载暂停时保存下载进度
                        if (isPause) {
                            mThreadDAO.updateThread(mThreadInfo.getUrl(), mThreadInfo.getId(), mProgress);
                            LogUtils.i("DownloadTask", "run: " + "isPause: " + isPause);
                            return;
                        }
                    }
                    intent.putExtra("progress",100);
                    mContext.sendBroadcast(intent);
                    // 删除线程信息
                    mThreadDAO.deleteThread(mThreadInfo.getUrl(), mThreadInfo.getId());
                }
            }
            catch (SocketException e) {
                e.printStackTrace();
                mThreadDAO.updateThread(mThreadInfo.getUrl(), mThreadInfo.getId(), mProgress);
            }
            catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    Objects.requireNonNull(inputStream).close();
                    randomAccessFile.close();
                    connection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
