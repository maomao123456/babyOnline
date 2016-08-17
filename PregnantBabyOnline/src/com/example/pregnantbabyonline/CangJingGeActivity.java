package com.example.pregnantbabyonline;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 藏经阁 页面
 * @author Administrator
 *
 */
public class CangJingGeActivity extends Activity{
	TextView beiyun;
	TextView yunqi;
	TextView yinger;
	ImageView back;
	View beiyunV;
	View yunqiV;
	View yingerV;
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Uri uri;
	int numb=1;//判断孕期  备孕   婴儿 哪个被点击了
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_cangjingge);
		findId();
	}
	public void findId(){
		beiyun=(TextView)findViewById(R.id.textview_beiyun);
		yunqi=(TextView)findViewById(R.id.textview_yunqi);
		yinger=(TextView)findViewById(R.id.textview_yinger);
		beiyunV=(View)findViewById(R.id.view_beiyun);
		yunqiV=(View)findViewById(R.id.view_yunqi);
		yingerV=(View)findViewById(R.id.view_yinger);
		back=(ImageView)findViewById(R.id.imageview_back);
		button1=(Button)findViewById(R.id.button1_cangjingge);
		button2=(Button)findViewById(R.id.button2_cangjingge);
		button3=(Button)findViewById(R.id.button3_cangjingge);
		button4=(Button)findViewById(R.id.button4_cangjingge);
		beiyun.setOnClickListener(onClickListener);
		yunqi.setOnClickListener(onClickListener);
		yinger.setOnClickListener(onClickListener);
		back.setOnClickListener(onClickListener);
		button1.setOnClickListener(onClickListener);
		button2.setOnClickListener(onClickListener);
		button3.setOnClickListener(onClickListener);
		button4.setOnClickListener(onClickListener);
	}
	OnClickListener onClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch(view.getId()){
			case R.id.textview_beiyun:
				numb=1;
				beiyun.setTextColor(getResources().getColor(R.color.green));
				beiyunV.setBackgroundColor(getResources().getColor(R.color.green));
				yunqi.setTextColor(getResources().getColor(R.color.blackc));
				yunqiV.setBackgroundColor(getResources().getColor(R.color.white));
				yinger.setTextColor(getResources().getColor(R.color.blackc));
				yingerV.setBackgroundColor(getResources().getColor(R.color.white));
				button1.setBackgroundColor(getResources().getColor(R.color.white));
				button2.setBackgroundColor(getResources().getColor(R.color.white));
				button3.setBackgroundColor(getResources().getColor(R.color.white));
				button4.setBackgroundColor(getResources().getColor(R.color.white));
				button1.setText(R.string.beiyunchangshi);
				button2.setText(R.string.yinshitiaoli);
				button3.setText(R.string.shouyunjiqiao);
				button4.setText(R.string.yunqianjiancha);
				break;
			case R.id.textview_yunqi:
				numb=2;
				beiyun.setTextColor(getResources().getColor(R.color.blackc));
				beiyunV.setBackgroundColor(getResources().getColor(R.color.white));
				yunqi.setTextColor(getResources().getColor(R.color.green));
				yunqiV.setBackgroundColor(getResources().getColor(R.color.green));
				yinger.setTextColor(getResources().getColor(R.color.blackc));
				yingerV.setBackgroundColor(getResources().getColor(R.color.white));
				button1.setBackgroundColor(getResources().getColor(R.color.white));
				button2.setBackgroundColor(getResources().getColor(R.color.white));
				button3.setBackgroundColor(getResources().getColor(R.color.white));
				button4.setBackgroundColor(getResources().getColor(R.color.yellow));
				button1.setText("孕期注意");
				button2.setText("孕期饮食");
				button3.setText("孕期检查");
				button4.setText(null);
				break;
			case R.id.textview_yinger:
				numb=3;
				beiyun.setTextColor(getResources().getColor(R.color.blackc));
				beiyunV.setBackgroundColor(getResources().getColor(R.color.white));
				yunqi.setTextColor(getResources().getColor(R.color.blackc));
				yunqiV.setBackgroundColor(getResources().getColor(R.color.white));
				yinger.setTextColor(getResources().getColor(R.color.green));
				yingerV.setBackgroundColor(getResources().getColor(R.color.green));
				button1.setBackgroundColor(getResources().getColor(R.color.white));
				button2.setBackgroundColor(getResources().getColor(R.color.white));
				button3.setBackgroundColor(getResources().getColor(R.color.yellow));
				button4.setBackgroundColor(getResources().getColor(R.color.yellow));
				button1.setText("育婴宝典");
				button2.setText("婴儿常识");
				button3.setText(null);
				button4.setText(null);
				break;
			case R.id.imageview_back:
				Intent intent=new Intent(CangJingGeActivity.this,ShouyeActivity.class);
				startActivity(intent);
				CangJingGeActivity.this.finish();
				break;
			}
			if(numb==1){
				switch(view.getId()){
				case R.id.button1_cangjingge:
					Toast.makeText(CangJingGeActivity.this, "跳转到备孕常识页面", Toast.LENGTH_SHORT).show();
					uri=Uri.parse("https://www.baidu.com");
					enterWeb();
					break;
				case R.id.button2_cangjingge:
					Toast.makeText(CangJingGeActivity.this, "跳转到饮食调理页面", Toast.LENGTH_SHORT).show();
					uri=Uri.parse("https://www.baidu.com");
					enterWeb();
					break;
				case R.id.button3_cangjingge:
					Toast.makeText(CangJingGeActivity.this, "跳转到受孕技巧页面", Toast.LENGTH_SHORT).show();
					uri=Uri.parse("https://www.baidu.com");
					enterWeb();
					break;
				case R.id.button4_cangjingge:
					Toast.makeText(CangJingGeActivity.this, "跳转到孕前检查页面", Toast.LENGTH_SHORT).show();
					uri=Uri.parse("https://www.baidu.com");
					enterWeb();
					break;
				}
			}else if(numb==2){
				switch(view.getId()){
				case R.id.button1_cangjingge:
					Toast.makeText(CangJingGeActivity.this, "跳转到孕期注意页面", Toast.LENGTH_SHORT).show();
					uri=Uri.parse("https://www.baidu.com");
					enterWeb();
					break;
				case R.id.button2_cangjingge:
					Toast.makeText(CangJingGeActivity.this, "跳转到孕期饮食页面", Toast.LENGTH_SHORT).show();
					uri=Uri.parse("https://www.baidu.com");
					enterWeb();
					break;
				case R.id.button3_cangjingge:
					Toast.makeText(CangJingGeActivity.this, "跳转到孕期检查页面", Toast.LENGTH_SHORT).show();
					uri=Uri.parse("https://www.baidu.com");
					enterWeb();
					break;
				}
			}else if(numb==3){
				switch(view.getId()){
				case R.id.button1_cangjingge:
					Toast.makeText(CangJingGeActivity.this, "跳转到育婴宝典页面", Toast.LENGTH_SHORT).show();
					uri=Uri.parse("https://www.baidu.com");
					enterWeb();
					break;
				case R.id.button2_cangjingge:
					Toast.makeText(CangJingGeActivity.this, "跳转到婴儿常识页面", Toast.LENGTH_SHORT).show();
					uri=Uri.parse("https://www.baidu.com");
					enterWeb();
					break;
				}
			}
		}
	};
	public void enterWeb(){//进入网页
		Intent intent=new Intent(Intent.ACTION_VIEW,uri);
		startActivity(intent);
	}
}
