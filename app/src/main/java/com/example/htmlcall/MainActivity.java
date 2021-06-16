package com.example.htmlcall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
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

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //if (url.startsWith("weixin://wap/pay?") || url.startsWith("http://weixin/wap/pay")) {
                   if(true){
                    try {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        // 处理错误
                    }
                    return true;
                }
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, android.net.http.SslError error) {
                //重写此方法可以让webview处理https请求
                handler.proceed();
            }

        });

        //webView.loadUrl("file:///android_asset/request.html");
        //webView.loadUrl("http://www.baidu.com");
        //success
        //webView.loadUrl(" https://wx.tenpay.com/cgi-bin/mmpayweb-bin/checkmweb?prepay_id=wx20161110163838f231619da20804912345&package=1037687096");

        //最终参考网页：
        // https://juejin.cn/post/6844903542134669319
        webView.loadUrl("https://qr.alipay.com/stx01744jxpniu1ijb5wr7d");
    }
}