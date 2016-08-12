package com.example.pregnantbabyonline;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ForgetPwdActivity extends Activity {

	ImageButton returnLogin;
	EditText phoneNum,code;
	Button getCode,next;
	private int countSeconds = 60;//倒计时秒数
	private String usersuccess;
	private Context context;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forget_password);
		initView();
	}
	
	private Handler countHandler = new Handler(){
		public void handleMessage(Message msg){
			super.handleMessage(msg);
			if(countSeconds > 0){
				--countSeconds;
				getCode.setText("("+ countSeconds +")后获取验证码");
				countHandler.sendEmptyMessageDelayed(0, 1000);
			}else{
				countSeconds = 60;
				getCode.setText("请重新获取验证码");
			}
		}
	};
	
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
	
	public void getCode(){
		if(countSeconds == 60){
			String mobile = phoneNum.getText().toString();
			getMobile(mobile);
		}
	}
	
	private void getMobile(String mobile){
		if("".equals(mobile)){
			new AlertDialog.Builder(context).setTitle("提示").setMessage("手机号码不正确");
		}
	}
}
