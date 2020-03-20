package com.test.downloadtest.databse;

import com.test.downloadtest.bean.DownloadThreadInfo;

import java.util.List;

/**
 * @author DavidZXY
 * @date 2019/12/23
 */
public interface ThreadDAO {

    /**
     * 插入线程信息
     * @param threadInfo 实体类
     */
    void insertThread(DownloadThreadInfo threadInfo);

    /**
     * 删除线程
     * @param url 文件下载地址
     * @param threadId 线程id
     */
    void deleteThread(String url, int threadId);

    /**
     * 更新线程下载进度
     * @param url 文件下载地址
     * @param threadId 线程id
     * @param progress 下载进度
     */
    void updateThread(String url, int threadId, int progress);

    /**
     * 查询文件的线程信息
     * @param url 文件下载地址
     * @return 返回所有搜索结果
     */
    List<DownloadThreadInfo> searchThreads(String url);

    /**
     * 线程信息是否存在
     * @param url 文件下载地址
     * @param threadId 线程id
     * @return 存在返回true，不存在返回false
     */
    boolean isExists(String url, int threadId);
}
