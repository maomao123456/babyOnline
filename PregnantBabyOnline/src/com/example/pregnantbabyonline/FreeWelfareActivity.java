package com.example.pregnantbabyonline;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.baseadapter.ShouYeAdapter;
import com.example.lei.ShouYeListview;

public class FreeWelfareActivity extends Activity{
	ListView listview;
	ShouYeAdapter adapter;
	ShouYeListview shouye;
	List<ShouYeListview> list;
	ImageView back;
	TextView textview;
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
		textview=(TextView)findViewById(R.id.textview_bedstory);
		textview.setText("免费福利");
		adapter=new ShouYeAdapter(FreeWelfareActivity.this, list,R.layout.listview_item_baby,id);
		listview.setAdapter(adapter);
		back.setOnClickListener(clickListener);
		listview.setOnItemClickListener(onItemClickListener);
	}
	public void getList(){
		list=new ArrayList<ShouYeListview>();
		for(int i=0;i<10;i++){
			shouye=new ShouYeListview();
			shouye.setTupian(R.drawable.fuli_baby);
			shouye.setTitle("母婴健康体检");
			shouye.setName("重庆济仁医院");
			shouye.setNeirong(getString(R.string.free_welfare));
			list.add(shouye);
		}
	}
	OnClickListener clickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.imageview_back_bedstory:
				Intent intent=new Intent(FreeWelfareActivity.this,ShouYeActivity.class);
				startActivity(intent);
				FreeWelfareActivity.this.finish();
				break;
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
				Toast.makeText(FreeWelfareActivity.this, "进入第一项福利", Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Toast.makeText(FreeWelfareActivity.this, "进入第二项福利", Toast.LENGTH_SHORT).show();
				break;
			case 2:
				Toast.makeText(FreeWelfareActivity.this, "进入第三项福利", Toast.LENGTH_SHORT).show();
				break;
			case 3:
				Toast.makeText(FreeWelfareActivity.this, "进入第四项福利", Toast.LENGTH_SHORT).show();
				break;
			case 4:
				Toast.makeText(FreeWelfareActivity.this, "进入第五项福利", Toast.LENGTH_SHORT).show();
				break;
			case 5:
				Toast.makeText(FreeWelfareActivity.this, "进入第六项福利", Toast.LENGTH_SHORT).show();
				break;
			case 6:
				Toast.makeText(FreeWelfareActivity.this, "进入第七项福利", Toast.LENGTH_SHORT).show();
				break;
			case 7:
				Toast.makeText(FreeWelfareActivity.this, "进入第八项福利", Toast.LENGTH_SHORT).show();
				break;
			case 8:
				Toast.makeText(FreeWelfareActivity.this, "进入第九项福利", Toast.LENGTH_SHORT).show();
				break;
			case 9:
				Toast.makeText(FreeWelfareActivity.this, "进入第十项福利", Toast.LENGTH_SHORT).show();
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