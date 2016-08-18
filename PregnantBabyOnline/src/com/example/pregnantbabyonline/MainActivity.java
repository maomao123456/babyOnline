package com.example.pregnantbabyonline;

import android.app.Activity;
import android.content.Intent;
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
	
	public void initView(){
		bord = (TextView) findViewById(R.id.born);
		pregnancying = (TextView) findViewById(R.id.pregnancying);
		forPregnancy = (TextView) findViewById(R.id.for_pregnancy);
		directLogin = (Button) findViewById(R.id.direct_login);
		directLogin.setOnClickListener(clickListener);
	}
	
	OnClickListener clickListener = new OnClickListener(){
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.direct_login:
				directLogin();	
				break;
			default:
				break;
			}
		}	
	};
	
	public void directLogin(){// 直接登录跳转到登录界面
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, LoginActivity.class);
		startActivity(intent);
	}

}
