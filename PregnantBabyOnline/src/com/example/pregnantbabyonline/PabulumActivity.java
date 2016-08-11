package com.example.pregnantbabyonline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/**
 * 备孕营养 页面
 * @author Administrator
 *
 */
public class PabulumActivity extends Activity{
	ListView listview;
	ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pabulum);
		imageView=(ImageView)findViewById(R.id.imageviewTitle);
		String [] from={"title","text","image"};
		int [] to={R.id.textview1,R.id.textview2,R.id.imageviewSimple};
		SimpleAdapter simpleAdapter=new SimpleAdapter(PabulumActivity.this, getData(), R.layout.activity_simple_adapter, from, to);
		listview=(ListView)findViewById(R.id.listview);
		listview.setAdapter(simpleAdapter);
		listview.setOnItemClickListener(onItemClickListener);
		imageView.setOnClickListener(onClickListener1);
		
	}
	
	public List<? extends Map<String, ?>> getData(){
		
		List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("title", "�Ե��²��У���������ε�����ʳ��");
		map.put("text", "����");
		map.put("image", R.drawable.tp1);
		list.add(map);
		
		map=new HashMap<String, Object>();
		map.put("title", "��ͷ�ʢ���������������");
		map.put("text", "����");
		map.put("image", R.drawable.tp2);
		list.add(map);
		
		map=new HashMap<String, Object>();
		map.put("title", "����Ӫ������������äĿ���ñ���Ʒ");
		map.put("text", "����");
		map.put("image", R.drawable.tp3);
		list.add(map);
		
		map=new HashMap<String, Object>();
		map.put("title", "��������Ҫ�Ը���ά�����");
		map.put("text", "����");
		map.put("image", R.drawable.tp4);
		list.add(map);
		
		map=new HashMap<String, Object>();
		map.put("title", "������ʱӦ����δ���Ӫ����");
		map.put("text", "����");
		map.put("image", R.drawable.tp5);
		list.add(map);
		
		map=new HashMap<String, Object>();
		map.put("title", "�뻳��Ӧ���ٳ��⣿û���о�֤ʵ��㣡");
		map.put("text","����");
		map.put("image", R.drawable.tp5);
		list.add(map);
	
		return list;
	}
	
	OnItemClickListener onItemClickListener=new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
				switch (position) {
				case 0:
					Toast.makeText(PabulumActivity.this, "ͼƬ1", Toast.LENGTH_SHORT).show();
					break;
						
				case 1:
					Toast.makeText(PabulumActivity.this, "ͼƬ2", Toast.LENGTH_SHORT).show();
					break;
					
				case 2:
					Toast.makeText(PabulumActivity.this, "ͼƬ3", Toast.LENGTH_SHORT).show();
					break;
					
				case 3:
					Toast.makeText(PabulumActivity.this, "ͼƬ4", Toast.LENGTH_SHORT).show();
					break;
					
				case 4:
					Toast.makeText(PabulumActivity.this, "ͼƬ5", Toast.LENGTH_SHORT).show();
					break;
					
				case 5:
					Toast.makeText(PabulumActivity.this, "ͼƬ6", Toast.LENGTH_SHORT).show();
					break;

				default:
					break;
				}
				
			}
			
		
	};
	
	OnClickListener onClickListener1=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(PabulumActivity.this, "����",Toast.LENGTH_SHORT).show();
			
			
		}
	};
	
	
}