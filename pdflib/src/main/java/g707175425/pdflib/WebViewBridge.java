package g707175425.pdflib;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by gongyasen on 2017/3/31.
 */

public class WebViewBridge {

    private final Context context;
    private final Gson gson;

    public WebViewBridge(Context context){
        this.context = context;
        this.gson = new Gson();
    }


    @JavascriptInterface
    public String getByte(final String url) {
        try {
            return gson.toJson(file2byte(new FileInputStream(url)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @JavascriptInterface
    public void time(String pre){
        String time = pre+"时间:" + System.currentTimeMillis();
        System.out.println(time);
        Toast.makeText(context, time, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void start(){
        Toast.makeText(context, "开始加载", Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void end(){
        Toast.makeText(context, "加载完成", Toast.LENGTH_SHORT).show();
    }

    /**
     * 加载bcmap
     * @param name bcmap名
     * @return
     */
    @JavascriptInterface
    public String getBCMap(String name){
        try {
            String json = gson.toJson(file2byte(context.getAssets().open("pdfviewer/" +name + ".bcmap")));
            System.out.println("json:"+json);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] file2byte(InputStream fis) {
        byte[] buffer = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
}
