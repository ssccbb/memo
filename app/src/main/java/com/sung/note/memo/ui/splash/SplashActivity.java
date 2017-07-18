package com.sung.note.memo.ui.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.sung.note.memo.R;
import com.sung.note.memo.application.App;
import com.sung.note.memo.ui.index.MemoIndexActivity;

public class SplashActivity extends AppCompatActivity {
    private ImageView img;
    private final static int INVISIBLE_ICON=1;
    private Handler uiHand=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case INVISIBLE_ICON:
                    img.setVisibility(View.VISIBLE);
                    Animation anim= AnimationUtils.loadAnimation(SplashActivity.this,R.anim.from_alph);
                    img.setAnimation(anim);
                    anim.start();
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            try {
                                Thread.sleep(2000);
                                int version = Integer.valueOf(android.os.Build.VERSION.SDK);
                                Intent intent=new Intent();
                                intent.setClass(SplashActivity.this, MemoIndexActivity.class);
                                finish();
                                startActivity(intent);
                                if(version > 5 ){
                                    overridePendingTransition(R.anim.from_alph, R.anim.to_alph);
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        App.getInstance().addActivity(SplashActivity.this);
        initView();
        Intent2Index();
    }

    private void initView(){
        img = (ImageView) findViewById(R.id.img);
        img.setVisibility(View.INVISIBLE);
    }

    private void Intent2Index(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    uiHand.sendEmptyMessage(INVISIBLE_ICON);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
