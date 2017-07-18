package com.sung.note.memo.tools.PicUtils;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by sung on 16/5/11.
 */
public class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {
    private final WeakReference<ImageView> imageViewReference;
    private String data = null;
    private ImageDecodeUtils idu;

    public BitmapWorkerTask(ImageView imageView) {
        // Use a WeakReference to ensure the ImageView can be garbage collected
        imageViewReference = new WeakReference<ImageView>(imageView);
    }


    // Decode image in background.
    @Override
    protected Bitmap doInBackground(String... params) {
        data = params[0];
        String[] strings = data.split("/");
        String[] split = (strings[strings.length - 1]).split("/.");
        Log.e("ViewHolder", "doInBackground " + data);
        idu = new ImageDecodeUtils();
//        boolean flag=true;
//        if (params[2].equals("true")){
//            flag=true;
//        }else {
//            flag=false;
//        }
//        Bitmap comp = idu.comp(idu.returnBitmap(data), Integer.parseInt(params[1]),flag);
//        Bitmap comp = idu.compressImage(idu.returnBitmap(data));
        //bitmap/本地保存路径/文件名
        Bitmap comp = idu.returnBitmap(data);
        idu.saveBitmap(comp, "/sdcard/yrksCache/yrks_marchant", split[0]);
        return comp;
    }


    // Once complete, see if ImageView is still around and set bitmap.
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (imageViewReference != null && bitmap != null) {
            final ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
