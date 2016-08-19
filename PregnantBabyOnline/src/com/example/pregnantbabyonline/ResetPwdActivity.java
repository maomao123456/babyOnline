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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ResetPwdActivity extends Activity {

	EditText newPassword, confirmNewPassword;
	Button confirmBtn;
	ImageButton back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reset_password);
		initView();
	}

	public void initView() {
		newPassword = (EditText) findViewById(R.id.new_password);
		confirmNewPassword = (EditText) findViewById(R.id.confirm_new_password);
		confirmBtn = (Button) findViewById(R.id.confirm_button);
		back = (ImageButton) findViewById(R.id.back_forgetpwd);
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.back_forgetpwd:
				Intent intent = new Intent();
				intent.setClass(ResetPwdActivity.this, ForgetPwdActivity.class);
				startActivity(intent);
				finish();
				break;

			default:
				break;
			}
		}
	};

	public void complete(String str) {
		if (judgePassword(str)) {
			if (str.equals(confirmNewPassword.getText().toString().trim())) {

			}
		}
	}

	// 验证密码格式是否正确
	public boolean judgePassword(String password) {
		String str = "^([A-Za-z] | [0-9]) + $";// 判断密码格式
		if (TextUtils.isEmpty(password)) {
			// 若密码参数为空
			return false;
		} else {
			if (password.length() > 6 && password.length() < 16) {
				return password.matches(str);
			} else {
				return false;
			}
		}

	}

	String str;

	public void resetPassword(final String useraccount, final String password) {
		new Thread(new Runnable() {
			public void run() {
				StringBuilder builder = new StringBuilder();
				try {
					String httpPost = "http://192.168.1.145/index.php/Home/Api/update";
					String name = "useraccount=" + useraccount
							+ "&userpassword=" + password;
					String urlName = httpPost + "?" + name;
					URL url = new URL(urlName);
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.setConnectTimeout(5000);
					conn.setRequestProperty("accept", "*/*");// 设置客户端接受信息的类型，通配符代表所有的数据类型
					conn.setRequestProperty("connection", "Keep-Alive");// 保持长连接
					conn.setRequestProperty("user-agent",
							"Mozilla/4.0(compatible;MSIE 6.0;Windows NT5.1;SV1)");// 设置浏览器代理
					conn.setRequestProperty("accept-charset", "utf-8;GBK");// 客户端接受的字符集
					conn.connect();// 建立连接
					InputStream is = conn.getInputStream();
					Map<String, List<String>> headers = conn.getHeaderFields();
					for (String key : headers.keySet()) {
						System.out.print(key + "----" + headers.get(key));
					}
					BufferedReader buff = new BufferedReader(
							new InputStreamReader(is));
					String line = buff.readLine();
					while (line != null && line.length() > 0) {
						builder.append(line);
						line = buff.readLine();
					}
					buff.close();
					is.close();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
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
				// int status = jsonObject.getInt("status");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

}
