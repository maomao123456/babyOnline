package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lei.CircularImage;
import com.example.pregnantbabyonline.BeiYunRiLiActivity;
import com.example.pregnantbabyonline.R;


public class WoFragment  extends Fragment{
	LinearLayout linearlayout1,linearlayout2,linearlayout3,linearlayout4,linearlayout5,linearlayout6;
	LayoutInflater inflater;
	View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view=inflater.inflate(R.layout.activity_mine, null);
		CircularImage cover_user_photo = (CircularImage)view. findViewById(R.id.cover_user_photo);
		 cover_user_photo.setImageResource(R.drawable.tp6); 
		 
	        linearlayout1=(LinearLayout)view.findViewById(R.id.linearLayout1);
	        linearlayout2=(LinearLayout)view.findViewById(R.id.linearLayout2);
	        linearlayout3=(LinearLayout)view.findViewById(R.id.linearLayout3);
	        linearlayout4=(LinearLayout)view.findViewById(R.id.linearLayout4);
	        linearlayout5=(LinearLayout)view.findViewById(R.id.linearLayout5);
	        linearlayout6=(LinearLayout)view.findViewById(R.id.linearLayout6);
	        linearlayout1.setOnClickListener(onClickListener);
	        linearlayout2.setOnClickListener(onClickListener);
	        linearlayout3.setOnClickListener(onClickListener);
	        linearlayout4.setOnClickListener(onClickListener);
	        linearlayout5.setOnClickListener(onClickListener);
	        linearlayout6.setOnClickListener(onClickListener);
	        cover_user_photo.setOnClickListener(onClickListener2);
		return view;
	}
	
	OnClickListener onClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		switch (v.getId()) {
		case R.id.linearLayout1:
			Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
			break;
		case R.id.linearLayout2:
			Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();
			break;
		case R.id.linearLayout3:
			Intent intent=new Intent(getActivity(), BeiYunRiLiActivity.class);
			startActivity(intent);
			Toast.makeText(getActivity(), "跳转到日历界面", Toast.LENGTH_SHORT).show();
			break;
		case R.id.linearLayout4:
			Toast.makeText(getActivity(), "4", Toast.LENGTH_SHORT).show();
			break;
		case R.id.linearLayout5:
			Toast.makeText(getActivity(), "5", Toast.LENGTH_SHORT).show();
			break;
		case R.id.linearLayout6:
			Toast.makeText(getActivity(), "6", Toast.LENGTH_SHORT).show();
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
	
	
}