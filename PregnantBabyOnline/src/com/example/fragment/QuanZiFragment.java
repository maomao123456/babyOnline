package com.example.fragment;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat.Action;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.baseadapter.QuanZiAdapter;
import com.example.lei.QuanZiListview;
import com.example.pregnantbabyonline.R;
import com.example.pregnantbabyonline.WebActivity;

public class QuanZiFragment extends Fragment {
	View view;
	View headView;
	View listChlid;
	QuanZiAdapter adapter;
	QuanZiListview quanzi;
	List<QuanZiListview> list;
	ListView listview;
	ViewFlipper viewFlipper;
	RadioGroup rdgroup;
	RadioButton bt1;
	RadioButton bt2;
	RadioButton bt3;
	ImageView img1;
	ImageView img2;
	ImageView img3;
	int listPosition;
	int[] id = { R.id.quanzi_listview_name, R.id.quanzi_listview_title,
			R.id.quanzi_listview_neirong, R.id.qunazi_listView_touxiang,
			R.id.quanzi_listview_xioatouxiang, R.id.quanzi_listview_nicheng,
			R.id.quanzi_listview_huifu };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		headView=(View)inflater.inflate(R.layout.quanzi_listview_head_item, null);
		view = (View) inflater.inflate(R.layout.fragment_quanzi, null);
		initView();
		viewFlipper.startFlipping();
		senMsg();
		getlist();
		listview.addHeaderView(headView);
		adapter = new QuanZiAdapter(getActivity(), list,
				R.layout.quanzi_listview_item, id);
		listview.setAdapter(adapter);
		return view;
	}

	/**
	 * 通过id找到空件
	 */
	public void initView() {
		listview = (ListView) view.findViewById(R.id.quanzi_listView);
		viewFlipper = (ViewFlipper) headView.findViewById(R.id.quanzi_viewFlipper);
		viewFlipper.setOnTouchListener(l);
		rdgroup = (RadioGroup) headView.findViewById(R.id.quanzi_radioGroup);
		bt1 = (RadioButton) headView.findViewById(R.id.quanzi_radioButton1);
		bt2 = (RadioButton) headView.findViewById(R.id.quanzi_radioButton2);
		bt3 = (RadioButton) headView.findViewById(R.id.quanzi_radioButton3);
		img1=(ImageView)headView.findViewById(R.id.quanzi_img1);
		img2=(ImageView)headView.findViewById(R.id.quanzi_img2);
		img3=(ImageView)headView.findViewById(R.id.quanzi_img3);
		rdgroup.setOnCheckedChangeListener(checkedChangeListener);
		listview.setOnItemClickListener(onItemClickListener);
	}
	/**
	 * 全局点击事件
	 */
	OnTouchListener l=new OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			Uri uri2=Uri.parse("https://www.baidu.com");
			Uri uri3=Uri.parse("http://www.51baomu.cn/baomu1-6101-4-0-0-0-0-0-0-1-1-0-0.html");
				if(viewFlipper.getDisplayedChild()==0){
					Toast.makeText(getActivity(), "进入当前应用1",
							Toast.LENGTH_SHORT).show();
					//Intent intent=new Intent("", "http://tv.sogou.com/v?query=%CD%F2%BC%D2%D3%FD%D3%A4%CA%A6&p=40230600&tn=0&st=255");
					 Intent intent=new Intent();//创建Intent对象  
		                intent.setAction(Intent.ACTION_VIEW);//为Intent设置动作  
		                String data="http://tv.sogou.com/v?query=%CD%F2%BC%D2%D3%FD%D3%A4%CA%A6&p=40230600&tn=0&st=255";
		                //获取编辑框里面的文本内容  
		                intent.setData(Uri.parse(data));//为Intent设置数据  
		                startActivity(intent);//将Intent传递给Activity  
					startActivity(intent);
					
				}else if(viewFlipper.getDisplayedChild()==1){
					Toast.makeText(getActivity(), "进入当前应用2",
							Toast.LENGTH_SHORT).show();
					Intent intent=new Intent(Intent.ACTION_VIEW, uri2);
					startActivity(intent);
				}else if(viewFlipper.getDisplayedChild()==2){
					Toast.makeText(getActivity(), "进入当前应用3",
							Toast.LENGTH_SHORT).show();
					Intent intent=new Intent(Intent.ACTION_VIEW, uri3);
					//调用webview跳转到webviewActivity 地址详见webViewActivity  
					Intent intent2=new Intent(getActivity(), WebActivity.class);
					startActivity(intent2);
				}
			
			return false;
		}
	};
	/**
	 * listview的点击事件
	 */
	OnItemClickListener onItemClickListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			switch (position) {
			case 1:
				listPosition=1;
				Toast.makeText(getActivity(), "你所在位置为"+position+"view的id为"+view.getId(),
						Toast.LENGTH_SHORT).show();
				System.out.println(listPosition+"位置12124545454");
				break;
			case 2:
				listPosition=2;
				Toast.makeText(getActivity(), "你所在位置为"+position+"view的id为"+view.getId(),
						Toast.LENGTH_SHORT).show();
				break;
			case 3:
				listPosition=3;
				Toast.makeText(getActivity(), "你所在位置为"+position+"view的id为"+view.getId(),
						Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		}

	};
	/**
	 * 圆点的监听事件
	 */
	OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			if (bt1.isChecked()) {
				viewFlipper.stopFlipping();
				viewFlipper.setDisplayedChild(0);
				viewFlipper.startFlipping();
			} else if (bt2.isChecked()) {
				viewFlipper.stopFlipping();
				viewFlipper.setDisplayedChild(1);
				viewFlipper.startFlipping();
			} else if (bt3.isChecked()) {
				viewFlipper.stopFlipping();
				viewFlipper.setDisplayedChild(2);
				viewFlipper.startFlipping();
			}
		}
	};

	/**
	 * 图片轮播 并绑定圆点指示器
	 */
	public void bangding() {
		if (viewFlipper.getDisplayedChild() == 0) {
			bt1.setChecked(true);
		} else if (viewFlipper.getDisplayedChild() == 1) {
			bt2.setChecked(true);
		} else if (viewFlipper.getDisplayedChild() == 2) {
			bt3.setChecked(true);
		}
	}
	/**
	 * 间隔时间发送消息进行更新广告
	 */
	public void senMsg() {
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < i + 1; i++) {
					try {
						Thread.sleep(50);
						Message msg = new Message();
						handler.sendMessage(msg);
						if (i == 10) {
							i = 0;
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			bangding();
		};
	};

	/**
	 * 添加并获得list集合中的数据
	 */
	public void getlist() {
		list = new ArrayList<QuanZiListview>();
		quanzi = new QuanZiListview();
		quanzi.setName("时尚辣妈");
		quanzi.setTitle("50岁，离过两次婚，带着三个娃...");
		quanzi.setNeirong("一个女人，50多岁。先后经历了两次失败的婚姻，带着三个孩子。这么看上去，堪称是...");
		quanzi.setTouxiang(R.drawable.datouxiang1);
		quanzi.setXiaotouxiang(R.drawable.xiaotouxiang2);
		quanzi.setNicheng("Bacio");
		quanzi.setHuifu("48回复");
		list.add(quanzi);

		quanzi = new QuanZiListview();
		quanzi.setName("美食厨房");
		quanzi.setTitle("【为爱下厨】我的东北大拉皮");
		quanzi.setNeirong("夏天到了，天气炎热，都不想下厨房炒菜了，都想吃点凉的，来点凉拌菜，东北的大拉皮...");
		quanzi.setTouxiang(R.drawable.datouxiang2);
		quanzi.setXiaotouxiang(R.drawable.xiaotouxiang3);
		quanzi.setNicheng("糖果味");
		quanzi.setHuifu("82回复");
		list.add(quanzi);
		
		quanzi = new QuanZiListview();
		quanzi.setName("娱乐休闲");
		quanzi.setTitle("等咱们老师有钱了，你会做点啥");
		quanzi.setNeirong("等咱老师有了钱，悬赏100万通缉那个说“没有教不会的学生，只有不会教的老师”的家伙，让他来教教70个参差不齐的学生，看他能不能把他们都培养成社会主义四有新人!");
		quanzi.setTouxiang(R.drawable.datouxiang4);
		quanzi.setXiaotouxiang(R.drawable.xiaotouxiang4);
		quanzi.setNicheng("小新");
		quanzi.setHuifu("110回复");
		list.add(quanzi);

		quanzi = new QuanZiListview();
		quanzi.setName("美妆小编推荐");
		quanzi.setTitle("【太赞了！祛斑+美白7小技巧，再...");
		quanzi.setNeirong("夏天到了，天气炎热，都不想下厨房炒菜了，都想吃点凉的，来点凉拌菜，东北的大拉皮...");
		quanzi.setTouxiang(R.drawable.datouxiang3);
		quanzi.setXiaotouxiang(R.drawable.xiaotouxiang1);
		quanzi.setNicheng("糖果味");
		quanzi.setHuifu("60回复");
		list.add(quanzi);
	}
}
