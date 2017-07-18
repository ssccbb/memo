package com.sung.note.memo.ui.index;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sung.note.memo.R;
import com.sung.note.memo.ui.note.EditArticle;

/**
 * A simple {@link Fragment} subclass.
 * ariticle list
 */
public class NoteFragment extends Fragment implements View.OnClickListener{
    private View view;

    public NoteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_note, container, false);
        initUI();
        return view;
    }

    private void initUI(){
        TextView edit= (TextView) view.findViewById(R.id.edit);
        edit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edit:
                startActivity(new Intent().setClass(getActivity(), EditArticle.class));
                break;
            default:
                break;
        }
    }
}
