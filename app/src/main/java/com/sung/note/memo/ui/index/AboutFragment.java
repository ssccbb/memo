package com.sung.note.memo.ui.index;


import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sung.note.memo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment implements View.OnClickListener{
    private View view;

    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_about, container, false);

        initUI();
        return view;
    }

    private void initUI(){
        Typeface typeface = Typeface.createFromAsset(getResources().getAssets(), "fonts/china.ttf");
        TextView hint= (TextView) view.findViewById(R.id.hint);
        hint.setTypeface(typeface);

        typeface = Typeface.createFromAsset(getResources().getAssets(), "fonts/english.otf");
        TextView about= (TextView) view.findViewById(R.id.about);
        about.setOnClickListener(this);
        about.setTypeface(typeface);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.about:
                break;
            default:
                break;
        }
    }
}
