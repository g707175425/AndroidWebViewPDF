package com.example.gongyasen.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import g707175425.pdflib.PdfWebView;

/**
 * pdf js for android
 * Created by gongyasen on 2017/3/31.
 */

public class MainActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PdfWebView webview = (PdfWebView) findViewById(R.id.webview);
        String path = new File(Environment.getExternalStorageDirectory(), "1.pdf")
                .getAbsolutePath();
        assets2Sd(MainActivity.this,"1.pdf",path);
        webview.loadFilePath(path);
    }

    public static void assets2Sd(Context context, String fileAssetPath, String fileSdPath){
        //测试把文件直接复制到sd卡中 fileSdPath完整路径
        try {
            copyBigDataToSD(context, fileAssetPath, fileSdPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyBigDataToSD(Context context, String fileAssetPath, String strOutFileName) throws IOException
    {
        InputStream myInput;
        OutputStream myOutput = new FileOutputStream(strOutFileName);
        myInput = context.getAssets().open(fileAssetPath);
        byte[] buffer = new byte[1024];
        int length = myInput.read(buffer);
        while(length > 0)
        {
            myOutput.write(buffer, 0, length);
            length = myInput.read(buffer);
        }

        myOutput.flush();
        myInput.close();
        myOutput.close();
    }
}
