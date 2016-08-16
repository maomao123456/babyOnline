package com.example.pregnantbabyonline;

import com.example.lei.MySQLiteOpenHelper;
import com.example.lei.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class RegisterActivity extends Activity {

	EditText regAccount, regPwd, checkPwd;
	Button register;
	ImageButton backLogin;	
	int num;
	MySQLiteOpenHelper mysql;// 数据库类
	SQLiteDatabase sqliteDatabase;// 数据库sqliteDatabase
	Cursor cursor;//获得数据库的表中的对象
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		initView();

	}

	public void initView() {
		regAccount = (EditText) findViewById(R.id.register_account);
		regPwd = (EditText) findViewById(R.id.register_pwd);
		checkPwd = (EditText) findViewById(R.id.check_pwd);
		register = (Button) findViewById(R.id.register_button);
		backLogin = (ImageButton) findViewById(R.id.regidter_back);
		register.setOnClickListener(clickListener);
		backLogin.setOnClickListener(clickListener);

	}

	OnClickListener clickListener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.register_button:
				if(checkedEdit()==false){//	判断输入框内容，FALSE重新调用checkedEdit()方法
					checkedEdit();
				}else{
					Toast.makeText(RegisterActivity.this, "注册成功", 3000).show();
					saveData();// 在注册成功之后把用户的信息保存在本地的数据库
					backLogin();// 自动跳转回登录界面
				}
				break;
			case R.id.regidter_back:
				backLogin();// 再点击<按钮后，返回登录界面
				break;
			default:
				break;
			}
		}
	};

	public boolean checkedEdit() {
		String userAccount = regAccount.getText().toString();// 获取到输入的手机号码
		//辨别    是否是手机号
		boolean result = Utils.isRightPhone(userAccount);//判断用户输入的手机号码是否是正确的格式
		String userPassword = regPwd.getText().toString();// 获取到密码框的内容
		String rePassword = checkPwd.getText().toString();// 获取到确认密码框瘤内容
		if (TextUtils.isEmpty(userAccount)) {// 判断用户输入框的内容是否为空
			regAccount.setError("用户名不能为空");// 提示输入框中的用户名不能为空
		} else if (result == false) {
			regAccount.setError("请输入正确的手机号");
		} else if (checkData() != 0) {// 在数据库中注册的账号已存在
			Toast.makeText(RegisterActivity.this, "用户已存在", Toast.LENGTH_SHORT)
					.show();
		} else if (TextUtils.isEmpty(userPassword)) {
			regPwd.setError("密码不能为空");
		} else if (TextUtils.isEmpty(rePassword)) {
			checkPwd.setError("确认密码不能为空");
		} else if (!userPassword.equals(rePassword)) {
			Toast.makeText(RegisterActivity.this, "密码和确认密码不一致！ 请重新输入", 2000).show();
		} else {
			return true;
		}
		return false;
	}

	public void saveData() {// 保存数据到数据库
		final ContentValues cv = new ContentValues();// 插入数据
		mysql = new MySQLiteOpenHelper(
				RegisterActivity.this, "Users", null, 1);
		sqliteDatabase = mysql.getWritableDatabase();// 连接本地的数据库
		String userName = regAccount.getText().toString();
		String password = regPwd.getText().toString();
		cv.put("userName", userName);
		cv.put("password", password);
		sqliteDatabase.insert("Users", null, cv);
	}
	
	public int checkData() {// 数据库中存在的用户
		mysql = new MySQLiteOpenHelper(
				RegisterActivity.this, "Users", null, 1);
		sqliteDatabase = mysql.getWritableDatabase();// 连接本地瘤数据库
		String userName = regAccount.getText().toString();
		// String password = regPwd.getText().toString();
		cursor = sqliteDatabase.query("Users", null, "userName='"
				+ userName + "'", null, null, null, null);
		return cursor.getCount();
	}

	private void backLogin() {// 返回登录界面方法
		Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
		startActivity(intent);
	}

}
