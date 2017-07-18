package com.sung.note.memo.tools.Interface;

import com.sung.note.memo.tools.View.ObservableScrollView;

/**
 * Created by sung on 16/3/29.
 */
public interface ScrollViewListener {

    void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy);

}
