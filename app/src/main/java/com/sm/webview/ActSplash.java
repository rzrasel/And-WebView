package com.sm.webview;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class ActSplash extends AppCompatActivity {
    private TextView sysTvProgress;
    private WebView sysWebView;
    //private String url = "https://www.google.com.sg";
    private String url = "http://www.prothom-alo.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);

        sysTvProgress = (TextView) findViewById(R.id.sysTvProgress);

        sysWebView = (WebView) findViewById(R.id.sysWebView);

        sysWebView.setWebChromeClient(new MyChromeClient());
        sysWebView.setWebViewClient(new myWebClient());

        //ON CLICK EVENT
        sysWebView.getSettings().setLoadsImagesAutomatically(true);
        sysWebView.getSettings().setJavaScriptEnabled(true);
        /*WebSettings webSettings = sysWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);*/
        sysWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        sysWebView.loadUrl(url);
    }

    public class myWebClient extends WebViewClient {
        ProgressDialog progressDialog;

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            System.out.println("your current url when webpage loading.." + url);
        }

        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            /*final Uri uri = Uri.parse(url);
            return handleUri(uri);*/
            System.out.println("when you click on any interlink on webview that time you got url :-" + url);
            //return super.shouldOverrideUrlLoading(view, url);
            view.loadUrl(url);
            return true;
        }

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            /*final Uri uri = request.getUrl();
            return handleUri(uri);*/
            System.out.println("when you click on any interlink on webview that time you got url :-" + url);
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        //Show loader on url load
        public void onLoadResource(WebView view, String url) {
            /*if (progressDialog == null) {
                // in standard case YourActivity.this
                progressDialog = new ProgressDialog(ActSplash.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
            }*/
            super.onLoadResource(view, url);
        }

        public void onPageFinished(WebView view, String url) {
            try {
                /*if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
                progressDialog.dismiss();*/
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            System.out.println("your current url when webpage loading.. finish" + url);
            super.onPageFinished(view, url);
        }

    }

    public class MyChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            try {
                sysTvProgress.setText(newProgress + "%");
                /*if (progressdialog.isShowing()) {
                    progressdialog.setMessage(getString(R.string.loading) + newProgress + " %");
                } else {
                    *//*
                     * webView.stopLoading(); webView.clearView();
                     *//*
                }*/
            } catch (Throwable e) {

                e.printStackTrace();
            }
        }
    }
}
/*
https://vlemonn.com/Blog/Android/Android-WebView-Display-Loading-Percentage/
Android - Camera
https://www.tutorialspoint.com/android/android_camera.htm
*/