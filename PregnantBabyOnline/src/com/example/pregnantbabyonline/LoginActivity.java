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
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lei.MySQLiteOpenHelper;

@SuppressLint("WorldReadableFiles")
public class LoginActivity extends Activity {

	private SharedPreferences preferences;// 使用SharedPreferences来记录程序的使用次数
	EditText phoneNum, password;
	Button registerNow, login, forgetPassword, haveQuestion;
	TextView QQ, sina, wechat;
	MySQLiteOpenHelper mysql;
	Cursor cursor;

	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);

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
				/*if (checkEdit() == false) {
					checkEdit();
				} else if (checkEdit() == true) {
					login();
				}*/
				login();
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
	
	// 登录验证账号密码方法
		public void login() {
			logintype(phoneNum.getText().toString(), password.getText().toString());
			}
	
	String str;
	public void logintype(final String phoneNum ,final String password) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				StringBuilder builder = new StringBuilder();
				try {
					String httpHost = "http://192.168.1.145/index.php/Home/Api/login";
					String name = "useraccount="+phoneNum+"&userpassword="+password;
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
					str = builder.toString();
					myHandler.sendEmptyMessage(0);
					
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
	
	Handler myHandler = new Handler() {  
        public void handleMessage(Message msg) {   
        	try {
    			JSONObject jsonObject = new JSONObject(str);
    			int status = jsonObject.getInt("status");
    			String message = jsonObject.getString("message");
    			if(status==2){
    			//prompt.setText(message);
    				Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    			
    			}else if(status == 3){
    				Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    			}else{
    				Intent intent = new Intent();
    				intent.setClass(LoginActivity.this, ShouyeActivity.class);
    				startActivity(intent);
    			}
    			
    		} catch (JSONException e) {
    			// TODO 自动生成的 catch 块
    			e.printStackTrace();
    		}
            
        }   
   };
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/*String result;

	public void loginRead() {
		new Thread() {
			public void run() {
				// StringBuilder builder = new StringBuilder();
				String httpUrl = "http://192.168.1.145/index.php/Home/Api/login";// 接口
				HttpPost httpRequest = new HttpPost(httpUrl);// 用post方法请求HTTP数据
				// 插入数据
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("useraccount", phoneNum
						.getText().toString().trim()));
				params.add(new BasicNameValuePair("userpassword", password
						.getText().toString().trim()));
				try {
					// 请求设置参数
					HttpEntity httpEntity = new UrlEncodedFormEntity(params,
							"utf8");
					httpRequest.setEntity(httpEntity);
					HttpClient httpClient = new DefaultHttpClient();
					// 发送请求
					HttpResponse httpResponse = httpClient.execute(httpRequest);
					if (httpResponse.getStatusLine().getStatusCode() == 200) {

					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}*/

	/*// 验证输入框是否有内容
	private boolean checkEdit() {
		if (phoneNum.getText().toString().trim().equals("")) {
			Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT)
					.show();
		} else if (password.getText().toString().trim().equals("")) {
			Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT)
					.show();
		} else {
			return true;
		}
		return false;
	}*/

	/*public void login() {
		Intent intent = new Intent(LoginActivity.this, ShouyeActivity.class);
		startActivity(intent);
		LoginActivity.this.finish();
	}*/

	/*public int checkData2() {// 判断登录的账号是否存在在数据库中
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
		String pwd = password.getText().toString();
		cursor = sqliteDatabase.query("Users", null, "userName='" + userName
				+ "'and password='" + pwd + "'", null, null, null, null);
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
	}*/

	/*
	 * public void update() {// 修改密码 String str = "用户修改后的密码"; String name =
	 * "用户输入的名字"; String sql = "update user set password = '" + str + "'" +
	 * "where userName = '" + name + "'"; MySQLiteOpenHelper mysql = new
	 * MySQLiteOpenHelper(LoginActivity.this, "Users", null, 1); SQLiteDatabase
	 * sqliteDatabase = mysql.getWritableDatabase();// 连接本地的数据库
	 * sqliteDatabase.execSQL(sql); }
	 */
}
