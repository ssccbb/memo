package com.sung.note.memo.tools;

/**
 * Created by sung on 15/12/8.
 */

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 *      在Application中统一捕获异常,防止程序异常退出
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler instance;  //单例引用，这里我们做成单例的，因为我们一个应用程序里面只需要一个UncaughtExceptionHandler实例
    private Context context;
    /** 系统默认的UncaughtException处理类 */
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    private CrashHandler(){}

    public synchronized static CrashHandler getInstance(){  //同步方法，以免单例多线程环境下出现异常
        if (instance == null){
            instance = new CrashHandler();
        }
        return instance;
    }

    public void init(Context context){  //初始化，把当前对象设置成UncaughtExceptionHandler处理器
        this.context=context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {  //当有未处理的异常发生时，就会来到这里。。
//        Log.e("Sung", "uncaughtException, thread: " + thread
//                + " \nname: " + thread.getName() + " \nid: " + thread.getId() + "\nexception: "
//                + ex);
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            //Sleep一会后结束程序
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.e("Sung", "Error : ", e);
            }
//            Intent restartintent=new Intent(context,SplashActivity.class);
//            int paID=1;
//            PendingIntent pa=PendingIntent.getActivity(context,paID,restartintent,PendingIntent.FLAG_CANCEL_CURRENT);
//            AlarmManager mgr= (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//            mgr.set(AlarmManager.RTC,System.currentTimeMillis()+100,pa);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(10);
        }
    }

    private boolean handleException(Throwable ex) {
        if (ex == null) {
            Log.e("Sung", "handleException --- ex==null");
            return true;
        }
        Log.e("", "handleException "+ex.toString());
        final String msg = ex.getLocalizedMessage();
        if(msg == null) {
            return false;
        }
        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast toast = Toast.makeText(context, "侬伯品出错了，即将退出",
                        Toast.LENGTH_LONG);
                //Log.e("", "侬伯品出错了，即将退出 ");
                //toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                Looper.loop();
            }
        }.start();
        return true;
    }
}
