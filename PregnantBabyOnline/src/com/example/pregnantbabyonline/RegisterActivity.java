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
	MySQLiteOpenHelper mysql;//�������ݿ���
	SQLiteDatabase sqliteDatabase;//�������ݿ�
	Cursor cursor;
	
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
				if(checkedEdit()==false){//������������ΪFALSE������checkedEdit()����
					checkedEdit();
				}else{
					Toast.makeText(RegisterActivity.this, "注册成功", 3000).show();
					saveData();//�������ݵ����ݿ�
					backLogin();//���ص�¼����
				}
				break;
			case R.id.regidter_back:
				backLogin();//���ص�¼����
				break;
			default:
				break;
			}
		}
	};

	public boolean checkedEdit() {
		String userAccount = regAccount.getText().toString();// ��ȡ��������˺�
		//辨别    是否是手机号
		boolean result = Utils.isRightPhone(userAccount);//�ж�����ĵ绰�����˺��Ƿ���ȷ(����Utils������isRightPhone()����)
		String userPassword = regPwd.getText().toString();// ��ȡ�����������
		String rePassword = checkPwd.getText().toString();// ��ȡ�������ȷ������
		if (TextUtils.isEmpty(userAccount)) {// �ж�������Ƿ�����������
			regAccount.setError("用户名不能为空");
		} else if (result == false) {
			regAccount.setError("请输入正确的手机号");
		} else if (checkData() != 0) {//�˺���ע��
			Toast.makeText(RegisterActivity.this, "用户已存在", Toast.LENGTH_SHORT)
					.show();
		} else if (TextUtils.isEmpty(rePassword)) {
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

	public void saveData() {//�����û�������˺ŵ����ݿ���
		final ContentValues cv = new ContentValues();// ��ֵ��
		mysql = new MySQLiteOpenHelper(
				RegisterActivity.this, "Users", null, 1);
		sqliteDatabase = mysql.getWritableDatabase();// ������д�����ݿ�
		String userName = regAccount.getText().toString();
		String password = regPwd.getText().toString();
		cv.put("userName", userName);
		cv.put("password", password);
		sqliteDatabase.insert("Users", null, cv);
	}
	
	public int checkData() {// ��ע��ʱ��֤ע���˺��Ƿ����
		mysql = new MySQLiteOpenHelper(
				RegisterActivity.this, "Users", null, 1);
		sqliteDatabase = mysql.getWritableDatabase();// ������д�����ݿ�
		String userName = regAccount.getText().toString();
		// String password = regPwd.getText().toString();
		cursor = sqliteDatabase.query("Users", null, "userName='"
				+ userName + "'", null, null, null, null);
		return cursor.getCount();
	}

	private void backLogin() {// ���ص�¼ҳ��
		Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
		startActivity(intent);
	}

}
