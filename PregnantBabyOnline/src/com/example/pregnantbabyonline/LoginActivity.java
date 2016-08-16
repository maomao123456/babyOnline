package com.example.pregnantbabyonline;

import com.example.lei.MySQLiteOpenHelper;

import android.app.Activity;
import android.content.Intent;
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

public class LoginActivity extends Activity {

	EditText userAccount, userPassword;
	Button register, login, forgetPassword, question;
	TextView qq, sina, wechat;
	MySQLiteOpenHelper mysql;
	Cursor cursor;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		initView();
	}

	public void initView() {
		userAccount = (EditText) findViewById(R.id.login_account);
		userPassword = (EditText) findViewById(R.id.login_password);
		register = (Button) findViewById(R.id.register_now);
		login = (Button) findViewById(R.id.login_button);
		forgetPassword = (Button) findViewById(R.id.forget_password);
		question = (Button) findViewById(R.id.question);
		qq = (TextView) findViewById(R.id.qq);
		sina = (TextView) findViewById(R.id.sina_weibo);
		wechat = (TextView) findViewById(R.id.wechat);

		register.setOnClickListener(clickListener);
		login.setOnClickListener(clickListener);
		forgetPassword.setOnClickListener(clickListener);
		question.setOnClickListener(clickListener);
		qq.setOnClickListener(clickListener);
		sina.setOnClickListener(clickListener);
		wechat.setOnClickListener(clickListener);
	}

	OnClickListener clickListener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.register_now:
				registerNow();
				break;
			case R.id.login_button:

				if (checkEdit() == false) {
					checkEdit();
				} else if (checkEdit() == true) {
					login();
				}
				break;
			case R.id.forget_password:
				forgetPassword();
				break;
			default:
				break;
			}
		}
	};
	
	public void forgetPassword() {
		Intent intent = new Intent(LoginActivity.this, ForgetPwdActivity.class);
		startActivity(intent);
	}

	public void login() {
		Intent intent = new Intent(LoginActivity.this, ShouyeActivity.class);
		startActivity(intent);
		LoginActivity.this.finish();
	}

	// 跳转到注册页面的方法
	public void registerNow() {
		Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
		startActivity(intent);
	}

	

	private boolean checkEdit() {
		if (userAccount.getText().toString().trim().equals("")) {
			Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT)
					.show();
		} else if (userPassword.getText().toString().trim().equals("")) {
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
		String userName = userAccount.getText().toString();
		cursor = sqliteDatabase.query("Users", null, "userName='" + userName
				+ "'", null, null, null, null);
		return cursor.getCount();
	}

	public int checkedData() {// 确定用户登录时的信息是否正确
		mysql = new MySQLiteOpenHelper(LoginActivity.this, "Users", null, 1);
		SQLiteDatabase sqliteDatabase = mysql.getWritableDatabase();// 连接到本地的数据库
		String userName = userAccount.getText().toString();
		String password = userPassword.getText().toString();
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

	public void update() {// 修改密码
		String str = "用户修改后的密码";
		String name = "用户输入的名字";
		String sql = "update user set password = '" + str + "'"
				+ "where userName = '" + name + "'";
		MySQLiteOpenHelper mysql = new MySQLiteOpenHelper(LoginActivity.this,
				"Users", null, 1);
		SQLiteDatabase sqliteDatabase = mysql.getWritableDatabase();// 连接本地的数据库
		sqliteDatabase.execSQL(sql);
	}
}
