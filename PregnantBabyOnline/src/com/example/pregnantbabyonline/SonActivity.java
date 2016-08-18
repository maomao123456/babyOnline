package com.example.pregnantbabyonline;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class SonActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sonactivity);
		ImageView imageView=(ImageView)findViewById(R.id.imageview);
		imageView.setOnClickListener(onClickListener);
	}
OnClickListener onClickListener=new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(SonActivity.this, PabulumActivity.class);
		startActivity(intent);
		
	}
};
}
