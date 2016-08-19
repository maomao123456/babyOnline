package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.lei.ShiPinListview;
import com.example.pregnantbabyonline.R;

public class ShiPinAdapter extends BaseAdapter{
	LayoutInflater inflater;
	Context context;
	List<ShiPinListview> list;
	int item;
	int[] id;
	Uri uri;
	
	public ShiPinAdapter() {
	}
	
	public ShiPinAdapter(Context context,List<ShiPinListview> list,int item,int[] id){
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
			convertView=inflater.inflate(item, null);
		}
		ImageView tupian=(ImageView)convertView.findViewById(id[0]);
		TextView title=(TextView)convertView.findViewById(id[1]);
		TextView length=(TextView)convertView.findViewById(id[2]);
		ShiPinListview shipin=list.get(position);
		tupian.setImageResource(shipin.getTuPian());
		title.setText(shipin.getTitle());
		length.setText(shipin.getLength());
		tupian.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch(position){
				case 0:
					Toast.makeText(context, "进入到宝宝健康知识视频", Toast.LENGTH_SHORT).show();
					uri=Uri.parse("http://v.ku6.com/show/INx5eiWPMb4TaOwQIbJPrA...html");
					break;
				case 1:
					Toast.makeText(context, "进入到宝宝换尿布视频", Toast.LENGTH_SHORT).show();
					uri=Uri.parse("http://my.tv.sohu.com/us/63258879/57645231.shtml");
					break;
				case 2:
					Toast.makeText(context, "进入到交朋友视频", Toast.LENGTH_SHORT).show();
					uri=Uri.parse("http://v.youku.com/v_show/id_XMjQ0MzgzMDc2.html?beta&");
					break;
				}
				enterWeb();
			}
			
		});
		return convertView;
	}
	public void enterWeb(){//进入网页
		Intent intent=new Intent(Intent.ACTION_VIEW,uri);
		context.startActivity(intent);
	}
}
