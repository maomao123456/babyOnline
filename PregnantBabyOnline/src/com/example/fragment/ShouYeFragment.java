package com.example.fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.ShouYeAdapter;
import com.example.lei.ShouYeListview;
import com.example.pregnantbabyonline.BedStoryActivity;
import com.example.pregnantbabyonline.CangJingGeActivity;
import com.example.pregnantbabyonline.FreeWelfareActivity;
import com.example.pregnantbabyonline.PabulumActivity;
import com.example.pregnantbabyonline.R;

public class ShouYeFragment extends Fragment {
	TextView top_text;
	View view;
	ImageView image_head;
	TextView tizhong;
	TextView shengao;
	SeekBar seekBar;
	ImageView baby_future;
	TextView chusheng1;
	TextView yisui;
	TextView chusheng2;
	TextView baby_now;
	TextView baby_next;
	TextView now_date;
	TextView next_date;
	TextView baibaoxiang;
	TextView yimiao;
	TextView eat;
	TextView fuli;
	TextView story;
	TextView cangjingge;
	TextView shouce;
	LinearLayout buju1;
	LinearLayout buju2;
	Date date;
	Date next_day;
	ListView listview;
	View headView;
	ShouYeAdapter adapter;
	ShouYeListview shouye;
	List<ShouYeListview> list;
	Uri uri;

	int[] id = { R.id.listview_image_education_baby, R.id.listview_title_baby,
			R.id.listview_bigtitle_baby, R.id.listview_neirong_baby };

	PopupWindow popWindow;
	View touxiang;
	View yimiaoPop;
	int whichNumb;//判断创建哪个popupWindow
	int numb;//progress的初始值
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view=(View)inflater.inflate(R.layout.fragment_shouye, null);
		touxiang=(View)inflater.inflate(R.layout.baby_popupwindow_touxiang,null);
		yimiaoPop=(View)inflater.inflate(R.layout.baby_popupwindow_yimiao, null);
		headView=(View)inflater.inflate(R.layout.fragment_baby, null);
		listview=(ListView)view.findViewById(R.id.listview_shouye);
		listview.addHeaderView(headView);
		getList();
		adapter = new ShouYeAdapter(getActivity(), list,
				R.layout.listview_item_baby, id);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(onItemClickListener);
		initView();
		date();
		dianji();
		seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
		return view;
	}

	public void initView() {

		top_text = (TextView) headView.findViewById(R.id.textview_baby_top);
		image_head = (ImageView) headView.findViewById(R.id.image_baby_head);
		shengao = (TextView) headView.findViewById(R.id.textview_shengao);
		tizhong = (TextView) headView.findViewById(R.id.textview_tizhong);
		seekBar = (SeekBar) headView.findViewById(R.id.seekbar_baby);
		// baby_future=(ImageView)view.findViewById(R.id.baby_future);
		chusheng1 = (TextView) headView.findViewById(R.id.textview_chusheng1);
		yisui = (TextView) headView.findViewById(R.id.textview_yisui);
		chusheng2 = (TextView) headView.findViewById(R.id.textview_chusheng2);
		baby_now = (TextView) headView
				.findViewById(R.id.textview_baby_now_state);
		baby_next = (TextView) headView
				.findViewById(R.id.textview_baby_next_state);
		now_date = (TextView) headView
				.findViewById(R.id.textview_baby_now_state_date);
		next_date = (TextView) headView
				.findViewById(R.id.textview_baby_next_state_date);
		baibaoxiang = (TextView) headView
				.findViewById(R.id.textview_baibaoxiang_baby);
		yimiao = (TextView) headView.findViewById(R.id.textview_yimiao_baby);
		eat = (TextView) headView.findViewById(R.id.textview_eat_baby);
		fuli = (TextView) headView.findViewById(R.id.textview_fuli_baby);
		story = (TextView) headView.findViewById(R.id.textview_story_baby);
		cangjingge = (TextView) headView
				.findViewById(R.id.textview_cangjingge_baby);
		shouce = (TextView) headView
				.findViewById(R.id.textview_zhishishouce_baby);
	}

	public void getList() {
		list = new ArrayList<ShouYeListview>();
		shouye = new ShouYeListview();
		shouye.setTupian(R.drawable.education);
		shouye.setTitle("教育无处不在");
		shouye.setName("宝宝发育");
		shouye.setNeirong(getString(R.string.baby_neirong));
		list.add(shouye);

		shouye = new ShouYeListview();
		shouye.setTupian(R.drawable.education);
		shouye.setTitle("学前必读");
		shouye.setName("宝宝读书");
		shouye.setNeirong("唐诗是中国文化的精粹，其节奏鲜明、朗朗上口，非常适合儿童启蒙诵读，"
				+ "被称为“最成功的启蒙教材”。");
		list.add(shouye);
	}

	OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			switch (position) {
			case 1:
				uri=Uri.parse("http://baby.163.com/15/1102/07/B7D9DECI00364O2I.html");
				break;
			case 2:
				uri=Uri.parse("http://www.youban.com/mp3-t4422.html");
				break;
			}
			enterWeb();
		}
	};

	public void dianji() {
		image_head.setOnClickListener(onClickListener);
		yimiao.setOnClickListener(onClickListener);
		eat.setOnClickListener(onClickListener);
		fuli.setOnClickListener(onClickListener);
		story.setOnClickListener(onClickListener);
		cangjingge.setOnClickListener(onClickListener);
		shouce.setOnClickListener(onClickListener);
	}

	OnClickListener onClickListener=new OnClickListener() {//点击事件设置
		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch (view.getId()) {
			case R.id.image_baby_head:
				whichNumb=1;
				createPopWindow();
				break;
			case R.id.textview_yimiao_baby:
				whichNumb=2;
				createPopWindow();
				break;
			case R.id.textview_eat_baby:
				startActivity(new Intent(getActivity(), PabulumActivity.class));
				break;
			case R.id.textview_fuli_baby:
				startActivity(new Intent(getActivity(),
						FreeWelfareActivity.class));
				break;
			case R.id.textview_story_baby:
				startActivity(new Intent(getActivity(),BedStoryActivity.class));
				break;
			case R.id.textview_zhishishouce_baby:
				Toast.makeText(getActivity(), "跳转到知识手册页面", Toast.LENGTH_SHORT).show();
				uri=Uri.parse("http://www.doc88.com/p-993198458844.html");
				enterWeb();
				break;
			case R.id.textview_cangjingge_baby:
				startActivity(new Intent(getActivity(),
						CangJingGeActivity.class));
				break;
			case R.id.babyhead_pop_first:
				Intent intent=new Intent();
				intent.setAction(Intent.ACTION_GET_CONTENT);
				intent.setType("image/*");
				startActivity(intent);
				break;
			case R.id.babyhead_pop_second:
				Toast.makeText(getActivity(), "跳转到网页", Toast.LENGTH_SHORT).show();
				uri=Uri.parse("https://www.baidu.com");
				enterWeb();
				break;
			case R.id.baby_yimiao3:
				uri=Uri.parse("http://wenku.baidu.com/view/2274cb1afad6195f312ba68c.html");
				enterWeb();
				break;
			}
		}
	};

	public void date() {// 设置默认的progress和天数//设置默认日期
		try {
			getNumb("2016-08-12");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(numb);
		seekBar.setProgress(numb);
		baby_now.setText(numb + "天");
		baby_next.setText(numb + 1 + "天");
		Calendar calCurrent = Calendar.getInstance();
		int today = calCurrent.get(Calendar.DATE);
		int month = calCurrent.get(Calendar.MONTH) + 1;
		now_date.setText(month + "月" + today + "日");// 今天的日期
		Calendar calNext = Calendar.getInstance();
		calNext.add(Calendar.DAY_OF_MONTH, 1);
		int nextday = calNext.get(Calendar.DATE);
		int nextmonth = calNext.get(Calendar.MONTH) + 1;
		next_date.setText(nextmonth + "月" + nextday + "日");
	}
	OnSeekBarChangeListener seekBarChangeListener = new OnSeekBarChangeListener() {
		// 随seekBar改动的元素
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub
			if (progress > 0) {
				baby_now.setText(progress + "天");
				baby_next.setText(progress + 1 + "天");
				if(progress>360){
					tizhong.setText("体重:6.25~8.46kg");
					shengao.setText("身高:48.7~59.8cm");
					top_text.setText("此时你的宝宝已经一周岁了，他(她)已经在你的陪伴下悄然地成长了一年，也给你带来了从未体验过的"
							+ "难以忘记的快乐，你的心也会在那一声“麻麻”中渐渐融化。");
				}
				else if(progress>330){
					tizhong.setText("体重:5.85~8.06kg");
					shengao.setText("身高:48.3~59.4cm");
					top_text.setText("11个月宝宝的辅食开始变成主食，你应该要保证宝宝摄入足够的动物蛋白，辅食要少放盐、糖。此外，"
							+ "要开始锻炼宝宝克服怕生现象，要训练宝宝的独立性，让宝宝逐渐适应接触。");
				}
				else if(progress>300){
					tizhong.setText("体重:5.45~7.66kg");
					shengao.setText("身高:47.9~59.0cm");
					top_text.setText("孩子的身长会继续增加，给人的印象是瘦了, 陆续又长出2-4颗门牙,会说一两个字，"
							+ "能发出不同的声音表示不同的意思。");
				}
				else if(progress>270){
					tizhong.setText("体重:5.05~7.26kg");
					shengao.setText("身高:47.5~58.6cm");
					top_text.setText("这时已开始从婴儿的圆滚的体型转换到幼儿的体型,大多数婴儿在"
							+ "10月前乳牙已长出2-4颗。");
				}
				else if(progress>240){
					tizhong.setText("体重:4.65~6.86kg");
					shengao.setText("身高:47.1~58.2cm");
					top_text.setText("爬行越来越进步，从匍匐前行到四肢能撑起躯干灵活爬行；"
							+ "会朝自己看中的目标爬去并摇晃它。从此，婴儿的活动范围扩大至整个房间。");
				}
				else if(progress>210){
					tizhong.setText("体重:4.25~6.46kg");
					shengao.setText("身高:46.7~57.8cm");
					top_text.setText("7个月的宝宝开始模仿大人的咀嚼动作，左右移动上下颚。由于他还没有完全"
							+ "长出牙齿来咀嚼食物，所以最好还是喂他马铃薯泥、胡萝卜泥、麦片粥等糊状的食物");
				}
			    else if(progress>180){
					tizhong.setText("体重:3.85~6.16kg");
					shengao.setText("身高:46.3~57.4cm");
					top_text.setText("6个月的时候,妈咪在宝贝背后叫他的名字，宝贝会转头寻找妈咪，"
							+ "当宝贝不开心时会发出叫喊声，但不是哭声,能发出妈(mun)——妈的唇音");
			    }
				else if(progress>150){
					tizhong.setText("体重:4.25~6.56kg");
					shengao.setText("身高:46.7~57.8cm");
					top_text.setText("这段时期的婴儿，眉眼等五官也“长开了”，脸色红润而光滑，变得更可爱了。"
							+ "此时的宝宝已逐渐成熟起来，显露出活泼、可爱的体态，身长、体重增长速度开始较前减慢。");
				}
				else if(progress>120){
					tizhong.setText("体重:3.85~6.16kg");
					shengao.setText("身高:46.3~57.4cm");
					top_text.setText("此时的宝宝头部稳定居中，转动灵活，俯卧时能用手撑起头和胸。"
							+ "会翻身，能灵活变动姿势；喜欢用手触摸看到的东西，进行探索。");
				}
				else if(progress>90){
					tizhong.setText("体重:3.45~5.76kg");
					shengao.setText("身高:45.9~57.0cm");
					top_text.setText("3个月大的宝宝的目光会紧紧跟随着玩具，认识熟悉的面孔，喜欢玩自己的手，"
							+ "你会越来越多地听到他(她)咿咿呀呀地发音");
				}
				else if(progress>60){
					tizhong.setText("体重:3.05~5.36kg");
					shengao.setText("身高:45.5~56.6cm");
					top_text.setText("2个月的宝宝对铃声有反应，并会转动着头去寻找铃声。视线开始追踪人的脸；"
							+ "喜欢注视色彩鲜艳的物品；喜欢看明亮的地方。");
				}
				else if(progress>30){
					tizhong.setText("体重:2.66~4.96kg");
					shengao.setText("身高:45.1~56.2cm");
					top_text.setText("宝宝满月后体格发育迅速，宝宝能抬头，学会咿呀发音，"
							+ "这时期的宝宝吃奶量明显增加，所以称之为吃奶宝宝。");
				}else{
					top_text.setText("宝宝从诞生的那一刻到满月，这个小生命已不再被叫做胎儿了，是叫做新生儿。");
				}
				
				Calendar calCurrent = Calendar.getInstance();
				calCurrent.add(Calendar.DAY_OF_MONTH, progress-5);
				int today = calCurrent.get(Calendar.DATE);
				int month = calCurrent.get(Calendar.MONTH) + 1;
				now_date.setText(month + "月" + today + "日");// 今天的日期
				Calendar calNext = Calendar.getInstance();
				calNext.add(Calendar.DAY_OF_MONTH, progress-5 + 1);
				int nextday = calNext.get(Calendar.DATE);
				int nextmonth = calNext.get(Calendar.MONTH) + 1;
				next_date.setText(nextmonth + "月" + nextday + "日");// 明天的日期
			}
		}
	};

	@SuppressLint("NewApi")
	public void createPopWindow(){
		if(whichNumb==1){
			popWindow=new PopupWindow(touxiang, LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,true);
			popWindow.setBackgroundDrawable(new BitmapDrawable());
			popWindow.setTouchable(true);
			popWindow.setOutsideTouchable(true);
			popWindow.update();
			popWindow.setFocusable(true);
			popWindow.setTouchInterceptor(new OnTouchListener() {
				public boolean onTouch(View v, MotionEvent event) {
					return false;
				}
			});
			popWindow.showAtLocation(touxiang,Gravity.CENTER,0,-230);
			TextView title=(TextView)touxiang.findViewById(R.id.babyhead_pop_title);
			TextView first=(TextView)touxiang.findViewById(R.id.babyhead_pop_first);
			TextView second=(TextView)touxiang.findViewById(R.id.babyhead_pop_second);
			title.setText("选择宝宝的头像");
			first.setText("从相册中选择");
			second.setText("从网上选择");
			first.setOnClickListener(onClickListener);
			second.setOnClickListener(onClickListener);
		}else if(whichNumb==2){
			popWindow=new PopupWindow(yimiaoPop, LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,true);
			popWindow.setBackgroundDrawable(new BitmapDrawable());
			popWindow.setTouchable(true);
			popWindow.setOutsideTouchable(true);
			popWindow.update();
			popWindow.setFocusable(true);
			popWindow.setTouchInterceptor(new OnTouchListener() {
				public boolean onTouch(View v, MotionEvent event) {
					return false;
				}
			});
			TextView two=(TextView)yimiaoPop.findViewById(R.id.baby_yimiao2);
			TextView three=(TextView)yimiaoPop.findViewById(R.id.baby_yimiao3);
			two.setText("从出生开始宝宝就必须要打各种各样的预防针，宝妈们一定要注意了！详情请通过下面的网址来查看宝宝各个阶段的疫苗情况：");
			three.setText("http://wenku.baidu.com/view/2274cb1afad6195f312ba68c.html");
			three.setOnClickListener(onClickListener);
			popWindow.showAtLocation(yimiaoPop,Gravity.CENTER,0,200);
		}
	}
	public void enterWeb(){//进入网页
		Intent intent=new Intent(Intent.ACTION_VIEW,uri);
		startActivity(intent);
	}
	public void getNumb(String birthday) throws ParseException{
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date=simpleDateFormat.parse(birthday);
		long time=new Date().getTime()-date.getTime();
		numb=(int)(time/(1000*60*60*24));
	}
}
