package com.sung.note.memo.tools.Others;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 天旺 on 2015/8/31.
 */
public class CheckNetState {
    private Context context;
    public CheckNetState(Context context){
        this.context=context;
    }

    public boolean isNetOk(){
        //获取连接管理对象
        ConnectivityManager manager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        //获取正在连接着的网络信息的对象--可用
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        return networkInfo!=null;
    }
}
