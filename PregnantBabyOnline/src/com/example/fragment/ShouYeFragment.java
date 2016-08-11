package com.example.fragment;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

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
	
	int[] id={R.id.listview_image_education_baby,R.id.listview_title_baby,
			R.id.listview_bigtitle_baby,R.id.listview_neirong_baby};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view=(View)inflater.inflate(R.layout.fragment_baby, null);
		initView();
		dianji();
		seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
		return view;
	}
	public void initView(){
		top_text=(TextView)view.findViewById(R.id.textview_baby_top);
		image_head=(ImageView)view.findViewById(R.id.image_baby_head);
		shengao=(TextView)view.findViewById(R.id.textview_shengao);
		tizhong=(TextView)view.findViewById(R.id.textview_tizhong);
		seekBar=(SeekBar)view.findViewById(R.id.seekbar_baby);
		//baby_future=(ImageView)view.findViewById(R.id.baby_future);
		chusheng1=(TextView)view.findViewById(R.id.textview_chusheng1);
		yisui=(TextView)view.findViewById(R.id.textview_yisui);
		chusheng2=(TextView)view.findViewById(R.id.textview_chusheng2);
		baby_now=(TextView)view.findViewById(R.id.textview_baby_now_state);
		baby_next=(TextView)view.findViewById(R.id.textview_baby_next_state);
		now_date=(TextView)view.findViewById(R.id.textview_baby_now_state_date);
		next_date=(TextView)view.findViewById(R.id.textview_baby_next_state_date);
		baibaoxiang=(TextView)view.findViewById(R.id.textview_baibaoxiang_baby);
		yimiao=(TextView)view.findViewById(R.id.textview_yimiao_baby);
		eat=(TextView)view.findViewById(R.id.textview_eat_baby);
		fuli=(TextView)view.findViewById(R.id.textview_fuli_baby);
		story=(TextView)view.findViewById(R.id.textview_story_baby);
		cangjingge=(TextView)view.findViewById(R.id.textview_cangjingge_baby);
		shouce=(TextView)view.findViewById(R.id.textview_zhishishouce_baby);
		buju1=(LinearLayout)view.findViewById(R.id.buju_baby);
		buju2=(LinearLayout)view.findViewById(R.id.buju_baby2);
	}
	public void dianji(){
		image_head.setOnClickListener(onClickListener);
		yimiao.setOnClickListener(onClickListener);
		eat.setOnClickListener(onClickListener);
		fuli.setOnClickListener(onClickListener);
		story.setOnClickListener(onClickListener);
		cangjingge.setOnClickListener(onClickListener);
		shouce.setOnClickListener(onClickListener);
		buju1.setOnClickListener(onClickListener);
		buju2.setOnClickListener(onClickListener);
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
			case R.id.buju_baby:
				Toast.makeText(getActivity(), "跳转到宝宝发育页面", Toast.LENGTH_SHORT).show();
				break;
			case R.id.buju_baby2:
				Toast.makeText(getActivity(), "跳转到宝宝发育页面2", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};
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
				baby_now.setText(progress+"天");
				baby_next.setText(progress+1+"天");
			}
		}
	};
}