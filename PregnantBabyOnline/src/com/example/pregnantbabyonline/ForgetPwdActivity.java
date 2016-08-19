package com.example.pregnantbabyonline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.utils.SMSLog;

@SuppressLint("HandlerLeak")
public class ForgetPwdActivity extends Activity {

	String APPKEY = "1612b8e01b6a4";// 注册后你的APPKEY
	String APPSECRETE = "985806401e3f6b09f2e18c4b9ea95a75";
	ImageButton returnLogin;// 返回登录界面
	EditText phoneNum, phoneSMS;// 手机号输入框和验证码输入框
	Button getSMS, next;// 获取验证码的按钮和下一步的按钮
	int i = 60;// 获取验证码倒计时60秒

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_forget_password);

		returnLogin = (ImageButton) findViewById(R.id.back_login);
		phoneNum = (EditText) findViewById(R.id.input_phone);
		phoneSMS = (EditText) findViewById(R.id.input_phone_code);
		getSMS = (Button) findViewById(R.id.get_phone_code);
		next = (Button) findViewById(R.id.next_button);
		returnLogin.setOnClickListener(clickListener);
		getSMS.setOnClickListener(clickListener);
		next.setOnClickListener(clickListener);

		SMSSDK.initSDK(this, APPKEY, APPSECRETE, false);
		EventHandler eh = new EventHandler() {

			@Override
			public void afterEvent(int event, int result, Object data) {
				Message msg = new Message();
				msg.arg1 = event;
				msg.arg2 = result;
				msg.obj = data;
				mHandler.sendMessage(msg);
			}

		};
		SMSSDK.registerEventHandler(eh);
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.back_login:
				back();
				break;
			case R.id.get_phone_code:
				getsms(phoneNum.getText().toString().trim());
				break;
			case R.id.next_button:
				next();
				break;
			default:
				break;
			}
		}
	};

	// 下一步页面跳转方法
	public void next() {
		phString = phoneNum.getText().toString();
		Intent intent = new Intent();
		intent.putExtra("phoneNum", phString);
		intent.setClass(ForgetPwdActivity.this, ResetPwdActivity.class);
		startActivity(intent);
		finish();
	}

	// 返回方法
	public void back() {
		Intent intent = new Intent();
		intent.setClass(ForgetPwdActivity.this, LoginActivity.class);
		startActivity(intent);
		finish();
	}

	// 发送短信
	String phString;

	public void getsms(String str) {
		read(phoneNum.getText().toString().trim());
		SMSSDK.getVerificationCode("86", str);
		phString = str;

	}

	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {

			// TODO Auto-generated method stub
			super.handleMessage(msg);
			int event = msg.arg1;
			int result = msg.arg2;
			Object data = msg.obj;
			Log.e("event", "event=" + event);
			// System.out.println("--------result---0"+event+"--------*"+result+"--------"+data);

			if (result == SMSSDK.RESULT_COMPLETE) {
				if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
					Toast.makeText(getApplicationContext(), "发送验证码成功",
							Toast.LENGTH_SHORT).show();
				} else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
					next();
				}
			} else {
				@SuppressWarnings("unused")
				int status = 0;
				try {
					((Throwable) data).printStackTrace();
					Throwable throwable = (Throwable) data;
					JSONObject object = new JSONObject(throwable.getMessage());
					String des = object.optString("detail");
					status = object.optInt("status");
					if (!TextUtils.isEmpty(des)) {
						Toast.makeText(ForgetPwdActivity.this, des,
								Toast.LENGTH_SHORT).show();
						return;
					}
				} catch (Exception e) {
					SMSLog.getInstance().w(e);
				}
			}

		};
	};

	protected void onDestroy() {
		super.onDestroy();
		SMSSDK.unregisterAllEventHandler();
	};

	//判断用户是否存在
	String str1;

	public void read(final String phoneNum) {
		new Thread(new Runnable() {
			@Override
			public void run() {

				StringBuilder builder = new StringBuilder();
				try {
					String httpHost = "http://192.168.1.145/index.php/Home/api/read";
					String name = "useraccount=" + phoneNum;
					String urlName = httpHost + "?" + name;
					URL url = new URL(urlName);
					HttpURLConnection connection = (HttpURLConnection) url
							.openConnection();
					connection.setConnectTimeout(5000);
					connection.setRequestProperty("accept", "*/*");// 设置客户端接受那些类型的信息，通配符代表接收所有类型的数据
					connection.setRequestProperty("connection", "Keep-Alive");// 保持长链接
					connection
							.setRequestProperty("user-agent",
									"Mozilla/4.0(compatible;MSIE 6.0;Windows NT5.1;SV1)");// 设置浏览器代理
					connection
							.setRequestProperty("accept-charset", "utf-8;GBK");// 客户端接受的字符集
					connection.connect();// 建立连接
					InputStream inputStream = connection.getInputStream();
					Map<String, List<String>> headers = connection
							.getHeaderFields();
					for (String key : headers.keySet()) {
						System.out.println(key + "----" + headers.get(key));

					}
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(inputStream));
					String line = bufferedReader.readLine();
					while (line != null && line.length() > 0) {
						builder.append(line);
						line = bufferedReader.readLine();
					}
					bufferedReader.close();
					inputStream.close();
					Log.i("builder-----", builder.toString());
					str1 = builder.toString();
					phoneHandler.sendEmptyMessage(0);
				} catch (MalformedURLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}).start();
	}

	Handler phoneHandler = new Handler() {
		public void handleMessage(Message msg) {
			try {
				JSONObject jsonObject = new JSONObject(str1);
				int status = jsonObject.getInt("status");
				String message = jsonObject.getString("message");
				if (status == 2) {
					Toast.makeText(ForgetPwdActivity.this, message,
							Toast.LENGTH_SHORT).show();				
				} else {
					Toast.makeText(ForgetPwdActivity.this, message,
							Toast.LENGTH_SHORT).show();
					getsms(phoneNum.getText().toString());
				}

			} catch (JSONException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

		}
	};
}
