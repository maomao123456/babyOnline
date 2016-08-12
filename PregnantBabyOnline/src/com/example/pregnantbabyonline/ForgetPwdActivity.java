package com.example.pregnantbabyonline;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ForgetPwdActivity extends Activity {

	ImageButton returnLogin;
	EditText phoneNum,code;
	Button getCode,next;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forget_password);
		initView();
	}
	
	public void initView(){
		returnLogin = (ImageButton) findViewById(R.id.back_login);
		phoneNum = (EditText) findViewById(R.id.input_phone);
		code = (EditText) findViewById(R.id.input_phone_code);
		getCode = (Button) findViewById(R.id.get_phone_code);
		next = (Button) findViewById(R.id.next_button);
	}
	
	OnClickListener clickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.back_login:
				backToLogin();
				break;
			case R.id.next_button:
				nextToReset();
				break;
			default:
				break;
			}
		}
	};
	
	public void backToLogin(){//返回登录界面
		Intent intent = new Intent(ForgetPwdActivity.this, LoginActivity.class);
		startActivity(intent);
	}
	
	public void nextToReset(){//跳转到重新设置密码界面
		Intent intent = new Intent(ForgetPwdActivity.this, ResetPwdActivity.class);
		startActivity(intent);
	}
}
