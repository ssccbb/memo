package com.sung.note.memo.ui.note;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.sung.note.memo.R;
import com.sung.note.memo.control.DatabaseOperation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/*
* eidt article
* */
public class EditArticle extends AppCompatActivity implements View.OnClickListener{
    private EditText tittle,contant;
    private final static int PIC_CHOOSE_REQUES=0;
    private SQLiteDatabase db;
    private DatabaseOperation dop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_article);
        InitView();
    }

    private void InitView(){
        ImageView close= (ImageView) findViewById(R.id.close);
        close.setOnClickListener(this);
        ImageView history= (ImageView) findViewById(R.id.history);
        history.setOnClickListener(this);
        ImageView img= (ImageView) findViewById(R.id.load_pic);
        img.setOnClickListener(this);
        ImageView send= (ImageView) findViewById(R.id.send);
        send.setOnClickListener(this);

        //tittle edit
        tittle = (EditText) findViewById(R.id.tittle);

        //hide keyborad btn
        final ImageView hideKyeborad= (ImageView) findViewById(R.id.hide_keyborad);
        hideKyeborad.setVisibility(View.INVISIBLE);
        hideKyeborad.setOnClickListener(this);

        //contant edit
        contant= (EditText) findViewById(R.id.contant);
        contant.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b)
                    hideKyeborad.setVisibility(View.VISIBLE);
                else
                    hideKyeborad.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.hide_keyborad:
                contant.clearFocus();
                tittle.clearFocus();
                hideKeyboard();
                break;
            case R.id.close:
                finish();
                break;
            case R.id.send:
                saveArticle();
                break;
            case R.id.history:
                startActivity(new Intent().setClass(this, EditHistory.class));
                break;
            case R.id.load_pic:
                Toast.makeText(EditArticle.this, "pic", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditArticle.this, MultiImageSelectorActivity.class);
                // 是否显示调用相机拍照
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
                // 最大图片选择数量
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);
                // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者 多选/MultiImageSelectorActivity.MODE_MULTI)
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE);
                startActivityForResult(intent, PIC_CHOOSE_REQUES);
                break;
            default:
                break;
        }
    }

    private void saveArticle(){
        String tittle_text = tittle.getEditableText().toString().trim();
        String contant_text = contant.getEditableText().toString().trim();
        if (tittle_text.length()==0) {
            Toast.makeText(EditArticle.this, "标题为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (contant_text.length()==0) {
            Toast.makeText(EditArticle.this, "未获取到内容", Toast.LENGTH_SHORT).show();
            return;
        }

        dop=new DatabaseOperation(this,db);
        dop.create_db();
        //取得当前时间
        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy-MM-dd HH:mm");
        Date curDate   =   new   Date(System.currentTimeMillis());//获取当前时间
        String   time   =   formatter.format(curDate);
        dop.insert_db(tittle_text,contant_text,time);
        dop.close_db();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case PIC_CHOOSE_REQUES:
                if (data==null)
                    break;
                // 获取返回的图片列表
                ArrayList<String> pics =
                        data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                String pic = pics.get(0);
                Toast.makeText(EditArticle.this, pic, Toast.LENGTH_SHORT).show();
                // 处理你自己的逻辑 ....
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*
        * hide the keyborad
        * */
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
