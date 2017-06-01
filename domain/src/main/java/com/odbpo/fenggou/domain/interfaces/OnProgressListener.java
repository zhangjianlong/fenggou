package com.odbpo.fenggou.domain.interfaces;

/**
 * @author: zjl
 * @Time:  2017/6/1 15:13
 * @Desc: 上传下载进度监听器
 */


public interface OnProgressListener {

    /**
     * 上传，下载进度监听
     *
     * @param currentBytesCount 当前进度
     * @param totalBytesCount   总大小
     */
    void onProgress(long currentBytesCount, long totalBytesCount);
}
