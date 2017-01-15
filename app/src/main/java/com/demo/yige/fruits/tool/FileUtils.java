package com.demo.yige.fruits.tool;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yige on 2016/12/23.
 */

public class FileUtils {
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        Log.i("TAG","sdCardExist="+sdCardExist);
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();
        } else {
            return null;
        }
        return sdDir.toString();
    }

    public static String getFileName(Context context) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return "IMG_" + format.format(date);
    }

    public static void createPath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static File getImageFile(Context context) {
        Log.i("MSG??", "SD---" + getSDPath());
        if (getSDPath() == null) {
            return null;
        }
        createPath(getSDPath() + "/fruits");
        createPath(getSDPath() + "/fruits" + "/image");
        return new File(getSDPath() + "/fruits" + "/image", getFileName(context) + ".png");
    }

    public static File getImgNameFile(Context context,String name) {
        Log.i("MSG??", "SD---" + getSDPath());
        if (getSDPath() == null) {
            return null;
        }
        createPath(getSDPath() + "/fruits");
        createPath(getSDPath() + "/fruits" + "/image");
        Log.i("TAG",getSDPath() + "/fruits" + "/image");
        return new File(getSDPath() + "/fruits" + "/image", name);
    }
}
