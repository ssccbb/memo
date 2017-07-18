package com.sung.note.memo.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.sung.note.memo.R;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sung on 16/3/4.
 */
public class App extends Application {
    private List<Activity> mList = new LinkedList();
    private Context context;
    private static App instance;

    public App() {
    }

    public App(Context context) {
        this.context = context;
    }

    public synchronized static App getInstance() {
        if (null == instance) {
            instance = new App();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        initImageLoader();
        super.onCreate();
    }

    private void initImageLoader(){
        File cacheDir=StorageUtils.getOwnCacheDirectory(this,"/sdcard/memo");
        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.default_loading_pic)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .build();
        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(this)
                .memoryCache(new WeakMemoryCache())
                .memoryCacheExtraOptions(480, 800)
                .memoryCacheSize(2 * 1024 * 1024)
                .threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileCount(100)
                .discCache(new UnlimitedDiscCache(cacheDir))
                .defaultDisplayImageOptions(options)
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }

    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.gc();
            System.exit(0);
        }
    }
}
