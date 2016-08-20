package com.example.pregnantbabyonline;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyWebActivity extends Activity{
	WebView webview;
	LinearLayout back;
	TextView title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mywebview);
		initView();
		webview.setWebViewClient(client);//WebView的监听
		webview.getSettings().setJavaScriptEnabled(true);// 支持 JavaScript 方法
		String url=getIntent().getExtras().getString("uri");
		webview.loadUrl(url);
	}
	/**
	 * 找id
	 */
	public void initView(){
		webview=(WebView)findViewById(R.id.mywebview);
		back=(LinearLayout)findViewById(R.id.imageview_back_mywebview);
		title=(TextView)findViewById(R.id.textview_webview);
	}
	/**
	 * 返回上一级点击的方法
	 * @param view
	 */
	public void onClick(View view){
		MyWebActivity.this.finish();
	}
	WebViewClient client = new WebViewClient() {
		public void onReceivedHttpAuthRequest(WebView view,
				HttpAuthHandler handler, String host, String realm) {

		};
		// 加载前 进行中可以做的事
		public void onPageStarted(WebView view, String url,
				android.graphics.Bitmap favicon) {
		};
		// 加载完成那一刻 可以做的事
		public void onPageFinished(WebView view, String url) {

		};
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			return true;// ֻtrue 不调用系统自带浏览器 自己加载
		};

	};
}
