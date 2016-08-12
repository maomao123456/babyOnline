package com.example.pregnantbabyonline;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends Activity {
	WebView webview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		webview = (WebView) findViewById(R.id.webview);
		webview.setWebViewClient(client);//webview的监听事件
		// 支持 JavaScript 方法
		webview.getSettings().setJavaScriptEnabled(true);
		String url = "https://www.baidu.com";
		// 1. 访问自带浏览器
		 webview.loadUrl(url);

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
	/**
	 * 手机上的 按键监听
	 */
	/*public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			//按返回后可以做的事 do something
		}
		return true;
	};*/

}
