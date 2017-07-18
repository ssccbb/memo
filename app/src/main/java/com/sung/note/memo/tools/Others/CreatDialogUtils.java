package com.sung.note.memo.tools.Others;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.sung.note.memo.R;

/**
 * Created by sung on 16/3/31.
 */
public class CreatDialogUtils {
    /**
     * 得到自定义的progressDialog
     * @param context
     * @return
     */
    public Dialog createLoadingDialog(Context context) {

        Dialog loadingDialog = null;// 创建自定义样式dialog
        if (context!=null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.dialog_layout, null);// 得到加载view
            LinearLayout layout = (LinearLayout) v.findViewById(R.id.loading_view);// 加载布局
            // 创建自定义样式dialog
            loadingDialog = new Dialog(context, R.style.loading_dialog);
            WindowManager.LayoutParams lp = loadingDialog.getWindow().getAttributes();
            // 设置背景层透明度
            lp.dimAmount = 0.9f;
            loadingDialog.getWindow().setAttributes(lp);
//            loadingDialog.setCancelable(false);// 不可以用“返回键”取消
            loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.FILL_PARENT));// 设置布局
            loadingDialog.setCanceledOnTouchOutside(false);//点击外部不可取消
            return loadingDialog;
        }
        return loadingDialog;
    }
}
