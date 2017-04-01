# AndroidWebViewPDF
此Demo集成pdf.js使Android WebView支持浏览pdf,jpg,png格式,相比mupdf更为简单且轻量,只需要几行代码即可支持加载本地pdf(受制于pdf.js性能影响,如需加载大型pdf对显示速度有需求,请使用mupdf)

### 预览效果
   <img src="https://github.com/g707175425/AndroidWebViewPDF/blob/master/preview.png" width = "271" height = "478" alt="预览效果" align=center />

### 使用
```xml
xml中引入控件
<g707175425.pdflib.PdfWebView
    android:id="@+id/webview"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

```java
  //使用此方法打开指定路径
  webview.loadFilePath(path);
```

### 混淆
由于用到了js和java桥
需要proguard-project.txt 或者 proguard-rules.pro中加入如下配置:
```
-keep class cn.schope.lightning.activity.PdfViewerActivity.WebViewBridge.**{*;}
```
  
  
