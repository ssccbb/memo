package com.sung.note.memo.ui.index;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.sung.note.memo.R;
import com.sung.note.memo.application.App;
import com.sung.note.memo.tools.Others.SystemStatusMannager;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MemoIndexActivity extends AppCompatActivity implements View.OnClickListener{
    private String TAG="index";
    private List<View> lists = new ArrayList<View>();
    private MyAdapter myAdapter;
    private ViewPager viewPager;

    private SystemStatusMannager tintManager;

    private FloatingActionButton sub;
    private TextView cloud;
    private boolean fabOpened=false;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vpsplash);
        App.getInstance().addActivity(MemoIndexActivity.this);

        SharedPreferences sp = getSharedPreferences("IntentFlag", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        edit.putBoolean("flag", false);
        edit.commit();

        initViewPager();

        fabClick();
        initPopupWindow();
    }
    private void fabClick(){
        sub = (FloatingActionButton) findViewById(R.id.fab);
        sub.setOnClickListener(this);

        cloud = (TextView) findViewById(R.id.cloud);
        cloud.setLayoutParams(new RelativeLayout.LayoutParams(0,0));
        cloud.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                //Log.e(TAG, "onClick ");
                if (!fabOpened)
                    openMenu(view);
                else
                    closeMenu(sub);

                if (dialog.isShowing())
                    dialog.dismiss();
                else
                    dialog.show();
                break;
            case R.id.cloud:
                if (fabOpened)
                    closeMenu(sub);
                break;
            default:
                break;
        }
    }

    private void initViewPager(){
        View view1=getLayoutInflater().inflate(R.layout.viewpager_layout1, null);
        View view2=getLayoutInflater().inflate(R.layout.viewpager_layout2, null);
        View view3=getLayoutInflater().inflate(R.layout.viewpager_layout3, null);

        lists.add(view1);
        lists.add(view2);
        lists.add(view3);

        final Fragment todof=new TodoFragment();
        final Fragment note=new NoteFragment();
        final Fragment about=new AboutFragment();
        final FragmentManager fm = getFragmentManager();

        myAdapter = new MyAdapter(lists);
        viewPager = (ViewPager) findViewById(R.id.vpviewpager);
        viewPager.setAdapter(myAdapter);
        viewPager.setCurrentItem(1);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        if (!about.isAdded())
                            fm.beginTransaction().add(R.id.container1, about, "about").show(about).commit();
                        else
                            fm.beginTransaction().show(about).commit();
                        break;
                    case 1:
                        if (!todof.isAdded())
                            fm.beginTransaction().add(R.id.container2, todof, "todof").show(todof).commit();
                        else
                            fm.beginTransaction().show(todof).commit();
                        break;
                    case 2:
                        if (!note.isAdded())
                            fm.beginTransaction().add(R.id.container3, note, "note").show(note).commit();
                        else
                            fm.beginTransaction().show(note).commit();
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    class MyAdapter extends PagerAdapter {

        List<View> viewLists;

        public MyAdapter(List<View> lists)
        {
            viewLists = lists;
        }

        @Override
        public int getCount() {                                                                 //获得size
            // TODO Auto-generated method stub
            return viewLists.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(View view, int position, Object object)                       //销毁Item
        {
//            Log.e("position:",position+"");
            switch (position){
                case 0:
//                    bitmap1.recycle();
                    break;
                case 1:
//                    bitmap2.recycle();
                    break;
            }
            ((ViewPager) view).removeView(viewLists.get(position));
        }

        @Override
        public Object instantiateItem(View view, int position)                                //实例化Item
        {
            ((ViewPager) view).addView(viewLists.get(position), 0);
            return viewLists.get(position);
        }
    }

    // TODO: 16/8/21 FloatingActionButton animation
    private void openMenu(View view){
        ObjectAnimator animator=ObjectAnimator.ofFloat(view,"rotation",0,-155,-135);
        animator.setDuration(500);
        animator.start();
        AlphaAnimation alphaAnimation=new AlphaAnimation(0,0.7f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);
        cloud.startAnimation(alphaAnimation);
        cloud.setLayoutParams(new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        fabOpened=true;
    }

    private void closeMenu(View view){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", -135, 20, 0);
        animator.setDuration(500);
        animator.start();
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.7f, 0);
        alphaAnimation.setFillAfter(true);
        cloud.startAnimation(alphaAnimation);
        cloud.setLayoutParams(new RelativeLayout.LayoutParams(0, 0));
        fabOpened=false;
    }

    // TODO: 16/8/22 popupwindow
    private void initPopupWindow(){
        View inflate = this.getLayoutInflater().inflate(R.layout.index_fab_dialog, null);
        dialog = new AlertDialog.Builder(this,R.style.noBackDialog).create();
        dialog.setView(inflate);
        Window w = dialog.getWindow();
        w.setBackgroundDrawableResource(R.color.transparent);
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.y=250;
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                closeMenu(sub);
            }
        });
    }

    // TODO: 16/8/21 exit action
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;
    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            nm.cancel(1);
            App.getInstance().exit();
        }
    }
}
