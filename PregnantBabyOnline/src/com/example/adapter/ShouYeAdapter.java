package com.example.adapter;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lei.ShouYeListview;

public class ShouYeAdapter extends BaseAdapter{
	LayoutInflater inflater;
	Context context;
	List<ShouYeListview> list;
	int item;
	int[] id;
	
	public ShouYeAdapter() {
	}
	public ShouYeAdapter(Context context,int item){
		super();
		this.context=context;
		this.item=item;
	}
	public ShouYeAdapter(Context context,List<ShouYeListview> list,int item,int[] id){
		super();
		this.context=context;
		this.list=list;
		this.item=item;
		this.id=id;
		inflater=LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
			convertView=inflater.inflate(item, null);
		}
		ImageView tupian=(ImageView)convertView.findViewById(id[0]);
		TextView name=(TextView)convertView.findViewById(id[1]);
		TextView title=(TextView)convertView.findViewById(id[2]);
		TextView neirong=(TextView)convertView.findViewById(id[3]);
		ShouYeListview shouye=list.get(position);
		tupian.setImageResource(shouye.getTupian());
		name.setText(shouye.getName());
		title.setText(shouye.getTitle());
		neirong.setText(shouye.getNeirong());
		return convertView;
	}

}
