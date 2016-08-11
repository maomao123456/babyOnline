package com.example.baseadapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lei.CircularImage;
import com.example.lei.QuanZiListview;
import com.example.pregnantbabyonline.R;

public class QuanZiAdapter extends BaseAdapter{

	LayoutInflater inflater;
	
	Context context;
	List<QuanZiListview> list;
	int item;
	int[] id;
	
	
	public QuanZiAdapter() {
		//super();
	}

	public QuanZiAdapter(Context context, List<QuanZiListview> list, int item,
			int[] id) {
		super();
		this.context = context;
		this.list = list;
		this.item = item;
		this.id = id;
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
		TextView name=(TextView)convertView.findViewById(id[0]);
		TextView title=(TextView)convertView.findViewById(id[1]);
		TextView neirong=(TextView)convertView.findViewById(id[2]);
		ImageView touxiang=(ImageView)convertView.findViewById(id[3]);
		CircularImage xiaotouxiang = (CircularImage)convertView. findViewById(id[4]);
		//xiaotouxiang.setImageResource(R.drawable.tp6); 
		//ImageView xiaotouxiang=(ImageView)convertView.findViewById(id[4]);
		
		TextView nicheng=(TextView)convertView.findViewById(id[5]);
		TextView huifu=(TextView)convertView.findViewById(id[6]);
		QuanZiListview quanzi=list.get(position);
		name.setText(quanzi.getName());
		title.setText(quanzi.getTitle());
		neirong.setText(quanzi.getNeirong());
		touxiang.setImageResource(quanzi.getTouxiang());
		xiaotouxiang.setImageResource(quanzi.getXiaotouxiang());
		nicheng.setText(quanzi.getNicheng());
		huifu.setText(quanzi.getHuifu());
		return convertView;
	}

}
