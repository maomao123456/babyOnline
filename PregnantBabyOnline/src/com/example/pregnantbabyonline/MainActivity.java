package com.example.pregnantbabyonline;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView bord, pregnancying, forPregnancy;
	Button directLogin;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide_page);
		initView();
	}

	public void initView() {
		bord = (TextView) findViewById(R.id.born);
		pregnancying = (TextView) findViewById(R.id.pregnancying);
		forPregnancy = (TextView) findViewById(R.id.for_pregnancy);
		directLogin = (Button) findViewById(R.id.direct_login);
		directLogin.setOnClickListener(clickListener);
	}

	OnClickListener clickListener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.direct_login:
				directLogin();
				break;
			case R.id.born:
				num = "宝宝";
				selectShenFen();
				break;
			case R.id.pregnancying:
				num = "怀孕中";
				selectShenFen();
				break;
			case R.id.for_pregnancy:
				num = "备孕";
				selectShenFen();
				break;
			default:
				break;
			}
		}
	};

	String num;

	@SuppressLint("WorldWriteableFiles")
	public void selectShenFen() {
		SharedPreferences sp = getSharedPreferences("User", MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("身份", num);
		editor.commit();
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, LoginActivity.class);
		startActivity(intent);
		finish();
	}

	/**
	 * 获取引导页身份状态
	 */
	@SuppressWarnings("unused")
	public void getShenFen() {
		String str;//用户点击时获取的身份状态
		SharedPreferences sp = getSharedPreferences("User", MODE_PRIVATE);
		String str1 = sp.getString("身份", "没得");
		if (str1.equals("宝宝")) {
			str = str1;
		} else if (str1.equals("怀孕中")) {
			str = str1;
		} else if (str1.equals("备孕")) {
			str = str1;
		}
	}

	public void directLogin() {// 直接登录跳转到登录界面
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, LoginActivity.class);
		startActivity(intent);
	}

}
