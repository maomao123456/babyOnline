package com.example.pregnantbabyonline;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

	TextView bord, pregnancying, forPregnancy;
	Button directLogin;
	SharedPreferences preferences;
	private Editor editor;	
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide_page);
		initView();
		
		/*preferences = getSharedPreferences("count", Context.MODE_PRIVATE);
		new Handler().postDelayed(new Runnable(){
			public void run() {
				if(preferences.getBoolean("firststart", true)){//�ж��û��ǲ��ǵ�һ�ε�¼App
					editor = preferences.edit();
					//����¼��־λ����Ϊfalse���´ε�¼ʱ������ʾ����ҳ��
					editor.putBoolean("firststart", false);
					editor.commit();
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, RegisterActivity.class);
					MainActivity.this.startActivity(intent);
					MainActivity.this.finish();
				}else {
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, LoginActivity.class);
					MainActivity.this.startActivity(intent);
					MainActivity.this.finish();
				}
			}}, 0);*/
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
	
	public void directLogin(){//ֱ�ӵ�¼��ֱ����ת����¼����
		Intent intent = new Intent(MainActivity.this, LoginActivity.class);
		startActivity(intent);
		MainActivity.this.finish();
	}
	
//	public void yinDao(){
//		preferences = getSharedPreferences("count", MODE_WORLD_READABLE);;
//		int count = preferences.getInt("count", 1);
//		
//		//�жϳ���ڼ������У�����ǵ�һ����������ת������ҳ��
//		if(count == 0){
//			Intent intent = new Intent();
//			intent.setClass(getApplicationContext(), LoginActivity.class);
//			startActivity(intent);
//			this.finish();
//		}
//		Editor editor = preferences.edit();
//		//��������
//		editor.putInt("count", ++count);
//		//�ύ�޸�
//		editor.commit();
//	}

}
