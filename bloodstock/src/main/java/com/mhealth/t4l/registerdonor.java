package com.mhealth.t4l;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mhealth.sqlite.R;

/**
 * Created by HP on 5/26/2016.
 */
public class registerdonor extends AppCompatActivity {
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerdonor);

        WebView mWebView=(WebView) findViewById(R.id.webviewdonor);
        // Stop local links and redirects from opening in browser instead of WebView
        mWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings =mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setInitialScale(1);
        //mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setUseWideViewPort(true);
        //mWebView.setKeepScreenOn(true);
        mWebView.loadUrl("http://rd.chanjo.co.ke/");

        final Activity MyActivity = this;
        mWebView.setWebChromeClient(new WebChromeClient()
        {
            public void onProgressChanged(WebView view, int progress)
            {
                MyActivity.setTitle("Loading...");
                MyActivity.setProgress(progress * 100);

                if(progress == 100)
                    MyActivity.setTitle(R.string.register_donor);
            }
        });


    }





}

