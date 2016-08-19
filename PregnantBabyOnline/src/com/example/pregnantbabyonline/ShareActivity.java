package com.example.pregnantbabyonline;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ShareActivity  extends  Activity{
		Button button1,button2;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_share);
			button1=(Button)findViewById(R.id.button1);
			button2=(Button)findViewById(R.id.button2);
			button1.setOnClickListener(onClickListener);
			button2.setOnClickListener(onClickListener);
		}
		
		
		OnClickListener onClickListener=new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.button2:
					shareSingleImage(button1);
					break;
					
				case R.id.button1:
					shareText(button2);
					break;

				default:
					break;
				}
				
			}
		};
		
		//分享文字
	    public void shareText(View view) {
	        Intent shareIntent = new Intent();
	        shareIntent.setAction(Intent.ACTION_SEND);
	        shareIntent.putExtra(Intent.EXTRA_TEXT, "这是我分享的文字");
	        shareIntent.setType("text/plain");

	        //设置分享列表的标题，并且每次都显示分享列表
	        startActivity(Intent.createChooser(shareIntent, "分享"));
	    }
	    
	  //分享单张图片
	    public void shareSingleImage(View view) {
	        String imagePath = Environment.getExternalStorageDirectory() + File.separator + "hs.jpg";
	        //由文件得到uri
	        Uri imageUri = Uri.fromFile(new File(imagePath));
	        Log.d("share", "uri:" + imageUri);  //输出：file:///storage/emulated/0/test.jpg

	        Intent shareIntent = new Intent();
	        shareIntent.setAction(Intent.ACTION_SEND);
	        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
	        shareIntent.setType("image/*");
	        startActivity(Intent.createChooser(shareIntent, "分享到"));
	    }
}
