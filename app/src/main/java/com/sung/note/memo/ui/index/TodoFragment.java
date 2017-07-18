package com.sung.note.memo.ui.index;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.sung.note.memo.R;
import com.sung.note.memo.control.DatabaseOperation;
import com.sung.note.memo.tools.View.SodukuListView;
import com.sung.note.memo.ui.note.ArticleDetail;

/**
 * A simple {@link Fragment} subclass.
 * homepage ,todolist
 */
public class TodoFragment extends Fragment {
    private View view;
    private SQLiteDatabase db;
    private DatabaseOperation dop;
    private String TAG="TodoFragment";

    public TodoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_memo, container, false);
        db=SQLiteDatabase.openOrCreateDatabase(getActivity().getFilesDir().toString()+"/mynotes.db3", null);
        dop=new DatabaseOperation(getActivity(),db);
        showArticle();
        return view;
    }

    private void showArticle(){
        SodukuListView list= (SodukuListView) view.findViewById(R.id.list);
        //创建或打开数据库
        dop.create_db();
        final Cursor cursor = dop.query_db();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(),
                R.layout.note_item,
                cursor,
                new String[]{"_id","title","context","time"},
                new int[]{R.id.tv_note_id,R.id.tv_note_title,R.id.tv_note_content,R.id.tv_note_time});
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e(TAG, "onItemClick: "+i );
                TextView num= (TextView) view.findViewById(R.id.tv_note_id);
                int id = Integer.parseInt(num.getText().toString());
                startActivity(new Intent().setClass(getActivity(), ArticleDetail.class).putExtra("_id",id));
            }
        });
        dop.close_db();
    }
}
