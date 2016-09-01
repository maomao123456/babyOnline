package com.example.pregnantbabyonline;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BeiYunRiLiActivity extends Activity implements OnTouchListener{
	//手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
	 float x1 = 0;
	 float x2 = 0;
	 float y1 = 0;
	 float y2 = 0;
	LinearLayout backQuyu;
	ImageView back;
	ImageView rightData;
	TextView riqi;
	ImageView leftData;
	TextView xiugaijingqi;
	LinearLayout riliBuju;
	CheckBox yimaXuanze;
	CheckBox yesuan;
	CheckBox tongfang;
	CheckBox wendu;
	CheckBox pailuanshizhi;
	CheckBox baidai;
	SharedPreferences sharedPreferences; // 缓存数据

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
		sharedPreferences = getSharedPreferences("quanziChild", Context.MODE_PRIVATE);
		comeYima=sharedPreferences.getInt("comeYima", 0);
		initView();
		getRiliId();
		timeChange();
		getYimachecked();
	}
	/**
	 * 记忆上次用户的选择状态（yimachecked）
	 */
	public void getYimachecked(){
		if(comeYima==1){
			yimaXuanze.setChecked(true);
			tongfang.setChecked(false);
		}else{
			yimaXuanze.setChecked(false);
			tongfang.setChecked(true);
		}
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
				if(comeYima==0){
					color=Color.parseColor("#FFFFFF");
				}else{
					color=Color.parseColor("#f86eaf");
				}
				
				bg=R.drawable.xingzhuang;
				if(numb==0){
					visible=View.VISIBLE;
				}else{
					visible=View.GONE;
				}
			}else if(comeYima==1&&j>today+numbDay&&j<today+numbDay+5){
				color=Color.parseColor("#f86eaf");
				visible=View.GONE;
				bg=0;	
			}else if(j>3&&j<10||j>19&&j<29){
				color=Color.parseColor("#53bb00");
				visible=View.GONE;
				bg=0;
			}else if(j>28){
				color=Color.parseColor("#f3afcb");
				visible=View.GONE;
				bg=0;
			}else{
				color=Color.parseColor("#ff6c54");
				visible=View.GONE;
				bg=0;
				if(numb%5==0){
					rilix15.setVisibility(View.VISIBLE);
					rilix14.setVisibility(View.GONE);	
					rilix16.setVisibility(View.GONE);
					rilix19.setVisibility(View.GONE);
					rilix11.setVisibility(View.GONE);
					rilix17.setVisibility(View.GONE);	
					rilix18.setVisibility(View.GONE);
					rilix13.setVisibility(View.GONE);	
					rilix12.setVisibility(View.GONE);
					
				}else if(numb%5==1){
					rilix16.setVisibility(View.VISIBLE);
					rilix17.setVisibility(View.GONE);	
					rilix15.setVisibility(View.GONE);
				}else if(numb%5==2){
					rilix17.setVisibility(View.VISIBLE);
					rilix18.setVisibility(View.GONE);	
					rilix16.setVisibility(View.GONE);
				}else if(numb%5==3){
					rilix18.setVisibility(View.VISIBLE);
					rilix17.setVisibility(View.GONE);	
					rilix19.setVisibility(View.GONE);
				}else if(numb%5==4){
					rilix19.setVisibility(View.VISIBLE);
					rilix18.setVisibility(View.GONE);
					rilix15.setVisibility(View.GONE);
				}else if(numb%5==-1){
					rilix14.setVisibility(View.VISIBLE);
					rilix13.setVisibility(View.GONE);	
					rilix15.setVisibility(View.GONE);
				}else if(numb%5==-2){
					rilix13.setVisibility(View.VISIBLE);
					rilix12.setVisibility(View.GONE);	
					rilix14.setVisibility(View.GONE);
				}else if(numb%5==-3){
					rilix12.setVisibility(View.VISIBLE);
					rilix11.setVisibility(View.GONE);	
					rilix13.setVisibility(View.GONE);
				}else if(numb%5==-4){
					rilix11.setVisibility(View.VISIBLE);
					rilix12.setVisibility(View.GONE);
					rilix15.setVisibility(View.GONE);
				}
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
	}

	/**
	 * 通过id找到简单的控件
	 */
	public void initView() {
		backQuyu=(LinearLayout)findViewById(R.id.rili_back_quyu);
		rightData = (ImageView) findViewById(R.id.rili_data_right);
		riqi = (TextView) findViewById(R.id.rili_data);
		leftData = (ImageView) findViewById(R.id.rili_data_left);
		xiugaijingqi = (TextView) findViewById(R.id.rili_xiugaijingqi);
		yimaXuanze = (CheckBox) findViewById(R.id.rili_yima_xuanze);
		riliBuju=(LinearLayout)findViewById(R.id.rili_buju);
		riliBuju.setOnTouchListener(this);
		yesuan = (CheckBox) findViewById(R.id.rili_yesuan);
		tongfang = (CheckBox) findViewById(R.id.rili_tongfang);
		wendu = (CheckBox) findViewById(R.id.rili_wendu);
		pailuanshizhi = (CheckBox) findViewById(R.id.rili_pailuanshizhi);
		baidai = (CheckBox) findViewById(R.id.rili_baidai);
		backQuyu.setOnClickListener(listener);
		leftData.setOnClickListener(listener);
		riqi.setOnClickListener(listener);
		rightData.setOnClickListener(listener);
		xiugaijingqi.setOnClickListener(listener);
		yimaXuanze.setOnClickListener(listener);
		wendu.setOnClickListener(listener);
	}
	/**
	 * 判断用户的选择状态
	 */
	int comeYima;
	/**
	 * 文字的背景颜色的判断
	 */
	int colorNumb;
	/**
	 * 普通控件的点击事件
	 */
	OnClickListener listener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.rili_back_quyu:
				Intent intent = new Intent(BeiYunRiLiActivity.this,
						ShouYeActivity.class);
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
				if(wendu.isChecked()){
					wendu.setChecked(false);
				}else{
					wendu.setChecked(true);
				}
				Intent intent2=new Intent(BeiYunRiLiActivity.this,EmperatureActivity.class);
				startActivity(intent2);
				break;
			case R.id.rili_yima_xuanze:
				Editor editor = sharedPreferences.edit();// 获得一个editor的对象
				if(numb==0){
					if(yimaXuanze.isChecked()){
						comeYima=1;
						tongfang.setChecked(false);
						numb = 0;
						timeChange();
						editor.putInt("comeYima", 1);
					}else{
						comeYima=0;
						tongfang.setChecked(true);
						numb = 0;
						timeChange();
						editor.putInt("comeYima", 0);
					}
					editor.commit();
				}else{
					if(yimaXuanze.isChecked()){
						yimaXuanze.setChecked(false);
					}else{
						yimaXuanze.setChecked(true);
					}
					builder = new AlertDialog.Builder(BeiYunRiLiActivity.this);
					builder.setTitle("亲！此选项只能在本月操作，是否返回到本月？");
					builder.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									numb = 0;
									timeChange();
									xiugaijingqi.setVisibility(View.VISIBLE);
									Toast.makeText(BeiYunRiLiActivity.this, "已为您返回到本月，现在你可以操作此选项。",
											Toast.LENGTH_SHORT).show();
								}
							});
					builder.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
									
								}
							});
					builder.show();
				}
				break;
			case R.id.rili_xiugaijingqi:
				if(xiugaijingqi.getText().equals("修改经期")){
					Toast.makeText(BeiYunRiLiActivity.this, "请选择日期", Toast.LENGTH_LONG).show();
					xiugaijingqi.setText("保存经期");
					colorNumb=1;
					xiugaijingqi.setTextColor(getResources().getColor(R.color.baocunjingqi));
				}else if(xiugaijingqi.getText().equals("保存经期")){
					if(alertDialog==null){//保证代码的完整性（不要也行）
						creatDialog();
					}
					alertDialog.show();
				}
				break;
			case R.id.rili1:
				gt(rili1);
				break;
			case R.id.rili2:
				gt(rili2);
				break;
			case R.id.rili3:
				gt(rili3);
				break;
			case R.id.rili4:
				gt(rili4);
				break;
			case R.id.rili5:
				gt(rili5);
				break;
			case R.id.rili6:
				gt(rili6);
				break;
			case R.id.rili7:
				gt(rili7);
				break;
			case R.id.rili8:
				gt(rili8);
				break;
			case R.id.rili9:
				gt(rili9);
				break;
			case R.id.rili10:
				gt(rili10);
				break;
			case R.id.rili11:
				gt(rili11);
				break;
			case R.id.rili12:
				gt(rili12);
				break;
			default:
				break;
			}
			if(numb<=0){
				xiugaijingqi.setVisibility(View.VISIBLE);
			}else{
				xiugaijingqi.setVisibility(View.GONE);
			}	
		}
	};
	/**
	 * 修改经期的方法
	 * @param vi
	 */
	public void gt(CheckBox vi){
		if (colorNumb == 1) {
			if (vi.isChecked()) {
				vi.setTextColor(getResources().getColor(R.color.rili_yuejingqi));
				vi.setBackgroundColor(getResources().getColor(R.color.qianhuise));
			} else {
				vi.setTextColor(getResources().getColor(R.color.rili_luse));
				vi.setBackgroundColor(getResources().getColor(R.color.baise));
			}
		}
	}
	AlertDialog alertDialog;
	Builder builder;
	/**
	 * 修改经期后确认保存数据的对话框
	 */
	public void creatDialog(){
		builder=new AlertDialog.Builder(BeiYunRiLiActivity.this);
		builder.setTitle("提示");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setMessage("数据即将保存，是否再次确认？");
		builder.setPositiveButton("立即保存", onClickListener);//设置监听 且默认下标为-1；
		builder.setNegativeButton("再次确认", onClickListener);//默认下标为-2；
		builder.setNeutralButton("放弃更改", onClickListener);//默认下标为-3；
		alertDialog=builder.create();
	}
	/**
	 * yimalailiao选择对话框
	 */
	public void yimachoseDg(){
		builder=new AlertDialog.Builder(BeiYunRiLiActivity.this);
		builder.setTitle("提示");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setMessage("只允许在本月执行此操作，是否回到当前时间？");
		builder.setPositiveButton("确定", onClickListener);//设置监听 且默认下标为-1；
		builder.setNegativeButton("取消", onClickListener);//默认下标为-2；
		alertDialog=builder.create();
	}
	/**
	 * 对话框的点击事件
	 */
	DialogInterface.OnClickListener onClickListener=new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int nb) {
			if(nb==-1){
				xiugaijingqi.setText("修改经期");
				colorNumb=0;
				xiugaijingqi.setTextColor(getResources().getColor(R.color.xiugaijingqi));
				Toast.makeText(BeiYunRiLiActivity.this, "数据保存成功！", Toast.LENGTH_SHORT).show();
			}else if(nb==-3){
				xiugaijingqi.setText("修改经期");
				colorNumb=0;
				numb = 0;
				timeChange();
				xiugaijingqi.setTextColor(getResources().getColor(R.color.xiugaijingqi));
				Toast.makeText(BeiYunRiLiActivity.this, "已为您取消任何更改", Toast.LENGTH_SHORT).show();
			}else if(nb==-2){
				Toast.makeText(BeiYunRiLiActivity.this, "请确认您选择的日期", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(BeiYunRiLiActivity.this, "你尚未提交任何数据，经期暂未保存！", Toast.LENGTH_LONG).show();
			}
		}
	};
	
	CheckBox rili1, rili2, rili3, rili4, rili5, rili6, rili7, rili8, rili9,
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
		rili1 = (CheckBox) findViewById(R.id.rili1);
		rili2 = (CheckBox) findViewById(R.id.rili2);
		rili3 = (CheckBox) findViewById(R.id.rili3);
		rili4 = (CheckBox) findViewById(R.id.rili4);
		rili5 = (CheckBox) findViewById(R.id.rili5);
		rili6 = (CheckBox) findViewById(R.id.rili6);
		rili7 = (CheckBox) findViewById(R.id.rili7);
		rili8 = (CheckBox) findViewById(R.id.rili8);
		rili9 = (CheckBox) findViewById(R.id.rili9);
		rili10 = (CheckBox) findViewById(R.id.rili10);
		rili11 = (CheckBox) findViewById(R.id.rili11);
		rili12 = (CheckBox) findViewById(R.id.rili12);
		rili13 = (CheckBox) findViewById(R.id.rili13);
		rili14 = (CheckBox) findViewById(R.id.rili14);
		rili15 = (CheckBox) findViewById(R.id.rili15);
		rili16 = (CheckBox) findViewById(R.id.rili16);
		rili17 = (CheckBox) findViewById(R.id.rili17);
		rili18 = (CheckBox) findViewById(R.id.rili18);
		rili19 = (CheckBox) findViewById(R.id.rili19);
		rili20 = (CheckBox) findViewById(R.id.rili20);
		rili21 = (CheckBox) findViewById(R.id.rili21);
		rili22 = (CheckBox) findViewById(R.id.rili22);
		rili23 = (CheckBox) findViewById(R.id.rili23);
		rili24 = (CheckBox) findViewById(R.id.rili24);
		rili25 = (CheckBox) findViewById(R.id.rili25);
		rili26 = (CheckBox) findViewById(R.id.rili26);
		rili27 = (CheckBox) findViewById(R.id.rili27);
		rili28 = (CheckBox) findViewById(R.id.rili28);
		rili29 = (CheckBox) findViewById(R.id.rili29);
		rili30 = (CheckBox) findViewById(R.id.rili30);
		rili31 = (CheckBox) findViewById(R.id.rili31);
		rili32 = (CheckBox) findViewById(R.id.rili32);
		rili33 = (CheckBox) findViewById(R.id.rili33);
		rili34 = (CheckBox) findViewById(R.id.rili34);
		rili35 = (CheckBox) findViewById(R.id.rili35);
		rili36 = (CheckBox) findViewById(R.id.rili36);
		rili37 = (CheckBox) findViewById(R.id.rili37);
		rili38 = (CheckBox) findViewById(R.id.rili38);
		rili39 = (CheckBox) findViewById(R.id.rili39);
		rili40 = (CheckBox) findViewById(R.id.rili40);
		rili41 = (CheckBox) findViewById(R.id.rili41);
		rili42 = (CheckBox) findViewById(R.id.rili42);
		rili1.setOnClickListener(listener);
		rili2.setOnClickListener(listener);
		rili3.setOnClickListener(listener);
		rili4.setOnClickListener(listener);
		rili5.setOnClickListener(listener);
		rili6.setOnClickListener(listener);
		rili7.setOnClickListener(listener);
		rili8.setOnClickListener(listener);
		rili9.setOnClickListener(listener);
		rili10.setOnClickListener(listener);
		rili11.setOnClickListener(listener);
		rili12.setOnClickListener(listener);
		rili13.setOnClickListener(listener);
		rili14.setOnClickListener(listener);
		rili15.setOnClickListener(listener);
		rili16.setOnClickListener(listener);
		rili17.setOnClickListener(listener);
		rili18.setOnClickListener(listener);
		rili19.setOnClickListener(listener);
		rili20.setOnClickListener(listener);
		rili21.setOnClickListener(listener);
		rili22.setOnClickListener(listener);
		rili23.setOnClickListener(listener);
		rili24.setOnClickListener(listener);
		rili25.setOnClickListener(listener);
		rili26.setOnClickListener(listener);
		rili27.setOnClickListener(listener);
		rili28.setOnClickListener(listener);
		rili29.setOnClickListener(listener);
		rili30.setOnClickListener(listener);
		rili31.setOnClickListener(listener);
		rili32.setOnClickListener(listener);
		rili33.setOnClickListener(listener);
		rili34.setOnClickListener(listener);
		rili35.setOnClickListener(listener);
		rili36.setOnClickListener(listener);
		rili37.setOnClickListener(listener);
		rili38.setOnClickListener(listener);
		rili39.setOnClickListener(listener);
		rili40.setOnClickListener(listener);
		rili41.setOnClickListener(listener);
		rili42.setOnClickListener(listener);
		
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
	 * 此页面的滑动监听方法 如下；
	 */
	public boolean onTouch(View v, MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			  //当手指按下的时候
			  x1 = event.getX();
			  y1 = event.getY();
			 }
				if (event.getAction() == MotionEvent.ACTION_UP) {
					// 当手指离开的时候
					x2 = event.getX();
					y2 = event.getY();
					if (y1 - y2 > 50) {
						/*Toast.makeText(BeiYunRiLiActivity.this, "向上滑",
								Toast.LENGTH_SHORT).show();*/
					} else if (y2 - y1 > 50) {
						/*Toast.makeText(BeiYunRiLiActivity.this, "向下滑",
								Toast.LENGTH_SHORT).show();*/
					} else if (x1 - x2 > 20) {
						/*Toast.makeText(BeiYunRiLiActivity.this, "向左滑，跳转到下一个月",
								Toast.LENGTH_SHORT).show();*/
						numb++;
						timeChange();
						if(numb<=0){
							xiugaijingqi.setVisibility(View.VISIBLE);
						}else{
							xiugaijingqi.setVisibility(View.GONE);
						}	
					} else if (x2 - x1 > 20) {
						/*Toast.makeText(BeiYunRiLiActivity.this, "向右滑，跳转到上一个月",
								Toast.LENGTH_SHORT).show();*/
						numb--;
						timeChange();
						if(numb<=0){
							xiugaijingqi.setVisibility(View.VISIBLE);
						}else{
							xiugaijingqi.setVisibility(View.GONE);
						}	
					}
				}
				return true;
	}
	/**
	 * 系统返回键的监听 事件
	 *//*
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK){
			Intent intent =new Intent(BeiYunRiLiActivity.this,ShouYeActivity.class);
			startActivity(intent);
			BeiYunRiLiActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}*/

	
}
/**
 * 获得某月的天数  自定义类
 * @author Administrator
 *
 */
class DateUtil {
	/**
	 * 获取天数的方法
	 * @param year
	 * @param month
	 * @return
	 */
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