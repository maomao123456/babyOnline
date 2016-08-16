package com.example.pregnantbabyonline;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import com.example.lei.MySQLiteOpenHelper;
import com.example.lei.Utils;

@SuppressLint("HandlerLeak")
public class ForgetPwdActivity extends Activity implements OnClickListener {

	String APPKEY = "1612b8e01b6a4";// 注册后你的APPKEY
	String APPSECRETE = "985806401e3f6b09f2e18c4b9ea95a75";
	ImageButton returnLogin;// 返回登录界面
	EditText phoneNum, phoneSMS;// 手机号输入框和验证码输入框
	Button getSMS, next;// 获取验证码的按钮和下一步的按钮
	int i = 60;// 获取验证码倒计时60秒
	MySQLiteOpenHelper mysql;// 数据库类
	SQLiteDatabase sqliteDatabase;// 数据库sqliteDatabase
	Cursor cursor;//获得数据库的表中的对象

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_forget_password);
		initView();
	}
	
	//初始化控件
	private void initView(){
		returnLogin = (ImageButton) findViewById(R.id.back_login);
		phoneNum = (EditText) findViewById(R.id.input_phone);
		phoneSMS = (EditText) findViewById(R.id.input_phone_code);
		getSMS = (Button) findViewById(R.id.get_phone_code);
		next = (Button) findViewById(R.id.next_button);
		getSMS.setOnClickListener(this);
		next.setOnClickListener(this);
		
		//启动短信验证SDK
		SMSSDK.initSDK(this, APPKEY, APPSECRETE);
		EventHandler eventHandler = new EventHandler(){
			public void afterEvent(int event, int result, Object data){
				Message msg = new Message();
				msg.arg1 = event;
				msg.arg2 = result;
				msg.obj = data;
				handler.sendMessage(msg);
			}
		};
		//注册回调监听接口
		SMSSDK.registerEventHandler(eventHandler);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String phoneNums = phoneNum.getText().toString();
		switch(v.getId()){
		case R.id.input_phone:
			//通过规则判断手机号码
			if(!judgePhoneNums(phoneNums)){
				return;
			}
			//通过SDK发送短信验证
			SMSSDK.getVerificationCode("86", phoneNums);
			//把按钮变成不可点击,并且显示倒计时(正在获取)
			getSMS.setClickable(false);
			getSMS.setText("重新发送(" + i + ")");
			new Thread(new Runnable(){
				public void run(){
					for(; i > 0; ){
						handler.sendEmptyMessage(-9);
						if(i <= 0){
							break;
						}	
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					handler.sendEmptyMessage(-8);
				}
			}).start();
			break;
		case R.id.next_button:
			//将收到的验证码和手机号提交再次核对
			SMSSDK.submitVerificationCode("86", phoneNums, phoneNum.getText().toString());
			
			break;
		default:
			break;
		}
	}
	
	Handler handler = new Handler(){
		@SuppressWarnings("unused")
		public void handlerMessage(Message msg){
			if(msg.what == -9){
				getSMS.setText("重新发送(" + i + ")");
			} else if(msg.what == -8){
				getSMS.setText("获取验证码");
				getSMS.setClickable(true);//把获取验证码按钮设置为可点击
				i = 60;//重新设置获取验证码的时间
			} else {
				int event = msg.arg1;
				int result = msg.arg2;
				Object data = msg.obj;
				if(result == SMSSDK.RESULT_COMPLETE){
					//短信注册成功后，提示验证码提交成功
					if(event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
						Toast.makeText(getApplicationContext(), "验证码提交成功", Toast.LENGTH_SHORT).show();
					} else if(event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
						Toast.makeText(getApplicationContext(), "验证码提交成功", Toast.LENGTH_SHORT).show();	
					} else {
						((Throwable) data).printStackTrace();
					}
				}
			}
		}
	};

	//判断手机号码输入是否正确
	private boolean judgePhoneNums(String phoneNums){
		boolean result = Utils.isRightPhone(phoneNums);
		if(result){
			return true;
		}else{
			Toast.makeText(ForgetPwdActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
		}
		return false;
	}
	
	//注销
	protected void onDestroy(){
		SMSSDK.unregisterAllEventHandler();
		super.onDestroy();
	}
	/*// 初始化控件
	public void initView() {
		returnLogin = (ImageButton) findViewById(R.id.back_login);
		phoneNum = (EditText) findViewById(R.id.input_phone);
		code = (EditText) findViewById(R.id.input_phone_code);
		getCode = (Button) findViewById(R.id.get_phone_code);
		next = (Button) findViewById(R.id.next_button);

		// 启动短信验证SDK
		SMSSDK.initSDK(ForgetPwdActivity.this, APPKEY, APPSECRETE);
		EventHandler eventHandler = new EventHandler() {
			public void afterEvent(int event, int result, Object data) {
				Message msg = new Message();
				msg.arg1 = event;
				msg.arg2 = result;
				msg.obj = data;
				handler.sendMessage(msg);
			}
		};
		// 注册回调监听接口
		SMSSDK.registerEventHandler(eventHandler);
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
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

	// 返回登录界面
	public void backToLogin() {
		Intent intent = new Intent(ForgetPwdActivity.this, LoginActivity.class);
		startActivity(intent);
	}

	// 跳转到重新设置密码界面
	public void nextToReset() {
		String userAccount = phoneNum.getText().toString();
		// 将收到的验证码和手机号提交再次核对
		SMSSDK.submitVerificationCode("86", userAccount, phoneNum.getText()
				.toString());
	}

	// 获取验证密码
	public void getCode() {
		String userAccount = phoneNum.getText().toString();
		// 通过规则判断受手机号码
		if (!judgePhoneNums(userAccount)) {
			return;
		}
		// 通过SDK发送短信验证
		SMSSDK.getVerificationCode("86", userAccount);
		// 把按钮变成不可点击,并且显示倒计时(正在获取)
		getCode.setClickable(false);
		getCode.setText("重新发送(" + i + ")");
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (; i > 0; i--) {
					handler.sendEmptyMessage(-9);
					if (i < 0) {
						break;
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				handler.sendEmptyMessage(-8);
			}

		}).start();
	}

	//
	Handler handler = new Handler() {
		@SuppressWarnings("unused")
		public void handlerMessage(Message msg) {
			if (msg.what == -9) {
				getCode.setText("重新发送(" + i + ")");
			} else if (msg.what == -8) {
				getCode.setText("获取验证码");
				getCode.setClickable(true);
				i = 60;
			} else {
				int event = msg.arg1;
				int result = msg.arg2;
				Object data = msg.obj;

				if (result == SMSSDK.RESULT_COMPLETE) {
					// 短信注册成功之后，跳转到重置密码界面，然后提示
					if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
						// 验证码提交成功
						Toast.makeText(ForgetPwdActivity.this, "提价验证码成功",
								Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(ForgetPwdActivity.this,
								ResetPwdActivity.class);
						startActivity(intent);
					} else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
						Toast.makeText(ForgetPwdActivity.this, "正在获取验证码",
								Toast.LENGTH_SHORT).show();
					} else {
						((Throwable) data).printStackTrace();
					}
				}
			}
		}
	};

	// 判断手机号的输入是否正确
	private boolean judgePhoneNums(String phoneNums) {
		boolean result = Utils.isRightPhone(phoneNums);
		if (isMatchLength(phoneNums, 11) && result) {
			return true;
		}else {
		Toast.makeText(ForgetPwdActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT)
				.show();
		}
		if(cursor.getCount() == 0){
			Toast.makeText(ForgetPwdActivity.this, "找不到用户，请先注册", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(ForgetPwdActivity.this, RegisterActivity.class);
			startActivity(intent);
		}
		return false;
	}
	
	//判断获取到的用户账号是否存在
	public int checkData2() {// 判断登录的账号是否存在在数据库中
		mysql = new MySQLiteOpenHelper(
				ForgetPwdActivity.this, "Users", null, 1);
		SQLiteDatabase sqliteDatabase = mysql.getWritableDatabase();// 连接到本地的数据库
		String userName = phoneNum.getText().toString();
		cursor = sqliteDatabase.query("Users", null, "userName='"
				+ userName + "'", null, null, null, null);		
		return cursor.getCount();
	}
	
	// 判断一个字符串的位数
	public static boolean isMatchLength(String str, int length) {
		if (str.isEmpty()) {
			return false;
		} else {
			return str.length() == length ? true : false;
		}
	}

	protected void onDestroy() {
		SMSSDK.unregisterAllEventHandler();
		super.onDestroy();
	}*/
}
