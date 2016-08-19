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
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("WorldReadableFiles")
public class LoginActivity extends Activity {

	private SharedPreferences preferences;// 使用SharedPreferences来记录程序的使用次数
	EditText phoneNum, password;
	Button registerNow, login, forgetPassword, haveQuestion;
	TextView QQ, sina, wechat;

	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);

		//引导页面的跳转
		// 读取SharedPreferences中需要的数据
		preferences = getSharedPreferences("count", MODE_WORLD_READABLE);
		int count = preferences.getInt("count", 0);// 登录程序的次数，初始值为0
		// 判断程序是第几次运行，若是第一次运行则跳转到引导页面
		if (count == 0) {
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), MainActivity.class);
			startActivity(intent);
			this.finish();
		}
		Editor editor = preferences.edit();
		// 存入数据
		editor.putInt("count", ++count);
		// 提交修改
		editor.commit();
		initView();
	}

	// 初始化
	public void initView() {
		phoneNum = (EditText) findViewById(R.id.login_account);
		password = (EditText) findViewById(R.id.login_password);
		registerNow = (Button) findViewById(R.id.register_now);
		login = (Button) findViewById(R.id.login_button);
		forgetPassword = (Button) findViewById(R.id.forget_password);
		haveQuestion = (Button) findViewById(R.id.question);
		QQ = (TextView) findViewById(R.id.qq);
		sina = (TextView) findViewById(R.id.sina_weibo);
		wechat = (TextView) findViewById(R.id.wechat);

		// 点击事件
		registerNow.setOnClickListener(l);// 立即注册点击事件
		login.setOnClickListener(l);// 登录点击事件
		forgetPassword.setOnClickListener(l);// 忘记密码点击事件
		haveQuestion.setOnClickListener(l);// 遇到问题点击事件
		// 第三方登录点击事假
		QQ.setOnClickListener(l);// QQ登录点击事件
		sina.setOnClickListener(l);// 新浪微博点击事件
		wechat.setOnClickListener(l);// 微信点击事件

	}

	OnClickListener l = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.register_now:
				// 立即注册
				Intent register = new Intent(LoginActivity.this,
						RegisterActivity.class);
				startActivity(register);
				finish();
				break;
			case R.id.login_button:
				if (!checkEdit()) {
					return;
				}
				login();
				break;
			case R.id.forget_password:
				// 忘记密码
				Intent forpwd = new Intent(LoginActivity.this,
						ForgetPwdActivity.class);
				startActivity(forpwd);
				break;
			case R.id.question:
				Toast.makeText(LoginActivity.this, "沒有", Toast.LENGTH_SHORT)
				.show();
				break;
			case R.id.qq:
				loginQQ();
				break;
			case R.id.sina_weibo:
				loginWeibo();
				break;
			case R.id.wechat:
				loginWechat();
				break;
			default:
				break;
			}
		}
	};

	// 登录验证账号密码方法
	public void login() {
		loginType(phoneNum.getText().toString(), password.getText().toString());
		
		finish();
	}
	
	//QQ登录
	public void loginQQ(){
		Toast.makeText(LoginActivity.this, "QQ没有实现", Toast.LENGTH_SHORT)
		.show();
		finish();
	}
	
	//微博登录
	public void loginWeibo(){
		Toast.makeText(LoginActivity.this, "微博没有实现", Toast.LENGTH_SHORT)
		.show();
		finish();
	}
	
	//微信登录
	public void loginWechat(){
		Toast.makeText(LoginActivity.this, "微信没有实现", Toast.LENGTH_SHORT)
		.show();
		finish();
	}

	// 判断输入框内容是否为空
	public boolean checkEdit() {
		if (phoneNum.getText().toString().trim().equals("")) {
			Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT)
					.show();
		} else if (password.getText().toString().equals("")) {
			Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT)
					.show();
		} else {
			return true;
		}
		return false;
	}

	// 登录时判断用户是否存在
	String str;

	public void loginType(final String phoneNum, final String password) {
		new Thread(new Runnable() {
			@Override
			public void run() {

				StringBuilder builder = new StringBuilder();
				try {
					String httpHost = "http://192.168.1.145/index.php/Home/Api/login";// php接口
					String name = "useraccount=" + phoneNum + "&userpassword="
							+ password;
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
					str = builder.toString();
					myHandler.sendEmptyMessage(0);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}).start();
	}

	@SuppressLint("HandlerLeak")
	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			try {
				JSONObject jsonObject = new JSONObject(str);
				int status = jsonObject.getInt("status");
				String message = jsonObject.getString("message");
				if (status == 2) {
					Toast.makeText(LoginActivity.this, message,
							Toast.LENGTH_SHORT).show();// 密码错误，提示用户名和密码错误
				} else if (status == 3) {
					Toast.makeText(LoginActivity.this, message,
							Toast.LENGTH_SHORT).show();// 当输入的用户名不存在时，提示用户不存在
				} else {
					Intent intent = new Intent();
					intent.setClass(LoginActivity.this, ShouYeActivity.class);// 登录成功，跳转到首页
					startActivity(intent);
					LoginActivity.this.finish();
				}

			} catch (JSONException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

		}
	};
}
