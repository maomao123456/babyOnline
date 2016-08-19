package com.example.pregnantbabyonline;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class EmperatureActivity  extends Activity{
	   private static final int msgKey1 = 1;
	   private TextView mTime;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emperature);
		ImageView imageView=(ImageView)findViewById(R.id.imageview);
		imageView.setOnClickListener(onClickListener);
		mTime=(TextView)findViewById(R.id.textview1);
		new TimeThread().start();
		
		
	}
	
	
    public class TimeThread extends Thread {
        @Override
        public void run () {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = msgKey1;
                    mHandler.sendMessage(msg);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while(true);
        }
    }
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage (Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case msgKey1:
                    long sysTime = System.currentTimeMillis();
                    CharSequence sysTimeStr = DateFormat.format("hh:mm:ss", sysTime);
                    mTime.setText(sysTimeStr);
                    break;

                default:
                    break;
            }
        }
    };
	
	OnClickListener onClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(EmperatureActivity.this, ShouYeActivity.class);
			startActivity(intent);
			EmperatureActivity.this.finish();
			
			
		}
	};
	
	

}
