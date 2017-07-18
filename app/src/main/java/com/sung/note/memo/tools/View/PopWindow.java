package com.sung.note.memo.tools.View;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sung on 16/5/3.
 */
public class PopWindow extends PopupWindow{
    /*private View contentView;
    private List<ServiceListBean> listBeans=new ArrayList<>();

    public PopWindow(Activity context,List<ServiceListBean> list,int width,int height){
        LayoutInflater inflater= (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView=inflater.inflate(R.layout.popwindow_view,null);
        this.setContentView(contentView);
        this.setWidth(width);
        this.setHeight(height);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationPreview);
        Log.e("PopWindow", "PopWindow "+list);
        if (list==null){
            initList();
            listBeans=list;
        }else {
            if (list.size()==0){
                initList();
                listBeans=list;
            }
        }

        CommonAdapter adapter=new CommonAdapter<ServiceListBean>(context,list,R.layout.item_provincelist) {
            @Override
            public void convert(ViewHolder helper, ServiceListBean item) {
                helper.setText(R.id.provincetext,item.getText());
            }
        };
        SodukuListView listView= (SodukuListView) contentView.findViewById(R.id.listview);
        listView.setAdapter(adapter);

    }

    private void initList(){
        listBeans.add(new ServiceListBean("育苗服务","1"));
        listBeans.add(new ServiceListBean("专家咨询","2"));
    }

    *//**
     * 显示popupWindow
     *
     * @param parent
     *//*
    public void showPopupWindow(View parent) {
        if (parent!=null) {
            if (!this.isShowing()) {
                // 以下拉方式显示popupwindow
                this.showAsDropDown(parent, 0, 0);
            } else {
                this.dismiss();
            }
        }else {
            this.dismiss();
        }
    }*/
}
