package com.ggg.sbtdemos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    /**
     * vars
     */
    String mainUrl = "https://m.sportsbet.com.au";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get webview
        WebView webview = (WebView) findViewById(R.id.webView);

        // set ChromeClient to webview so it can handle page navigation
        webview.setWebViewClient(new WebViewClient());

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

        // get intent
        Intent mainIntent = getIntent();
        String intentUrl = mainIntent.getStringExtra("SBT_DEEPLINK");

        // replace main url with passed in url if available
        if (intentUrl != null && intentUrl.length() > 0) {
            mainUrl = intentUrl;
        }

        // loading webview URL
        webview.loadUrl(mainUrl);
        Log.i("INFO", "WebView URL: " + mainUrl);
    }
}
