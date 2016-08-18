package com.example.pregnantbabyonline;

import com.example.fragment.WoFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FeedbackActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		Button button=(Button)findViewById(R.id.button);
		button.setOnClickListener(onClickListener);
		
		Button button1=(Button)findViewById(R.id.button1);
		button1.setOnClickListener(onClickListener2);
	}
	
	OnClickListener onClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(FeedbackActivity.this, "谢谢您的提交", Toast.LENGTH_SHORT).show();
		}
	};
	OnClickListener onClickListener2=new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(FeedbackActivity.this, WoFragment.class);
			startActivity(intent);
			FeedbackActivity.this.finish();
		}
	};

}
