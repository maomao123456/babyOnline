package com.example.pregnantbabyonline;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.example.fragment.QuanZiFragment;
import com.example.fragment.ShiPinFragment;
import com.example.fragment.ShouYeFragment;
import com.example.fragment.WoFragment;
import com.example.fragmentadapter.MainFragmentAdapter;

/**
 * 主页面  及中间的几个fragment
 * @author Administrator
 *
 */
public class ShouyeActivity extends FragmentActivity {
	TextView title;
	RadioGroup radioGroup;
	RadioButton shouye;
	RadioButton shipin;
	RadioButton quanzi;
	RadioButton wo;
	ViewPager viewPager;
	List<Fragment> fragmentList;
	MainFragmentAdapter fragmentAdapter;
	int numb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		try {
			getNumb("2016-08-12");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(numb<=0){
			title.setText("你家娃还没出生了");
		}else if(numb>0&&numb<=365){
			title.setText("宝宝"+numb+"天");
		}else if(numb>365){
			title.setText("宝宝已超过一周岁了");
		}
		System.out.println(numb+"hahaha");
		getData();
		fragmentAdapter=new MainFragmentAdapter(
				getSupportFragmentManager(), fragmentList);
		viewPager.setAdapter(fragmentAdapter);
		//getpage();
		
	}
	/**
	 * 通过intent传递参数
	 */
	public void getpage(){
		if(getIntent().getExtras()!=null){
			if(getIntent().getExtras().getInt("numb")==2){
				viewPager.setCurrentItem(2);
				quanzi.setChecked(true);
				title.setText(R.string.quanzi);
			}
		}
		
	}
	/**
	 * 通过id找到控件 并设置相应的点击事件
	 */
	public void initView(){
		title=(TextView)findViewById(R.id.main_title);
		radioGroup=(RadioGroup)findViewById(R.id.main_radioGroup);
		viewPager=(ViewPager)findViewById(R.id.main_viewPager);
		shouye=(RadioButton)findViewById(R.id.main_shouye);
		shipin=(RadioButton)findViewById(R.id.main_shipin);
		quanzi=(RadioButton)findViewById(R.id.main_quanzi);
		wo=(RadioButton)findViewById(R.id.main_wo);
		radioGroup.setOnCheckedChangeListener(listener);
		viewPager.setOnPageChangeListener(pageChangeListener);
	}
	/**
	 * 中间Fragment的点击事件
	 */
	OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

		public void onPageSelected(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		public void onPageScrollStateChanged(int arg0) {
			// 此处监听到 viewPager当前的停留位置
			getItem();
		}
	};
	/**
	 * 根据获得fragment的位置来改变 radiobutton的颜色
	 */
	public void getItem(){
		if(viewPager.getCurrentItem()==0){
			shouye.setChecked(true);
			title.setText("宝宝"+numb+"天");
		}
		if(viewPager.getCurrentItem()==1){
			shipin.setChecked(true);
			title.setText(R.string.shiping);
		}
		if(viewPager.getCurrentItem()==2){
			quanzi.setChecked(true);
			title.setText(R.string.quanzi);
		}
		if(viewPager.getCurrentItem()==3){
			wo.setChecked(true);
			title.setText(R.string.wo);
		}
	}
	/**
	 * 底部导航栏 的点击事件
	 */
	OnCheckedChangeListener listener = new OnCheckedChangeListener() {

		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
			case R.id.main_shouye:
					viewPager.setCurrentItem(0);
					title.setText("宝宝"+numb+"天");
				break;
			case R.id.main_shipin:
					viewPager.setCurrentItem(1);
					title.setText(R.string.shiping);
				break;
			case R.id.main_quanzi:
					viewPager.setCurrentItem(2);
					title.setText(R.string.quanzi);
				break;
			case R.id.main_wo:
					viewPager.setCurrentItem(3);
					title.setText(R.string.wo);
					
				break;

			default:
				break;
			}
		}
	};
	
	/**
	 * 获得主页中的 几个fragment(fragmentList中添加fragment的对象)
	 */
	public void getData() {
		fragmentList = new ArrayList<Fragment>();
		ShouYeFragment shouYeFragment=new ShouYeFragment();
		fragmentList.add(shouYeFragment);
		ShiPinFragment shiPinFragment=new ShiPinFragment();
		fragmentList.add(shiPinFragment);
		QuanZiFragment quanZiFragment=new QuanZiFragment();
		fragmentList.add(quanZiFragment);
		WoFragment twoActivity=new WoFragment();
		fragmentList.add(twoActivity);
	}
	/**
	 * 获得婴儿出生的天数
	 */
	public void getNumb(String birthday) throws ParseException{
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date=simpleDateFormat.parse(birthday);
		long time=new Date().getTime()-date.getTime();
		numb=(int)(time/(1000*60*60*24));
	}
}
