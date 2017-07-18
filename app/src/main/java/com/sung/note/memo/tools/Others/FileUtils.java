package com.sung.note.memo.tools.Others;

import android.content.Context;
import android.util.Log;

import java.io.File;

/**
 * Created by sung on 15/12/26.
 */
public class FileUtils {
    /**
     * 缓存文件目录
     */
    private File mCacheDir;

    public FileUtils(Context context, String cacheDir) {
        if (android.os.Environment.getExternalStorageState().
                equals(android.os.Environment.MEDIA_MOUNTED))
            mCacheDir = new File(cacheDir);
        else {
            mCacheDir = context.getCacheDir();// 如何获取系统内置的缓存存储路径
            Log.e("cacheDir", mCacheDir.getAbsolutePath());
        }
        if (!mCacheDir.exists())
            mCacheDir.mkdirs();
        Log.e("cacheDir",mCacheDir.getAbsolutePath());
    }

    public String getCacheDir() {
        return mCacheDir.getAbsolutePath();
    }
}
