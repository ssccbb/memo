package com.sung.note.memo.tools.Others;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by 天旺 on 2015/9/7.
 */
public class StorageTools {
    public void writeFile(Context context,String string,String fileName){
        try {
            FileOutputStream outputStream=context.openFileOutput(fileName, Context.MODE_PRIVATE|Context.MODE_WORLD_READABLE);
//            Log.e("TAG",""+fileName);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            return;
        } catch (IOException e) {
            return;
        }
    }
    public String readFile(Context context,String fileName){
        ByteArrayOutputStream stream= new ByteArrayOutputStream();
        try {
            FileInputStream inputStream=context.openFileInput(fileName);
            byte[] buffer=new byte[1024];
            int length=1;
            while ((length=inputStream.read(buffer))!=-1){
//                Log.e("==length=",length+"");
                stream.write(buffer,0,length);
            }
            stream.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  stream.toString();
    }

    public void writeFileWithPath(String pathName,String fileName,String s) {
//        Log.e("TAG","pathName:"+pathName+",fileName:"+fileName+",str:"+s);
        String sdStatus = Environment.getExternalStorageState();
        if(!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
            Log.e("TestFile", "SD card is not avaiable/writeable right now.");
            return;
        }
        try {
            File path = new File(pathName+"/");
            File file = new File(pathName +"/"+ fileName);
            if( !path.exists()) {
//                Log.e("TestFile", "Create the path:" + pathName);
                path.mkdir();
            }
            if( !file.exists()) {
//                Log.e("TestFile", "Create the file:" + fileName);
                file.createNewFile();
            }
//            Log.e("TAG",file.getAbsolutePath().toString());
            FileOutputStream stream = new FileOutputStream(file);
            byte[] buf = s.getBytes();
            stream.write(buf);
            stream.close();
        } catch(Exception e) {
            Log.e("TestFile", "Error on writeFilToSD.");
            e.printStackTrace();
        }
    }

    public String readFileWithPath(String pathName,String fileName) {
        StringBuffer sb = new StringBuffer();
        try {
            File file = new File(pathName, fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String readline = "";
            while ((readline = br.readLine()) != null) {
//                System.out.println("readline:" + readline);
                sb.append(readline);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
