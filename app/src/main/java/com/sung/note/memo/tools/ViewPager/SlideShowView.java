package com.sung.note.memo.tools.ViewPager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.squareup.okhttp.Request;
import com.sung.note.memo.application.App;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * ViewPager实现的轮播图广告自定义视图，如京东首页的广告轮播图效果；
 * 既支持自动轮播页面也支持手势滑动切换页面
 */

public class SlideShowView extends FrameLayout {
    public SlideShowView(Context context) {
        super(context);
    }
/*
    // 使用universal-image-loader插件读取网络图片，需要工程导入universal-image-loader-1.8.6-with-sources.jar
    private ImageLoader imageLoader = ImageLoader.getInstance();

    //轮播图图片数量
    private final static int IMAGE_COUNT = 4;
    //自动轮播的时间间隔
    private final static int TIME_INTERVAL = 5;
    //自动轮播启用开关
    private final static boolean isAutoPlay = true;

    //自定义轮播图的资源
    private String[] imageUrls;
    //放轮播图片的ImageView 的list
    private List<ImageView> imageViewsList;
    //放圆点的View的list
    private List<View> dotViewsList;

    private android.support.v4.view.ViewPager viewPager;
    //当前轮播页
    private int currentItem  = 0;
    //定时任务
    private ScheduledExecutorService scheduledExecutorService;

    private Context context;

    ArrayList<String> list;
    ArrayList<String> idlist;
    private String TAG="AD_TAG";

    private Handler uiHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            initUI(context);
            super.handleMessage(msg);
        }
    };

    //Handler
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            viewPager.setCurrentItem(currentItem);
        }

    };

    public SlideShowView(Context context) {
        this(context,null);
        // TODO Auto-generated constructor stub
    }
    public SlideShowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }
    public SlideShowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;

        //initImageLoader(context);

        initData();
        if(isAutoPlay){
            startPlay();
        }

    }
    *//**
     * 开始轮播图切换
     *//*
    private void startPlay(){
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 5, 4, TimeUnit.SECONDS);
    }
    *//**
     * 停止轮播图切换
     *//*
    private void stopPlay(){
        scheduledExecutorService.shutdown();
    }
    *//**
     * 初始化相关Data
     *//*
    private void initData(){
        imageViewsList = new ArrayList<ImageView>();
        dotViewsList = new ArrayList<View>();

        // 一步任务获取图片
        *//*String httpHead = App.getInstance().getHttpHead();
        OkHttpUtils.post()
                .url(httpHead+"/Busine/BaseInfo.aspx?mcode=GetImg")
                .addParams("mcode", "ADInfo")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, e.toString());
                    }

                    @Override
                    public void onResponse(String s) {
                        Log.e(TAG, s.toString());
                        try {
                            idlist = new ArrayList<String>();
                            list = new ArrayList<String>();
                            JSONObject obj=new JSONObject(s);
                            if (obj.getString("error").equals("0")){
                                JSONArray array = obj.getJSONArray("listimg");
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject object = array.getJSONObject(i);
                                    String url = object.getString("MainPic");
                                    String adid = object.getString("id");
                                    list.add(i, url);
                                    idlist.add(i, adid);
                                }
                                uiHandler.sendEmptyMessage(99);
                            }else {
                                Toast.makeText(context, "服务器繁忙！请稍后再试", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG,e.toString());
                        }
                    }
                });*//*

    }
    *//**
     * 初始化Views等UI
     *//*
    private void initUI(Context context){
        if(list == null || list.size() == 0)
            return;

        LayoutInflater.from(context).inflate(R.layout.layout_slideshow, this, true);

        LinearLayout dotLayout = (LinearLayout)findViewById(R.id.dotLayout);
        dotLayout.removeAllViews();

        // 热点个数与图片特殊相等
        for (int i = 0; i < list.size(); i++) {
            ImageView view =  new ImageView(context);
            view.setTag(list.get(i));
//            if(i==0)//给一个默认图
                view.setBackgroundResource(R.drawable.default_gray_bg);
            view.setScaleType(ScaleType.FIT_XY);
            imageViewsList.add(view);

            ImageView dotView =  new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15,15);
            params.leftMargin = 4;
            params.rightMargin = 4;
            dotLayout.addView(dotView, params);
            dotViewsList.add(dotView);
        }

        viewPager= (android.support.v4.view.ViewPager) findViewById(R.id.viewPager);
        viewPager.setFocusable(true);

        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.setOnPageChangeListener((android.support.v4.view.ViewPager.OnPageChangeListener) new MyPageChangeListener());
    }

    *//**
     * 填充ViewPager的页面适配器
     *
     *//*
    private class MyPagerAdapter  extends PagerAdapter {

        @Override
        public void destroyItem(View container, int position, Object object) {
            // TODO Auto-generated method stub
            //((ViewPag.er)container).removeView((View)object);
            ((android.support.v4.view.ViewPager)container).removeView(imageViewsList.get(position));
        }

        @Override
        public Object instantiateItem(View container, final int position) {
            ImageView imageView = imageViewsList.get(position);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.default_gray_bg));

            //图片设置
            String path=imageView.getTag().toString();
            //imageLoader.displayImage(path,imageView);
            saveImgByUrl(path,imageView);

                    ((android.support.v4.view.ViewPager) container).addView(imageViewsList.get(position));
            imageViewsList.get(position).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            return imageViewsList.get(position);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return imageViewsList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }
        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public Parcelable saveState() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

    }
    *//**
     * ViewPager的监听器
     * 当ViewPager中页面的状态发生改变时调用
     *
     *//*
    private class MyPageChangeListener implements android.support.v4.view.ViewPager.OnPageChangeListener{

        boolean isAutoPlay = false;

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
            switch (arg0) {
                case 1:// 手势滑动，空闲中
                    isAutoPlay = false;
                    break;
                case 2:// 界面切换中
                    isAutoPlay = true;
                    break;
                case 0:// 滑动结束，即切换完毕或者加载完毕
                    // 当前为最后一张，此时从右向左滑，则切换到第一张
                    if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                        viewPager.setCurrentItem(0);
                    }
                    // 当前为第一张，此时从左向右滑，则切换到最后一张
                    else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
                        viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
                    }
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int pos) {
            // TODO Auto-generated method stub

            currentItem = pos;
            for(int i=0;i < dotViewsList.size();i++){
                if(i == pos){
                    ((View)dotViewsList.get(pos)).setBackgroundResource(R.drawable.dot_focus);
                }else {
                    ((View)dotViewsList.get(i)).setBackgroundResource(R.drawable.dot_blur);
                }
            }
        }

    }

    *//**
     *执行轮播图切换任务
     *
     *//*
    private class SlideShowTask implements Runnable{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (viewPager) {
                currentItem = (currentItem+1)%imageViewsList.size();
                handler.obtainMessage().sendToTarget();
            }
        }

    }

    *//**
     * 销毁ImageView资源，回收内存
     *
     *//*
    private void destoryBitmaps() {

        for (int i = 0; i < IMAGE_COUNT; i++) {
            ImageView imageView = imageViewsList.get(i);
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                //解除drawable对view的引用
                drawable.setCallback(null);
            }
        }
    }

    *//**
     * ImageLoader 图片组件初始化
     *
     * @param context
     *//*
    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you
        // may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory().discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs() // Remove
                // for
                // release
                // app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }

    *//*
	* 	url 从服务器拿到的url地址
	* 	url必须要这种格式
	* 	http://123.57.253.181:8006/uploadDevelop/Dic/201605/20160504174045792533.png
	* *//*
    private void saveImgByUrl(String url,ImageView img){
        String[] split = url.split("/");
        String[] split1 = split[split.length - 1].split("\\.");
        String filePath="/sdcard/yrksCache/yrks_marchant"+"/yrks_marchant"+split1[0]+"_cropped.png";
        File file = new File(filePath);

        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;

        if (file.exists()){//有缓存
            //Log.e(TAG,"缓存"+filePath);
            Drawable d=new BitmapDrawable(BitmapFactory.decodeFile(filePath,opt));
            //Drawable imgFile=new BitmapDrawable(imgTools.readBitMapFromDrwable(d));//二次优化本地
            img.setImageDrawable(d);
        }else {
            //一次优化，进行本地保存

            //Log.e(TAG,"无缓存"+filePath);
			*//*
			* 	异步获取url图片并优化
			* 	保存至本地
			* *//*
            BitmapWorkerTask asyncTask=new BitmapWorkerTask(img);
            asyncTask.execute(url,10+"",false+"");//1倍，只进行一次优化，不压缩
        }
    }*/
}