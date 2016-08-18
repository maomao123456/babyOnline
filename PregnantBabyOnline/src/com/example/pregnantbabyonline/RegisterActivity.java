package com.example.pregnantbabyonline;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.lei.Utils;

public class RegisterActivity extends Activity {

	EditText regAccount, regPwd, checkPwd;
	Button register;
	ImageButton backLogin;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		initView();

	}

	public void initView() {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		regAccount = (EditText) findViewById(R.id.register_account);
		regPwd = (EditText) findViewById(R.id.register_pwd);
		checkPwd = (EditText) findViewById(R.id.check_pwd);
		register = (Button) findViewById(R.id.register_button);
		backLogin = (ImageButton) findViewById(R.id.regidter_back);
		regAccount.setOnFocusChangeListener(changeListener);
		regPwd.setOnFocusChangeListener(changeListener);
		checkPwd.setOnFocusChangeListener(changeListener);
		register.setOnClickListener(clickListener);
		backLogin.setOnClickListener(clickListener);

	}

	OnFocusChangeListener changeListener = new OnFocusChangeListener() {
		public void onFocusChange(View v, boolean hasFocus) {
			String phoneNum = regAccount.getText().toString();
			boolean phoneType = Utils.isRightPhone(phoneNum);
			String phonePwd = regPwd.getText().toString();
			String conPwd = checkPwd.getText().toString();
			switch (v.getId()) {
			case R.id.register_account:
				if (!hasFocus) {
					if (phoneType == false) {
						Toast.makeText(RegisterActivity.this, "请输入正确的手机号码",
								Toast.LENGTH_SHORT).show();
					}
				}
				break;
			case R.id.register_pwd:
				if (!hasFocus) {
					if (phonePwd.length() < 6) {
						Toast.makeText(RegisterActivity.this, "密码不能少于6个字符",
								Toast.LENGTH_SHORT).show();
					}
				}
				break;
			case R.id.check_pwd:
				if (!hasFocus) {
					if (conPwd.equals(phonePwd)) {
						Toast.makeText(RegisterActivity.this,
								"两次密码输入不一致,请重新输入", Toast.LENGTH_SHORT).show();
					}
				}
				break;
			default:
				break;
			}
		}
	};

	OnClickListener clickListener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.register_button:
				if (!checkEdit()) {
					return;
				}
				complete();
				break;
			case R.id.regidter_back:
				backLogin();// 再点击<按钮后，返回登录界面
				break;
			default:
				break;
			}
		}
	};

	private boolean checkEdit() {
		if (regAccount.getText().toString().trim().equals("")) {
			Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT)
					.show();
		} else if (regPwd.getText().toString().trim().equals("")) {
			Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT)
					.show();
		} else if (checkPwd.getText().toString().trim().equals("")) {
			Toast.makeText(RegisterActivity.this, "确认密码不能为空",
					Toast.LENGTH_SHORT).show();
		} else {
			return true;
		}
		return false;
	}

	public void complete() {
		new Thread(new Runnable() {
			public void run() {
				String httpUrl = "http://192.168.1.145/index.php/Home/Api/add";
				HttpPost httpRequest = new HttpPost(httpUrl);
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("useraccount", regAccount
						.getText().toString().trim()));
				params.add(new BasicNameValuePair("userpassword", regPwd
						.getText().toString().trim()));
				HttpEntity httpentity = null;
				try {
					httpentity = new UrlEncodedFormEntity(params, "utf8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				httpRequest.setEntity(httpentity);
				HttpClient httpClient = new DefaultHttpClient();
				HttpResponse httpResponse = null;
				try {
					httpResponse = httpClient.execute(httpRequest);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					String strResult = null;
					try {
						strResult = EntityUtils.toString(httpResponse
								.getEntity());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Looper.prepare();
					Toast.makeText(RegisterActivity.this, strResult + "注册成功",
							Toast.LENGTH_SHORT).show();
					Looper.loop();
				} else {
					Looper.prepare();
					Toast.makeText(RegisterActivity.this, "用户已存在",
							Toast.LENGTH_SHORT).show();
					Looper.loop();
				}
			}
		}).start();
	}

	private void backLogin() {// 返回登录界面方法
		Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
		startActivity(intent);
	}

}
