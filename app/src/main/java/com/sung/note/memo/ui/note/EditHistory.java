package com.sung.note.memo.ui.note;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.TextView;

import com.komi.slider.SliderUtils;
import com.sung.note.memo.R;
import com.sung.note.memo.tools.ToolBar.ToolBarActivity;

/**
 * Created by sung on 16/7/17.
 */
public class EditHistory extends ToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_list_activity);
        SliderUtils.attachActivity(this,null);
        initBar("选择本地草稿",R.color.blue);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initBar(String tittle,int color){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        android.support.v7.widget.Toolbar toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.id_tool_bar);
        toolbar.setBackground(getResources().getDrawable(color));
        TextView text= (TextView) toolbar.findViewById(R.id.tittle);
        text.setText(tittle);
    }

    @Override
    public void finish() {
        super.finish();
    }
}
