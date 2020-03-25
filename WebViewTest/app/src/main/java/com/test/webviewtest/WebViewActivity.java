package com.test.webviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWebView;
    private ImageButton mBackImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        initView();
        initEvent();
    }

    private void initView() {
        mWebView = findViewById(R.id.web_view);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        mWebView.getSettings().setBlockNetworkImage(false);
        mBackImageButton = findViewById(R.id.back_button);
    }

    private void initEvent() {
        mWebView.loadUrl("https://m.qidian.com");
        mBackImageButton.setOnClickListener((view) -> finish());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
