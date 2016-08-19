package com.example.pregnantbabyonline;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
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
	}
	
	public void initView(){
		newPassword = (EditText) findViewById(R.id.new_password);
		confirmNewPassword = (EditText) findViewById(R.id.confirm_new_password);
		confirmBtn = (Button) findViewById(R.id.confirm_button);
		back = (ImageButton) findViewById(R.id.back_forgetpwd);
	}
	
	public void resetPassword(final String useraccount){
		new Thread(new Runnable(){
			public void run(){
				StringBuilder builder = new StringBuilder();
				String httpPost = "http://192.168.1.138/index.php/Home/Api/update";
				String name = "useraccount=" + useraccount;
				String urlName = httpPost + name;
				try {
					URL url = new URL(urlName);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
}
