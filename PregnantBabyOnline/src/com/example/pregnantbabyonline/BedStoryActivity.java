package com.example.pregnantbabyonline;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.ShouYeAdapter;
import com.example.lei.ShouYeListview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class BedStoryActivity extends Activity{
	ListView listview;
	ShouYeAdapter adapter;
	ShouYeListview shouye;
	List<ShouYeListview> list;
	ImageView back;
	Uri uri;
	int[] id={R.id.listview_image_education_baby,R.id.listview_title_baby,
			R.id.listview_bigtitle_baby,R.id.listview_neirong_baby};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bedstory);
		getList();
		listview=(ListView)findViewById(R.id.listview_bedstory);
		back=(ImageView)findViewById(R.id.imageview_back_bedstory);
		adapter=new ShouYeAdapter(BedStoryActivity.this, list,R.layout.listview_item_baby,id);
		listview.setAdapter(adapter);
		back.setOnClickListener(clickListener);
		listview.setOnItemClickListener(onItemClickListener);
	}
	public void getList(){
		list=new ArrayList<ShouYeListview>();
		for(int i=0;i<10;i++){
			shouye=new ShouYeListview();
			shouye.setTupian(R.drawable.education);
			shouye.setTitle("卖火柴的小女孩");
			shouye.setName("安徒生童话");
			shouye.setNeirong(getString(R.string.bed_story));
			list.add(shouye);
		}
	}
	OnClickListener clickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()==R.id.imageview_back_bedstory){
				Intent intent=new Intent(BedStoryActivity.this,ShouyeActivity.class);
				startActivity(intent);
				BedStoryActivity.this.finish();
			}
		}
	};
	OnItemClickListener onItemClickListener=new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			switch(position){
			case 0:
				Toast.makeText(BedStoryActivity.this, "进入第一则故事", Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Toast.makeText(BedStoryActivity.this, "进入第二则故事", Toast.LENGTH_SHORT).show();
				break;
			case 2:
				Toast.makeText(BedStoryActivity.this, "进入第三则故事", Toast.LENGTH_SHORT).show();
				break;
			case 3:
				Toast.makeText(BedStoryActivity.this, "进入第四则故事", Toast.LENGTH_SHORT).show();
				break;
			case 4:
				Toast.makeText(BedStoryActivity.this, "进入第五则故事", Toast.LENGTH_SHORT).show();
				break;
			case 5:
				Toast.makeText(BedStoryActivity.this, "进入第六则故事", Toast.LENGTH_SHORT).show();
				break;
			case 6:
				Toast.makeText(BedStoryActivity.this, "进入第七则故事", Toast.LENGTH_SHORT).show();
				break;
			case 7:
				Toast.makeText(BedStoryActivity.this, "进入第八则故事", Toast.LENGTH_SHORT).show();
				break;
			case 8:
				Toast.makeText(BedStoryActivity.this, "进入第九则故事", Toast.LENGTH_SHORT).show();
				break;
			case 9:
				Toast.makeText(BedStoryActivity.this, "进入第十则故事", Toast.LENGTH_SHORT).show();
				break;
			}
			uri=Uri.parse("https://www.baidu.com");
			enterWeb();
		}
	};
	public void enterWeb(){//进入网页
		Intent intent=new Intent(Intent.ACTION_VIEW,uri);
		startActivity(intent);
	}
}