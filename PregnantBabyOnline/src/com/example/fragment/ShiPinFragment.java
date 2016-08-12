package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.ShiPinAdapter;
import com.example.lei.ShiPinListview;
import com.example.pregnantbabyonline.R;

public class ShiPinFragment extends Fragment{
	View view;
	ListView listview; 
	ShiPinAdapter adapter;
	List<ShiPinListview> list;
	ShiPinListview shipin;
	int[] id={R.id.listview_imageview_shipin,R.id.listview_title_shipin,
			R.id.listview_length_shipin};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view=inflater.inflate(R.layout.fragment_shipin, null);
		listview=(ListView)view.findViewById(R.id.listview_shipin);
		getList();
		adapter=new ShiPinAdapter(getActivity(), list, R.layout.listview_item_shipin, id);
		listview.setAdapter(adapter);
		return view;
	}
	public void getList(){
		list=new ArrayList<ShiPinListview>();
		shipin=new ShiPinListview();
		shipin.setTuPian(R.drawable.baby_health);
		shipin.setTitle("关注宝宝健康的那些常识，你知道多少");
		shipin.setLength("等待戈多/1'50");
		list.add(shipin);
		
		shipin=new ShiPinListview();
		shipin.setTuPian(R.drawable.baby_niaobu);
		shipin.setTitle("你知道给宝宝换尿布的正确步骤吗？");
		shipin.setLength("等待戈多/3'58");
		list.add(shipin);
		
		shipin=new ShiPinListview();
		shipin.setTuPian(R.drawable.baby_friend);
		shipin.setTitle("交友，从娃娃抓起");
		shipin.setLength("等待戈多/2'32");
		list.add(shipin);
	}
}
