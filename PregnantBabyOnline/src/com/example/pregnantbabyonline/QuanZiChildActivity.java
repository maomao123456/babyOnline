package com.example.pregnantbabyonline;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lei.CircularImage;

public class QuanZiChildActivity extends Activity {
	ImageView back;
	TextView type;
	CircularImage touxiang;
	TextView nicheng;
	TextView tucao;
	TextView title;
	ImageView tupian;
	TextView neirong;
	TextView dingyue;
	TextView guanzhu;
	int numb;//页面之间的数据判断
	String str;
	String str2;
	SharedPreferences sharedPreferences; // 缓存数据

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quanzi_listview_child);
		sharedPreferences = getSharedPreferences("quanziChild", Context.MODE_PRIVATE);
		initView();
		getintent();
		setGuanzhu();
	}

	/**
	 * 通过intent传递数据 并获得数据
	 */
	public void getintent() {
		if (getIntent().getExtras() != null) {
			numb = getIntent().getExtras().getInt("position");
			str=numb+"关注";
			str2=numb+"订阅";
			switch (numb) {
			case 1:
				type.setText("时尚辣妈");
				touxiang.setImageResource(R.drawable.xiaotouxiang1);
				nicheng.setText("Bacio");
				tucao.setText("吐槽3200		关注3.0万");
				title.setText("		50岁，离过两次婚，带着三个娃,人生大逆转！");
				tupian.setImageResource(R.drawable.datouxiang1);
				neirong.setText("");
				break;
			case 2:
				type.setText("美食厨房");
				touxiang.setImageResource(R.drawable.xiaotouxiang2);
				nicheng.setText("糖果味");
				tucao.setText("吐槽200		关注1.0万");
				title.setText(" 	【为爱下厨】我的东北大拉皮");
				tupian.setImageResource(R.drawable.datouxiang2);
				neirong.setText("");
				break;
			case 3:
				type.setText("娱乐休闲");
				touxiang.setImageResource(R.drawable.xiaotouxiang3);
				nicheng.setText("小新");
				tucao.setText("吐槽6200		关注1.5万");
				title.setText("		等咱们老师有钱了，你会做点啥？");
				tupian.setImageResource(R.drawable.datouxiang3);
				neirong.setText("");
				break;
			case 4:
				type.setText("美妆小编推荐");
				touxiang.setImageResource(R.drawable.xiaotouxiang4);
				nicheng.setText("花之语");
				tucao.setText("吐槽880		关注1.1万");
				title.setText(" 	【太赞了！祛斑+美白7小技巧，你掌握了吗？】");
				tupian.setImageResource(R.drawable.datouxiang4);
				neirong.setText("");
				break;

			default:
				break;
			}
		}
	}
	/**
	 * 根据用户上次是否关注的缓存 设置是否关注
	 */
	public void setGuanzhu(){
		String guanzhuStr=sharedPreferences.getString(str, "关注");
		String dingyueStr=sharedPreferences.getString(str2, "订阅");
		guanzhu.setText(guanzhuStr);
		dingyue.setText(dingyueStr);
	}

	/**
	 * 通过ID找控件
	 */
	public void initView() {
		back = (ImageView) findViewById(R.id.quanzi_child_back);
		type = (TextView) findViewById(R.id.quanzi_child_type);
		touxiang = (CircularImage) findViewById(R.id.quanzi_child_touxiang);
		nicheng = (TextView) findViewById(R.id.quanzi_child_nicheng);
		tucao = (TextView) findViewById(R.id.quanzi_child_tucao);
		title = (TextView) findViewById(R.id.quanzi_child_title);
		tupian = (ImageView) findViewById(R.id.quanzi_child_tupian);
		neirong = (TextView) findViewById(R.id.quanzi_child_neirong);
		guanzhu=(TextView)findViewById(R.id.quanzi_child_guanzhu);
		dingyue=(TextView)findViewById(R.id.quanzi_child_dingyue);
		dingyue.setOnClickListener(listener);
		guanzhu.setOnClickListener(listener);
		back.setOnClickListener(listener);
	}

	/**
	 * 普通控件的点击事件
	 */
	OnClickListener listener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.quanzi_child_back:
				Intent intent = new Intent(QuanZiChildActivity.this,
						ShouYeActivity.class);
				intent.putExtra("numb", 2);
				startActivity(intent);
				break;
			case R.id.quanzi_child_guanzhu:
				guanzhu();
				break;
			case R.id.quanzi_child_dingyue:
				dingyue();
				break;

			default:
				break;
			}
		}
	};
	/**
	 * 设置是否订阅
	 */
	public void dingyue(){
		Editor editor = sharedPreferences.edit();// 获得一个editor的对象
		if(dingyue.getText().equals("订阅")){
			dingyue.setText("已订阅");
			editor.putString(str2, "已订阅");
		}else{
			dingyue.setText("订阅");
			editor.putString(str2, "订阅");
		}
		editor.commit();// 提交 不提交不保存 且遵循put不可重复的特点
	}
	/**
	 * 设置是否关注
	 */
	public void guanzhu(){
		Editor editor = sharedPreferences.edit();// 获得一个editor的对象
		if(guanzhu.getText().equals("关注")){
			guanzhu.setText("已关注");
			editor.putString(str, "已关注");
		}else{
			guanzhu.setText("关注");
			editor.putString(str, "关注");
		}
		editor.commit();// 提交 不提交不保存 且遵循put不可重复的特点
	}

}
