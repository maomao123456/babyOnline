package com.example.pregnantbabyonline;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.fragment.QuanZiFragment;
import com.example.fragment.ShiPinFragment;
import com.example.fragment.ShouYeFragment;
import com.example.fragment.WoFragment;
import com.example.fragmentadapter.MainFragmentAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		getData();
		fragmentAdapter=new MainFragmentAdapter(
				getSupportFragmentManager(), fragmentList);
		viewPager.setAdapter(fragmentAdapter);
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
			title.setText(R.string.baobaoyitian);
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
					title.setText(R.string.baobaoyitian);
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
}
