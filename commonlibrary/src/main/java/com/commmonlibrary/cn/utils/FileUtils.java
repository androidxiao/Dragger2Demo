package com.commmonlibrary.cn.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by chawei on 2018/7/6.
 */

public class FileUtils {


    /**
     * 使用递归获取目录文件大小
     *
     * @param dir
     * @return
     */
    public static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file); // 递归调用继续统计
            }
        }
        return dirSize;
    }


    /**
     * 创建未存在的文件夹
     *
     * @param file
     * @return
     */
    public static File makeDirs(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }


    /**
     * 使用递归删除文件夹
     *
     * @param dir
     * @return
     */
    public static boolean deleteDir(File dir) {
        if (dir == null) {
            return false;
        }
        if (!dir.isDirectory()) {
            return false;
        }
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                deleteDir(file); // 递归调用继续删除
            }
        }
        return true;
    }


    /**
     * 返回缓存文件夹
     */
    public static File getCacheFile(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(getCacheFilePath(context));
            makeDirs(file);
            return file;
        } else {
            return context.getCacheDir();
        }
    }

    /**
     * 获取自定义缓存文件地址
     *
     * @param context
     * @return
     */
    public static String getCacheFilePath(Context context) {
        String packageName = context.getPackageName();
        return Environment.getExternalStorageDirectory().toString() + "/" + packageName;
    }

    private static int count = 0;

    /**
     * 使用递归算出文件夹中文件（文件夹和文件）个数
     * @param filepath
     * @return
     */
    public static int getFileCount(String filepath) {
        File file = new File(filepath);
        if (file == null) {
            return -1;
        }
        File[] listfile = file.listFiles();
        for (int i = 0; i < listfile.length; i++) {
            if (!listfile[i].isDirectory()) {
                String temp = listfile[i].toString().substring(7, listfile[i].toString().length());
                LogUtil.debug("temp==" + temp);
                ++count;
                LogUtil.debug("文件" + count + "---path=" + listfile[i]);
            } else {
                getFileCount(listfile[i].toString());
            }
        }

        return count;
    }

}
