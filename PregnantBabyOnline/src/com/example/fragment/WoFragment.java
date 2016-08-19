package com.example.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lei.CircularImage;
import com.example.pregnantbabyonline.BeiYunRiLiActivity;
import com.example.pregnantbabyonline.EmperatureActivity;
import com.example.pregnantbabyonline.FeedbackActivity;
import com.example.pregnantbabyonline.R;
import com.example.pregnantbabyonline.ShareActivity;
import com.example.pregnantbabyonline.SheZhiActivity;


public class WoFragment  extends Fragment{
	int guanzhu=0;
	int shoucang=0;
	
	LinearLayout linearlayout1,linearlayout2,linearlayout3,linearlayout4,linearlayout5,linearlayout6;
	LayoutInflater inflater;
	View view;
	TextView textview,textview2;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view=inflater.inflate(R.layout.activity_mine, null);
		CircularImage cover_user_photo = (CircularImage)view. findViewById(R.id.cover_user_photo);
		 cover_user_photo.setImageResource(R.drawable.tp6);
		guanzhu=0;
		shoucang=0;
		 getSave();
		 
	        linearlayout1=(LinearLayout)view.findViewById(R.id.linearLayout1);
	        linearlayout2=(LinearLayout)view.findViewById(R.id.linearLayout7);
	        linearlayout3=(LinearLayout)view.findViewById(R.id.linearLayout3);
	        linearlayout4=(LinearLayout)view.findViewById(R.id.linearLayout4);
	        linearlayout5=(LinearLayout)view.findViewById(R.id.linearLayout5);
	        linearlayout6=(LinearLayout)view.findViewById(R.id.linearLayout6);
	        textview=(TextView)view.findViewById(R.id.guanzhu);
	        textview2 =(TextView)view.findViewById(R.id.shoucang);
	     
	        textview.setOnClickListener(onClickListener);
	        textview2.setOnClickListener(onClickListener);
	        linearlayout1.setOnClickListener(onClickListener);
	        linearlayout2.setOnClickListener(onClickListener);
	        linearlayout3.setOnClickListener(onClickListener);
	        linearlayout4.setOnClickListener(onClickListener);
	        linearlayout5.setOnClickListener(onClickListener);
	        linearlayout6.setOnClickListener(onClickListener);
	        
	        cover_user_photo.setOnClickListener(onClickListener2);
	        textview2.setText(guanzhu+"");
	        textview.setText(shoucang+"");
		return view;
	}
	
	OnClickListener onClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		switch (v.getId()) {
		case R.id.linearLayout1:
			Toast.makeText(getActivity(), "暂无推送消息", Toast.LENGTH_SHORT).show();
			break;
		case R.id.linearLayout7:
			Intent intent1=new Intent(getActivity(), FeedbackActivity.class);
			startActivity(intent1);
			break;
		case R.id.linearLayout3:
			Intent intent=new Intent(getActivity(), BeiYunRiLiActivity.class);
			startActivity(intent);
			break;
		case R.id.linearLayout4:
			Intent intent3=new Intent(getActivity(), EmperatureActivity.class);
			startActivity(intent3);
	
			break;
		case R.id.linearLayout5:
			Intent intent2=new Intent(getActivity(), ShareActivity.class);
			startActivity(intent2);
			
			break;
		case R.id.linearLayout6:
<<<<<<< HEAD
			startActivity(new Intent(getActivity(),SheZhiActivity.class));
=======
			//设置
			/*intent=new Intent(getActivity(), SheZhiActivity.class);
			startActivity(intent);*/
>>>>>>> feature/zhangjian
			break;
			
		case R.id.shoucang:
		
			break;
			
		case R.id.guanzhu:
			
			break;

		default:
			break;
		}
			
		}
	};
	
	OnClickListener onClickListener2=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(getActivity(), "点击更换用户", Toast.LENGTH_SHORT).show();
			
		}
	};
	
	public void getSave(){
		guanzhu=0;
		shoucang=0;
		
		SharedPreferences sharedPreferences = getActivity().getSharedPreferences("quanziChild", getActivity().MODE_PRIVATE);
		for(int numb=1;numb<5;numb++){
			String str=numb+"关注";
			String str2=sharedPreferences.getString(str, "1");
			if(str2.equals("已关注")){
				guanzhu++;
			}
		}
		for(int numb=1;numb<5;numb++){
			String str=numb+"订阅";
			String str2=sharedPreferences.getString(str, "1");
			if(str2.equals("已订阅")){
				shoucang++;
			}
		}
		
	}

	/*@Override
	public void onResume() {
		// TODO Auto-generated method stub
	guanzhu=0;
	shoucang=0;
		super.onResume();
	}*/

	
	
}