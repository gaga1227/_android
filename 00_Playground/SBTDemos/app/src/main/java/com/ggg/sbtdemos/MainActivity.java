package com.ggg.sbtdemos;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get webview
        WebView webview = (WebView) findViewById(R.id.webView);

        // tweak settings
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webview.getSettings().setAllowFileAccessFromFileURLs(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setUseWideViewPort(false);
        if ((android.os.Build.VERSION.SDK_INT) >= 23) {
            webview.getSettings().setOffscreenPreRaster(true);
        }
        if ((android.os.Build.VERSION.SDK_INT) >= 21) {
            webview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        // loading webview URL
        webview.loadUrl("https://m.sportsbet.com.au");
    }
}
