package com.drs24.sdfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.os.Environment.MEDIA_MOUNTED;

public class MainActivity extends AppCompatActivity {

    // 声明一个数组，用来存储所有需要动态申请的权限
    private String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

//    // 声明一个集合，在后面的代码中用来存储用户拒绝授权的权
//    private List<String> mPermissionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        checkPermission();

        request();

        isSdCardExist();

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

//        Log.e("dir", "" + getSdCardPath());

        //指定路徑
        File path = Environment.getExternalStorageDirectory();
        File path1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File lu = new File(path1, "Lumify");
        File data = new File(lu, "727989544480550_Quick ID_");
//        Log.e("dirr", "" + path.toString());
//
//        getFilePath(this, "");
//        Log.e("dirrr", "" + getFilePath(this, ""));
//
//        getFilesAllName(path.getAbsolutePath());
//        Log.e("dirrr", "" + path.getAbsolutePath());
//        System.out.println();
        filePath(lu);
    }

    private void request() {
        ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,},
                100);
    }

    private void filePath(File file) {
//        File[] files = file.listFiles();
//        for (File file1 : files) {
//            if(file1.listFiles() != null){
//                Log.e("file", "file is " + file1);
//            }else {
//                Log.e("file", "文件不存在");
//            }
//        }

        File[] files = file.listFiles();
        for (File file1 : files) {
            String time=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(file1.lastModified()));
            Log.e("file", "file is " + file1.getName());
            Log.e("fileTTime", "file is " + time);
        }

//
//        if (file != null && file.exists() && file.isDirectory()) {
//            File[] files = file.listFiles();
//            for (File file2 : files) {
//                if (file2.listFiles() == null) {
//                    String str = "文件名称:" + file2.getName() + " 路径:" + file2.getPath();
//                    Log.e("dirrr", "" + str);
//                } else {
//                    filePath(file2);
//                }
//            }
//        } else {
//            System.out.println("文件不存在......");
//        }
    }

    public static List<String> getFilesAllName(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        if (files == null) {
            Log.e("error", "空目录");
            return null;
        }
        List<String> s = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            s.add(files[i].getAbsolutePath());
        }
        return s;
    }

    public static String getFilePath(Context context, String dir) {
        String directoryPath = "";
        if (MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {//判断外部存储是否可用
            directoryPath = context.getExternalFilesDir(dir).getAbsolutePath();
        } else {//没外部存储就使用内部存储
            directoryPath = context.getFilesDir() + File.separator + dir;
        }
        File file = new File(directoryPath);
        if (!file.exists()) {//判断文件目录是否存在
            file.mkdirs();
        }
        return directoryPath;
    }

//    private void checkPermission() {
//        mPermissionList.clear();
////        /**
////         * 判断哪些权限未授予
////         * 以便必要的时候重新申请
////         */
//        for (String permission : permissions) {
//            if (ContextCompat.checkSelfPermission(this, permission) !=
//                    PackageManager.PERMISSION_GRANTED) {
//                mPermissionList.add(permission);
//            }
//        }
//        /**
//         * 判断存储委授予权限的集合是否为空
//         */
//        if (!mPermissionList.isEmpty()) {
//            String [] permissions1 = mPermissionList.toArray(new String[mPermissionList.size()]);
//            ActivityCompat.requestPermissions(this, permissions1, 1);
//        } else {//未授予的权限为空，表示都授予了
//            // 后续操作...
//        }
//    }

    public static boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(
                MEDIA_MOUNTED);
    }

    public static String getSdCardPath() {
        boolean exist = isSdCardExist();
        String sdpath = "";
        if (exist) {
            sdpath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();
        } else {
            sdpath = "不适用";
        }
        return sdpath;

    }

}
