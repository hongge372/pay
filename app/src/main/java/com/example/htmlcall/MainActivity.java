package com.example.htmlcall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] permission ={ Manifest.permission.INTERNET };
        ActivityCompat.requestPermissions(this, permission, 1);
        runWebView();
    }

    private void runWebView(){
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl("file:///android_asset/request.html");
        //webView.loadUrl("http://www.baidu.com");
    }
}