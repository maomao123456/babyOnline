package com.example.pregnantbabyonline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.lei.MySQLiteOpenHelper;

public class LoginActivity extends Activity {

	private SharedPreferences preferences;//使用SharedPreferences来记录程序的使用次数
	EditText phoneNum, password;
	Button registerNow, login, forgetPassword, haveQuestion;
	TextView QQ, sina, wechat;
	MySQLiteOpenHelper mysql;
	Cursor cursor;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		
		//读取SharedPreferences中需要的数据
		preferences = getSharedPreferences("count", MODE_WORLD_READABLE);
		int count = preferences.getInt("count", 0);//登录程序的次数，初始值为0
		//判断程序是第几次运行，若是第一次运行则跳转到引导页面
		if(count == 0){
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), MainActivity.class);
			startActivity(intent);
			this.finish();
		}
		Editor editor = preferences.edit();
		//存入数据
		editor.putInt("count", ++count);
		//提交修改
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

	/*
	 * OnClickListener l = new OnClickListener() { public void onClick(View v) {
	 * switch (v.getId()) { case R.id.register_now: // 立即注册跳转到注册界面 Intent
	 * register = new Intent(LoginActivity.this, RegisterActivity.class);
	 * startActivity(register); break; case R.id.login_button: // 登录 break; case
	 * R.id.forget_password:// 忘记密码跳转到忘记密码界面 Intent forpwd = new
	 * Intent(LoginActivity.this, ForgetPwdActivity.class);
	 * startActivity(forpwd); break; case R.id.question: // 遇到问题 break; case
	 * R.id.qq: // QQ break; case R.id.sina_weibo: // 新浪微博 break; case
	 * R.id.wechat: // 微信 break; default: break; } } };
	 */

	OnClickListener l = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.register_now:
				Intent register = new Intent(LoginActivity.this,
						RegisterActivity.class);
				startActivity(register);
				break;
			case R.id.login_button:
				if (checkEdit() == false) {
					checkEdit();
				} else if (checkEdit() == true) {
					login();
				}
				break;
			case R.id.forget_password:
				Intent forpwd = new Intent(LoginActivity.this,
						ForgetPwdActivity.class);
				startActivity(forpwd);
				break;
			default:
				break;
			}
		}
	};

	String result;
	public void loginRead() {
		new Thread() {
			public void run() {

				try {
					// 获取输入框的内容
					//String phoneNums = phoneNum.getText().toString();
					//String phonePwds = password.getText().toString();
					//连接到PHP登录接口
					URL url = new URL("http://192.168.1.145");
					HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
					InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream());
					BufferedReader br = new BufferedReader(isr);
					result = br.readLine();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public void login() {
		Intent intent = new Intent(LoginActivity.this, ShouyeActivity.class);
		startActivity(intent);
		LoginActivity.this.finish();
	}

	private boolean checkEdit() {
		if (phoneNum.getText().toString().trim().equals("")) {
			Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT)
					.show();
		} else if (password.getText().toString().trim().equals("")) {
			Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT)
					.show();
		} else if (checkedData() == 0) {
			checkedData();
		} else {
			checkedData();
			return true;
		}
		return false;
	}

	public int checkData2() {// 判断登录的账号是否存在在数据库中
		mysql = new MySQLiteOpenHelper(LoginActivity.this, "Users", null, 1);
		SQLiteDatabase sqliteDatabase = mysql.getWritableDatabase();// 连接到本地的数据库
		String userName = phoneNum.getText().toString();
		cursor = sqliteDatabase.query("Users", null, "userName='" + userName
				+ "'", null, null, null, null);
		return cursor.getCount();
	}

	public int checkedData() {// 确定用户登录时的信息是否正确
		mysql = new MySQLiteOpenHelper(LoginActivity.this, "Users", null, 1);
		SQLiteDatabase sqliteDatabase = mysql.getWritableDatabase();// 连接到本地的数据库
		String userName = phoneNum.getText().toString();
		String userPwd = password.getText().toString();
		cursor = sqliteDatabase.query("Users", null, "userName='" + userName
				+ "'and password='" + password + "'", null, null, null, null);
		if (cursor.getCount() == 0 && checkData2() != 0) {// 用户存在而获取的输入框中的内容不正确，提示用户名或密码错误
			Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT)
					.show();
		} else if (cursor.getCount() == 0 && checkData2() == 0) {// 用户不存在在数据库中
			Toast.makeText(LoginActivity.this, "用户不存在", Toast.LENGTH_SHORT)
					.show();
		} else {
			Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT)
					.show();
		}
		return cursor.getCount();
	}

	/*
	 * public void update() {// 修改密码 String str = "用户修改后的密码"; String name =
	 * "用户输入的名字"; String sql = "update user set password = '" + str + "'" +
	 * "where userName = '" + name + "'"; MySQLiteOpenHelper mysql = new
	 * MySQLiteOpenHelper(LoginActivity.this, "Users", null, 1); SQLiteDatabase
	 * sqliteDatabase = mysql.getWritableDatabase();// 连接本地的数据库
	 * sqliteDatabase.execSQL(sql); }
	 */
}
