package com.sung.note.memo.ui.note;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sung.note.memo.R;
import com.sung.note.memo.control.DatabaseOperation;
import com.sung.note.memo.tools.ToolBar.ToolBarActivity;

public class ArticleDetail extends AppCompatActivity {
    private int id;
    private String TAG="ArticleDetail";

    private SQLiteDatabase db;
    private DatabaseOperation dop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        getId();
    }

    private void getId(){
        id = this.getIntent().getIntExtra("_id",0);
        Log.e(TAG, "getId: "+id);

        setData(id);
    }

    private void setData(int id){
        db=SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/mynotes.db3", null);
        dop=new DatabaseOperation(ArticleDetail.this,db);
        Cursor cursor = dop.query_db(id);
    }
}
