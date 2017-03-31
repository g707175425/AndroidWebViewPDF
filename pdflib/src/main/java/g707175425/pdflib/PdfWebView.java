package g707175425.pdflib;

import android.content.Context;
import android.os.Environment;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.File;

/**
 * Created by gongyasen on 2017/3/31.
 */

public class PdfWebView extends android.webkit.WebView {
    private String path;

    public PdfWebView(Context context) {
        this(context,null);
    }

    public PdfWebView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PdfWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getSettings().setJavaScriptEnabled(true);//让web可以运行js
        String url = "file:///android_asset/pdfviewer/index.html";
        setInitialScale(100);
        addJavascriptInterface(new WebViewBridge(getContext()), "pdfBridge");
        loadUrl(url);
        setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                loadFilePath(path);
            }
        });
    }

    public void loadFilePath(String path){
        this.path = path;
        loadUrl("javascript:download('"+path +"')");
    }


}
