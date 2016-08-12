package com.example.fragment;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.ShouYeAdapter;
import com.example.lei.ShouYeListview;
import com.example.pregnantbabyonline.CangJingGeActivity;
import com.example.pregnantbabyonline.R;

public class ShouYeFragment extends Fragment{
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
	
	int[] id={R.id.listview_image_education_baby,R.id.listview_title_baby,
			R.id.listview_bigtitle_baby,R.id.listview_neirong_baby};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view=(View)inflater.inflate(R.layout.fragment_shouye, null);
		headView=(View)inflater.inflate(R.layout.fragment_baby, null);
		listview=(ListView)view.findViewById(R.id.listview_shouye);
		listview.addHeaderView(headView);
		getList();
		adapter=new ShouYeAdapter(getActivity(), list,R.layout.listview_item_baby,id);
		listview.setAdapter(adapter);
		initView();
		date();
		dianji();
		seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
		return view;
	}
	public void initView(){
		
		top_text=(TextView)headView.findViewById(R.id.textview_baby_top);
		image_head=(ImageView)headView.findViewById(R.id.image_baby_head);
		shengao=(TextView)headView.findViewById(R.id.textview_shengao);
		tizhong=(TextView)headView.findViewById(R.id.textview_tizhong);
		seekBar=(SeekBar)headView.findViewById(R.id.seekbar_baby);
		//baby_future=(ImageView)view.findViewById(R.id.baby_future);
		chusheng1=(TextView)headView.findViewById(R.id.textview_chusheng1);
		yisui=(TextView)headView.findViewById(R.id.textview_yisui);
		chusheng2=(TextView)headView.findViewById(R.id.textview_chusheng2);
		baby_now=(TextView)headView.findViewById(R.id.textview_baby_now_state);
		baby_next=(TextView)headView.findViewById(R.id.textview_baby_next_state);
		now_date=(TextView)headView.findViewById(R.id.textview_baby_now_state_date);
		next_date=(TextView)headView.findViewById(R.id.textview_baby_next_state_date);
		baibaoxiang=(TextView)headView.findViewById(R.id.textview_baibaoxiang_baby);
		yimiao=(TextView)headView.findViewById(R.id.textview_yimiao_baby);
		eat=(TextView)headView.findViewById(R.id.textview_eat_baby);
		fuli=(TextView)headView.findViewById(R.id.textview_fuli_baby);
		story=(TextView)headView.findViewById(R.id.textview_story_baby);
		cangjingge=(TextView)headView.findViewById(R.id.textview_cangjingge_baby);
		shouce=(TextView)headView.findViewById(R.id.textview_zhishishouce_baby);
	}
	public void getList(){
		list=new ArrayList<ShouYeListview>();
		shouye=new ShouYeListview();
		shouye.setTupian(R.drawable.education);
		shouye.setTitle("教育无处不在");
		shouye.setName("宝宝发育");
		shouye.setNeirong(getString(R.string.baby_neirong));
		list.add(shouye);
	}
	public void dianji(){
		image_head.setOnClickListener(onClickListener);
		yimiao.setOnClickListener(onClickListener);
		eat.setOnClickListener(onClickListener);
		fuli.setOnClickListener(onClickListener);
		story.setOnClickListener(onClickListener);
		cangjingge.setOnClickListener(onClickListener);
		shouce.setOnClickListener(onClickListener);
	}
	OnClickListener onClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch(view.getId()){
			case R.id.image_baby_head:
				Toast.makeText(getActivity(), "跳转到更换头像页面", Toast.LENGTH_SHORT).show();
				break;
			case R.id.textview_yimiao_baby:
				Toast.makeText(getActivity(), "跳转到疫苗接种页面", Toast.LENGTH_SHORT).show();
				break;
			case R.id.textview_eat_baby:
				Toast.makeText(getActivity(), "跳转到能不能吃页面", Toast.LENGTH_SHORT).show();
				break;
			case R.id.textview_fuli_baby:
				Toast.makeText(getActivity(), "跳转到免费福利页面", Toast.LENGTH_SHORT).show();
				break;
			case R.id.textview_story_baby:
				Toast.makeText(getActivity(), "跳转到睡前故事页面", Toast.LENGTH_SHORT).show();
				break;
			case R.id.textview_zhishishouce_baby:
				Toast.makeText(getActivity(), "跳转到知识手册页面", Toast.LENGTH_SHORT).show();
				break;
			case R.id.textview_cangjingge_baby:
				Toast.makeText(getActivity(), "跳转到藏经阁页面", Toast.LENGTH_SHORT).show();
				startActivity(new Intent(getActivity(),CangJingGeActivity.class));
				break;
			}
		}
	};
	public void date(){//设置默认日期
		date=new Date();
		now_date.setText(date.getMonth()+1+"月"+date.getDay()+"日");//今天的日期
		next_day=new Date();
		next_day.setDate(date.getDate()+1);
		next_date.setText(next_day.getMonth()+1+"月"+next_day.getDay()+"日");//明天的日期
	}
	OnSeekBarChangeListener seekBarChangeListener=new OnSeekBarChangeListener() {
		
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
			if(progress>0){
				Date dateC=new Date();
				Date nextDayC=new Date();
				baby_now.setText(progress+"天");
				baby_next.setText(progress+1+"天");
				dateC.setDate(date.getDate()+progress);
				now_date.setText(dateC.getMonth()+1+"月"+dateC.getDay()+"日");
				nextDayC.setDate(date.getDate()+progress+1);
				next_date.setText(nextDayC.getMonth()+1+"月"+nextDayC.getDay()+"日");
			}
			
		}
	};
}
