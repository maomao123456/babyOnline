package com.example.pregnantbabyonline;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BeiYunRiLiActivity extends Activity {
	ImageView back;
	ImageView rightData;
	TextView riqi;
	ImageView leftData;
	TextView xiugaijingqi;
	CheckBox yimaXuanze;
	CheckBox yesuan;
	CheckBox tongfang;
	CheckBox wendu;
	CheckBox pailuanshizhi;
	CheckBox baidai;
	/**
	 * 当月的天数
	 */
	int monthDays;
	/**
	 * 当月的第一天星期几(0为星期日 1.2.3.星期一，以此类推)
	 */
	int firstDayWeek;
	/**
	 * 时间换算 计算上下月的时间
	 */
	int numb = 0;
	int year;
	int today;
	int month;
	TextView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beiyunrili);
		initView();
		getRiliId();
		timeChange();
	}

	/**
	 * 获取当前时间 或者上月月份+1 或者下月月份-1；
	 */
	public void timeChange() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, numb);
		year = calendar.get(Calendar.YEAR);
		today = calendar.get(Calendar.DATE);
		month = calendar.get(Calendar.MONTH) + 1;// 月份需要+1才为当前月份
		riqi.setText(year + "年" + month + "月");// 今天的日期
		// 当月最大天数
		int monthDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 计算这个月的第一天星期几
		calendar.add(Calendar.DAY_OF_MONTH,
				-calendar.get(Calendar.DAY_OF_MONTH) + 1);
		Date date = calendar.getTime();// 这个月的第一天
		int firstDayWeek = date.getDay();// 第一天星期几(0为星期日1.2.3以此类推)
		/*
		 * for(int i=1;i<=monthDays;i++){ if(firstDayWeek==0){
		 * view.findViewWithTag(i); view.setText(i+""); }else
		 * if(firstDayWeek==1){ view.findViewWithTag((i+1)); view.setText(i+"");
		 * }else if(firstDayWeek==2){ view.findViewWithTag((i+2));
		 * view.setText(i+""); }else if(firstDayWeek==3){
		 * view.findViewWithTag((i+3)); view.setText(i+""); }else
		 * if(firstDayWeek==4){ view.findViewWithTag((i+4)); view.setText(i+"");
		 * }else if(firstDayWeek==5){ view.findViewWithTag((i+5));
		 * view.setText(i+""); }else if(firstDayWeek==6){
		 * view.findViewWithTag((i+6)); view.setText(i+""); } }
		 */
		for (int j = 1; j <= 42; j++) {
			String str;
			int color;
			int bg;
			int visible;
			int numbDay=firstDayWeek;
			if(j>numbDay&&j<=numbDay+monthDays){
				str=j-numbDay+"";
			}else{
				str="";
			}
			if(numbDay+monthDays>35){
				sixHang.setVisibility(View.VISIBLE);
			}else{
				sixHang.setVisibility(View.GONE);
			}
			
			if(j==today+numbDay){
				color=Color.parseColor("#FFFFFF");
				bg=R.drawable.xingzhuang;
				if(numb==0){
					visible=View.VISIBLE;
				}else{
					visible=View.GONE;
				}
			}else if(j>3&&j<10||j>19&&j<29){
				color=Color.parseColor("#53bb00");
				visible=View.GONE;
				bg=0;
			}else if(j>29){
				color=Color.parseColor("#f3afcb");
				visible=View.GONE;
				bg=0;
			}else{
				color=Color.parseColor("#ff6c54");
				visible=View.GONE;
				bg=0;
			}
				switch (j) {
				case 1:
					rili1.setText(str);
					rili1.setTextColor(color);
					rili1.setBackgroundResource(bg);
					rilij1.setVisibility(visible);
					break;
				case 2:
					rili2.setText(str);
					rili2.setTextColor(color);
					rili2.setBackgroundResource(bg);
					rilij2.setVisibility(visible);
					break;
				case 3:
					rili3.setText(str);
					rili3.setTextColor(color);
					rili3.setBackgroundResource(bg);
					rilij3.setVisibility(visible);
					break;
				case 4:
					rili4.setText(str);
					rili4.setTextColor(color);
					rili4.setBackgroundResource(bg);
					rilij4.setVisibility(visible);
					break;
				case 5:
					rili5.setText(str);
					rili5.setTextColor(color);
					rili5.setBackgroundResource(bg);
					rilij5.setVisibility(visible);
					break;
				case 6:
					rili6.setText(str);
					rili6.setTextColor(color);
					rili6.setBackgroundResource(bg);
					rilij6.setVisibility(visible);
					break;
				case 7:
					rili7.setText(str);
					rili7.setTextColor(color);
					rili7.setBackgroundResource(bg);
					rilij7.setVisibility(visible);
					break;
				case 8:
					rili8.setText(str);
					rili8.setTextColor(color);
					rili8.setBackgroundResource(bg);
					rilij8.setVisibility(visible);
					break;
				case 9:
					rili9.setText(str);
					rili9.setTextColor(color);
					rili9.setBackgroundResource(bg);
					rilij9.setVisibility(visible);
					break;
				case 10:
					rili10.setText(str);
					rili10.setTextColor(color);
					rili10.setBackgroundResource(bg);
					rilij10.setVisibility(visible);
					break;
				case 11:
					rili11.setText(str);
					rili11.setTextColor(color);
					rili11.setBackgroundResource(bg);
					rilij11.setVisibility(visible);
					break;
				case 12:
					rili12.setText(str);
					rili12.setTextColor(color);
					rili12.setBackgroundResource(bg);
					rilij12.setVisibility(visible);
					break;
				case 13:
					rili13.setText(str);
					rili13.setTextColor(color);
					rili13.setBackgroundResource(bg);
					rilij13.setVisibility(visible);
					break;
				case 14:
					rili14.setText(str);
					rili14.setTextColor(color);
					rili14.setBackgroundResource(bg);
					rilij14.setVisibility(visible);
					break;
				case 15:
					rili15.setText(str);
					rili15.setTextColor(color);
					rili15.setBackgroundResource(bg);
					rilij15.setVisibility(visible);
					break;
				case 16:
					rili16.setText(str);
					rili16.setTextColor(color);
					rili16.setBackgroundResource(bg);
					rilij16.setVisibility(visible);
					break;
				case 17:
					rili17.setText(str);
					rili17.setTextColor(color);
					rili17.setBackgroundResource(bg);
					rilij17.setVisibility(visible);
					break;
				case 18:
					rili18.setText(str);
					rili18.setTextColor(color);
					rili18.setBackgroundResource(bg);
					rilij18.setVisibility(visible);
					break;
				case 19:
					rili19.setText(str);
					rili19.setTextColor(color);
					rili19.setBackgroundResource(bg);
					rilij19.setVisibility(visible);
					break;
				case 20:
					rili20.setText(str);
					rili20.setTextColor(color);
					rili20.setBackgroundResource(bg);
					rilij20.setVisibility(visible);
					break;
				case 21:
					rili21.setText(str);
					rili21.setTextColor(color);
					rili21.setBackgroundResource(bg);
					rilij21.setVisibility(visible);
					break;
				case 22:
					rili22.setText(str);
					rili22.setTextColor(color);
					rili22.setBackgroundResource(bg);
					rilij22.setVisibility(visible);
					break;
				case 23:
					rili23.setText(str);
					rili23.setTextColor(color);
					rili23.setBackgroundResource(bg);
					rilij23.setVisibility(visible);
					break;
				case 24:
					rili24.setText(str);
					rili24.setTextColor(color);
					rili24.setBackgroundResource(bg);
					rilij24.setVisibility(visible);
					break;
				case 25:
					rili25.setText(str);
					rili25.setTextColor(color);
					rili25.setBackgroundResource(bg);
					rilij25.setVisibility(visible);
					break;
				case 26:
					rili26.setText(str);
					rili26.setBackgroundResource(bg);
					rili26.setTextColor(color);
					rilij26.setVisibility(visible);
					break;
				case 27:
					rili27.setText(str);
					rili27.setTextColor(color);
					rili27.setBackgroundResource(bg);
					rilij27.setVisibility(visible);
					break;
				case 28:
					rili28.setText(str);
					rili28.setTextColor(color);
					rili28.setBackgroundResource(bg);
					rilij28.setVisibility(visible);
					break;
				case 29:
					rili29.setText(str);
					rili29.setTextColor(color);
					rili29.setBackgroundResource(bg);
					rilij29.setVisibility(visible);
					break;
				case 30:
					rili30.setText(str);
					rili30.setTextColor(color);
					rili30.setBackgroundResource(bg);
					rilij30.setVisibility(visible);
					break;
				case 31:
					rili31.setText(str);
					rili31.setTextColor(color);
					rili31.setBackgroundResource(bg);
					rilij31.setVisibility(visible);
					break;
				case 32:
					rili32.setText(str);
					rili32.setTextColor(color);
					rili32.setBackgroundResource(bg);
					rilij32.setVisibility(visible);
					break;
				case 33:
					rili33.setText(str);
					rili33.setTextColor(color);
					rili33.setBackgroundResource(bg);
					rilij33.setVisibility(visible);
					break;
				case 34:
					rili34.setText(str);
					rili34.setTextColor(color);
					rili34.setBackgroundResource(bg);
					rilij34.setVisibility(visible);
					break;
				case 35:
					rili35.setText(str);
					rili35.setTextColor(color);
					rili35.setBackgroundResource(bg);
					rilij35.setVisibility(visible);
					break;
				case 36:
					rili36.setText(str);
					rili36.setTextColor(color);
					rili36.setBackgroundResource(bg);
					rilij36.setVisibility(visible);
					break;
				case 37:
					rili37.setText(str);
					rili37.setTextColor(color);
					rili37.setBackgroundResource(bg);
					rilij37.setVisibility(visible);
					break;
				case 38:
					rili38.setText(str);
					rili38.setTextColor(color);
					rili38.setBackgroundResource(bg);
					rilij38.setVisibility(visible);
					break;
				case 39:
					rili39.setText(str);
					rili39.setTextColor(color);
					rili39.setBackgroundResource(bg);
					rilij39.setVisibility(visible);
					break;
				case 40:
					rili40.setText(str);
					rili40.setTextColor(color);
					rili40.setBackgroundResource(bg);
					rilij40.setVisibility(visible);
					break;
				case 41:
					rili41.setText(str);
					rili41.setTextColor(color);
					rili41.setBackgroundResource(bg);
					rilij41.setVisibility(visible);
					break;
				case 42:
					rili42.setText(str);
					rili42.setTextColor(color);
					rili42.setBackgroundResource(bg);
					rilij42.setVisibility(visible);
					break;

				default:
					break;
				}
		}

		/*
		 * calendar.add(Calendar.MONTH, numb-1);//回到上一月 //上一月的最大天数 int
		 * lastMonthDays=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		 * Toast.makeText(RiLiActivity.this,
		 * "当月一共多少天 ："+monthDays+"当月第一天星期几："+firstDayWeek+
		 * "上月一共多少天："+lastMonthDays , Toast.LENGTH_LONG).show();
		 * System.out.println("当月一共多少天 ："+monthDays+"当月第一天星期几："+firstDayWeek+
		 * "上月一共多少天："+lastMonthDays);
		 */
	}

	/**
	 * 通过id找到简单的控件
	 */
	public void initView() {
		back = (ImageView) findViewById(R.id.rili_back);
		rightData = (ImageView) findViewById(R.id.rili_data_right);
		riqi = (TextView) findViewById(R.id.rili_data);
		leftData = (ImageView) findViewById(R.id.rili_data_left);
		xiugaijingqi = (TextView) findViewById(R.id.rili_xiugaijingqi);
		yimaXuanze = (CheckBox) findViewById(R.id.rili_yima_xuanze);
		yesuan = (CheckBox) findViewById(R.id.rili_yesuan);
		tongfang = (CheckBox) findViewById(R.id.rili_tongfang);
		wendu = (CheckBox) findViewById(R.id.rili_wendu);
		pailuanshizhi = (CheckBox) findViewById(R.id.rili_pailuanshizhi);
		baidai = (CheckBox) findViewById(R.id.rili_baidai);
		back.setOnClickListener(listener);
		leftData.setOnClickListener(listener);
		riqi.setOnClickListener(listener);
		rightData.setOnClickListener(listener);
		xiugaijingqi.setOnClickListener(listener);
		yimaXuanze.setOnClickListener(listener);
		yesuan.setOnClickListener(listener);
		tongfang.setOnClickListener(listener);
		wendu.setOnClickListener(listener);
		pailuanshizhi.setOnClickListener(listener);
		baidai.setOnClickListener(listener);
	}

	OnClickListener listener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.rili_back:
				Intent intent = new Intent(BeiYunRiLiActivity.this,
						ShouyeActivity.class);
				startActivity(intent);
				break;
			case R.id.rili_data_left:
				numb--;
				timeChange();
				break;
			case R.id.rili_data:
				numb = 0;
				timeChange();
				Toast.makeText(BeiYunRiLiActivity.this, "已为你更新到当前时间",
						Toast.LENGTH_SHORT).show();
				break;
			case R.id.rili_data_right:
				numb++;
				timeChange();
				break;
			case R.id.rili_wendu:
				Toast.makeText(getApplicationContext(), "跳转到温度界面",
						Toast.LENGTH_SHORT).show();
				/*
				 * intent=new Intent(BeiYunRiLiActivity.this, "");
				 * startActivity(intent);
				 */
				break;

			default:
				break;
			}
		}
	};

	/**
	 * 获得日历界面
	 */
	public void getCalendarUI() {

	}

	TextView rili1, rili2, rili3, rili4, rili5, rili6, rili7, rili8, rili9,
			rili10, rili11, rili12, rili13, rili14, rili15, rili16, rili17,
			rili18, rili19, rili20, rili21, rili22, rili23, rili24, rili25,
			rili26, rili27, rili28, rili29, rili30, rili31, rili32, rili33,
			rili34, rili35, rili36, rili37, rili38, rili39, rili40, rili41,
			rili42;
	TextView rilij1, rilij2, rilij3, rilij4, rilij5, rilij6, rilij7, rilij8, rilij9,
			rilij10, rilij11, rilij12, rilij13, rilij14, rilij15, rilij16, rilij17,
			rilij18, rilij19, rilij20, rilij21, rilij22, rilij23, rilij24, rilij25,
			rilij26, rilij27, rilij28, rilij29, rilij30, rilij31, rilij32, rilij33,
			rilij34, rilij35, rilij36, rilij37, rilij38, rilij39, rilij40, rilij41,
			rilij42;
	TextView rilix1, rilix2, rilix3, rilix4, rilix5, rilix6, rilix7, rilix8, rilix9,
			rilix10, rilix11, rilix12, rilix13, rilix14, rilix15, rilix16, rilix17,
			rilix18, rilix19, rilix20, rilix21, rilix22, rilix23, rilix24, rilix25,
			rilix26, rilix27, rilix28, rilix29, rilix30, rilix31, rilix32, rilix33,
			rilix34, rilix35, rilix36, rilix37, rilix38, rilix39, rilix40, rilix41,
			rilix42;
	LinearLayout sixHang;

	/**
	 * 日历内容的id
	 */
	public void getRiliId() {
		rili1 = (TextView) findViewById(R.id.rili1);
		rili2 = (TextView) findViewById(R.id.rili2);
		rili3 = (TextView) findViewById(R.id.rili3);
		rili4 = (TextView) findViewById(R.id.rili4);
		rili5 = (TextView) findViewById(R.id.rili5);
		rili6 = (TextView) findViewById(R.id.rili6);
		rili7 = (TextView) findViewById(R.id.rili7);
		rili8 = (TextView) findViewById(R.id.rili8);
		rili9 = (TextView) findViewById(R.id.rili9);
		rili10 = (TextView) findViewById(R.id.rili10);
		rili11 = (TextView) findViewById(R.id.rili11);
		rili12 = (TextView) findViewById(R.id.rili12);
		rili13 = (TextView) findViewById(R.id.rili13);
		rili14 = (TextView) findViewById(R.id.rili14);
		rili15 = (TextView) findViewById(R.id.rili15);
		rili16 = (TextView) findViewById(R.id.rili16);
		rili17 = (TextView) findViewById(R.id.rili17);
		rili18 = (TextView) findViewById(R.id.rili18);
		rili19 = (TextView) findViewById(R.id.rili19);
		rili20 = (TextView) findViewById(R.id.rili20);
		rili21 = (TextView) findViewById(R.id.rili21);
		rili22 = (TextView) findViewById(R.id.rili22);
		rili23 = (TextView) findViewById(R.id.rili23);
		rili24 = (TextView) findViewById(R.id.rili24);
		rili25 = (TextView) findViewById(R.id.rili25);
		rili26 = (TextView) findViewById(R.id.rili26);
		rili27 = (TextView) findViewById(R.id.rili27);
		rili28 = (TextView) findViewById(R.id.rili28);
		rili29 = (TextView) findViewById(R.id.rili29);
		rili30 = (TextView) findViewById(R.id.rili30);
		rili31 = (TextView) findViewById(R.id.rili31);
		rili32 = (TextView) findViewById(R.id.rili32);
		rili33 = (TextView) findViewById(R.id.rili33);
		rili34 = (TextView) findViewById(R.id.rili34);
		rili35 = (TextView) findViewById(R.id.rili35);
		rili36 = (TextView) findViewById(R.id.rili36);
		rili37 = (TextView) findViewById(R.id.rili37);
		rili38 = (TextView) findViewById(R.id.rili38);
		rili39 = (TextView) findViewById(R.id.rili39);
		rili40 = (TextView) findViewById(R.id.rili40);
		rili41 = (TextView) findViewById(R.id.rili41);
		rili42 = (TextView) findViewById(R.id.rili42);
		
		rilij1 = (TextView) findViewById(R.id.rilij1);
		rilij2 = (TextView) findViewById(R.id.rilij2);
		rilij3 = (TextView) findViewById(R.id.rilij3);
		rilij4 = (TextView) findViewById(R.id.rilij4);
		rilij5 = (TextView) findViewById(R.id.rilij5);
		rilij6 = (TextView) findViewById(R.id.rilij6);
		rilij7 = (TextView) findViewById(R.id.rilij7);
		rilij8 = (TextView) findViewById(R.id.rilij8);
		rilij9 = (TextView) findViewById(R.id.rilij9);
		rilij10 = (TextView) findViewById(R.id.rilij10);
		rilij11 = (TextView) findViewById(R.id.rilij11);
		rilij12 = (TextView) findViewById(R.id.rilij12);
		rilij13 = (TextView) findViewById(R.id.rilij13);
		rilij14 = (TextView) findViewById(R.id.rilij14);
		rilij15 = (TextView) findViewById(R.id.rilij15);
		rilij16 = (TextView) findViewById(R.id.rilij16);
		rilij17 = (TextView) findViewById(R.id.rilij17);
		rilij18 = (TextView) findViewById(R.id.rilij18);
		rilij19 = (TextView) findViewById(R.id.rilij19);
		rilij20 = (TextView) findViewById(R.id.rilij20);
		rilij21 = (TextView) findViewById(R.id.rilij21);
		rilij22 = (TextView) findViewById(R.id.rilij22);
		rilij23 = (TextView) findViewById(R.id.rilij23);
		rilij24 = (TextView) findViewById(R.id.rilij24);
		rilij25 = (TextView) findViewById(R.id.rilij25);
		rilij26 = (TextView) findViewById(R.id.rilij26);
		rilij27 = (TextView) findViewById(R.id.rilij27);
		rilij28 = (TextView) findViewById(R.id.rilij28);
		rilij29 = (TextView) findViewById(R.id.rilij29);
		rilij30 = (TextView) findViewById(R.id.rilij30);
		rilij31 = (TextView) findViewById(R.id.rilij31);
		rilij32 = (TextView) findViewById(R.id.rilij32);
		rilij33 = (TextView) findViewById(R.id.rilij33);
		rilij34 = (TextView) findViewById(R.id.rilij34);
		rilij35 = (TextView) findViewById(R.id.rilij35);
		rilij36 = (TextView) findViewById(R.id.rilij36);
		rilij37 = (TextView) findViewById(R.id.rilij37);
		rilij38 = (TextView) findViewById(R.id.rilij38);
		rilij39 = (TextView) findViewById(R.id.rilij39);
		rilij40 = (TextView) findViewById(R.id.rilij40);
		rilij41 = (TextView) findViewById(R.id.rilij41);
		rilij42 = (TextView) findViewById(R.id.rilij42);
		
		rilix1 = (TextView) findViewById(R.id.rilix1);
		rilix2 = (TextView) findViewById(R.id.rilix2);
		rilix3 = (TextView) findViewById(R.id.rilix3);
		rilix4 = (TextView) findViewById(R.id.rilix4);
		rilix5 = (TextView) findViewById(R.id.rilix5);
		rilix6 = (TextView) findViewById(R.id.rilix6);
		rilix7 = (TextView) findViewById(R.id.rilix7);
		rilix8 = (TextView) findViewById(R.id.rilix8);
		rilix9 = (TextView) findViewById(R.id.rilix9);
		rilix10 = (TextView) findViewById(R.id.rilix10);
		rilix11 = (TextView) findViewById(R.id.rilix11);
		rilix12 = (TextView) findViewById(R.id.rilix12);
		rilix13 = (TextView) findViewById(R.id.rilix13);
		rilix14 = (TextView) findViewById(R.id.rilix14);
		rilix15 = (TextView) findViewById(R.id.rilix15);
		rilix16 = (TextView) findViewById(R.id.rilix16);
		rilix17 = (TextView) findViewById(R.id.rilix17);
		rilix18 = (TextView) findViewById(R.id.rilix18);
		rilix19 = (TextView) findViewById(R.id.rilix19);
		rilix20 = (TextView) findViewById(R.id.rilix20);
		rilix21 = (TextView) findViewById(R.id.rilix21);
		rilix22 = (TextView) findViewById(R.id.rilix22);
		rilix23 = (TextView) findViewById(R.id.rilix23);
		rilix24 = (TextView) findViewById(R.id.rilix24);
		rilix25 = (TextView) findViewById(R.id.rilix25);
		rilix26 = (TextView) findViewById(R.id.rilix26);
		rilix27 = (TextView) findViewById(R.id.rilix27);
		rilix28 = (TextView) findViewById(R.id.rilix28);
		rilix29 = (TextView) findViewById(R.id.rilix29);
		rilix30 = (TextView) findViewById(R.id.rilix30);
		rilix31 = (TextView) findViewById(R.id.rilix31);
		rilix32 = (TextView) findViewById(R.id.rilix32);
		rilix33 = (TextView) findViewById(R.id.rilix33);
		rilix34 = (TextView) findViewById(R.id.rilix34);
		rilix35 = (TextView) findViewById(R.id.rilix35);
		rilix36 = (TextView) findViewById(R.id.rilix36);
		rilix37 = (TextView) findViewById(R.id.rilix37);
		rilix38 = (TextView) findViewById(R.id.rilix38);
		rilix39 = (TextView) findViewById(R.id.rilix39);
		rilix40 = (TextView) findViewById(R.id.rilix40);
		rilix41 = (TextView) findViewById(R.id.rilix41);
		rilix42 = (TextView) findViewById(R.id.rilix42);
		sixHang=(LinearLayout)findViewById(R.id.rili_sixhang);
	}

	/**
	 * 获取当前时间的第二种方法 不废CPU
	 */
	/*
	 * public void getTime2(){ Time time =new Time("GTM+8"); time.setToNow();
	 * int year=time.year; int month=time.month+1; int day=time.monthDay;
	 * riqi.setText(year+"年"+month+"月"); } // 重写onActivityResult()方法处理返回的日期
	 * 
	 * @Override public void onActivityResult(int requestCode, int resultCode,
	 * Intent data) { if(resultCode==RESULT_OK) { int year =
	 * data.getIntExtra("year", 0); // 得到年 int month = data.getIntExtra("month",
	 * 0); // 得到月 int day = data.getIntExtra("day", 0); // 得到天
	 * riqi.setText(year+"年"+month+"月");
	 * 
	 * // 格式化日期显示 final Calendar dat = Calendar.getInstance();
	 * dat.set(Calendar.YEAR, year); dat.set(Calendar.MONTH, month);
	 * dat.set(Calendar.DAY_OF_MONTH, day);
	 * 
	 * // 显示日期结果 SimpleDateFormat format = new SimpleDateFormat("yyyy MMM dd");
	 * Toast.makeText(RiLiActivity.this, format.format(dat.getTime()),
	 * Toast.LENGTH_LONG).show(); } }
	 */

}

class DateUtil {
	public static int getMonthDays(int year, int month) {
		if (month > 12) {
			month = 1;
			year += 1;
		} else if (month < 1) {
			month = 12;
			year -= 1;
		}
		int[] arr = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int days = 0;

		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			arr[1] = 29; // 闰年2月29天
		}

		try {
			days = arr[month - 1];
		} catch (Exception e) {
			e.getStackTrace();
		}

		return days;
	}
}