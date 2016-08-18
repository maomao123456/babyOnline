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
		map.put("title", "吃素食导致不孕？备孕期间如何调节饮食");
		map.put("text", "备孕");
		map.put("image", R.drawable.tp1);
		list.add(map);
		
		map=new HashMap<String, Object>();
		map.put("title", "早餐丰富有助怀孕是真的吗？");
		map.put("text", "备孕");
		map.put("image", R.drawable.tp2);
		list.add(map);
		
		map=new HashMap<String, Object>();
		map.put("title", "备孕营养讲究，不可盲目服用保健品");
		map.put("text", "备孕");
		map.put("image", R.drawable.tp3);
		list.add(map);
		
		map=new HashMap<String, Object>();
		map.put("title", "备孕期需要吃复合维生素嘛？");
		map.put("text", "备孕");
		map.put("image", R.drawable.tp4);
		list.add(map);
		
		map=new HashMap<String, Object>();
		map.put("title", "想生宝宝时应该如何储备营养？");
		map.put("text", "备孕");
		map.put("image", R.drawable.tp5);
		list.add(map);
		
		map=new HashMap<String, Object>();
		map.put("title", "想怀孕应该少吃肉？没有研究证实这点！");
		map.put("text","备孕");
		map.put("image", R.drawable.tp6);
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
					Intent intent=new Intent(PabulumActivity.this, SonActivity.class);
					startActivity(intent);
					Toast.makeText(PabulumActivity.this, "1", Toast.LENGTH_SHORT).show();
					break;
						
				case 1:
					Intent intent1=new Intent(PabulumActivity.this, SonActivity.class);
					startActivity(intent1);
					Toast.makeText(PabulumActivity.this, "2", Toast.LENGTH_SHORT).show();
					break;
					
				case 2:
					Intent intent2=new Intent(PabulumActivity.this, SonActivity.class);
					startActivity(intent2);
					Toast.makeText(PabulumActivity.this, "3", Toast.LENGTH_SHORT).show();
					break;
					
				case 3:
					Intent intent3=new Intent(PabulumActivity.this, SonActivity.class);
					startActivity(intent3);
					Toast.makeText(PabulumActivity.this, "4", Toast.LENGTH_SHORT).show();
					break;
					
				case 4:
					Intent intent4=new Intent(PabulumActivity.this, SonActivity.class);
					startActivity(intent4);
					Toast.makeText(PabulumActivity.this, "5", Toast.LENGTH_SHORT).show();
					break;
					
				case 5:
					Intent intent5=new Intent(PabulumActivity.this, SonActivity.class);
					startActivity(intent5);
					Toast.makeText(PabulumActivity.this, "6", Toast.LENGTH_SHORT).show();
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
			Toast.makeText(PabulumActivity.this, "返回",Toast.LENGTH_SHORT).show();
			Intent intent=new Intent(PabulumActivity.this, ShouyeActivity.class);
			startActivity(intent);
			PabulumActivity.this.finish();
		}
	};
	
	
}