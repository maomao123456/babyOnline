package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.baseadapter.QuanZiAdapter;
import com.example.lei.QuanZiListview;
import com.example.pregnantbabyonline.R;

public class QuanZiFragment extends Fragment {
	View view;
	QuanZiAdapter adapter;
	QuanZiListview quanzi;
	List<QuanZiListview> list;
	ListView listview;
	ViewFlipper viewFlipper;
	RadioGroup rdgroup;
	RadioButton bt1;
	RadioButton bt2;
	RadioButton bt3;

	int[] id = { R.id.quanzi_listview_name, R.id.quanzi_listview_title,
			R.id.quanzi_listview_neirong, R.id.qunazi_listView_touxiang,
			R.id.quanzi_listview_xioatouxiang, R.id.quanzi_listview_nicheng,
			R.id.quanzi_listview_huifu };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = (View) inflater.inflate(R.layout.fragment_quanzi, null);
		initView();
		viewFlipper.startFlipping();
		senMsg();
		getlist();
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
		viewFlipper = (ViewFlipper) view.findViewById(R.id.quanzi_viewFlipper);
		viewFlipper.setOnTouchListener(l);
		
		rdgroup = (RadioGroup) view.findViewById(R.id.quanzi_radioGroup);
		bt1 = (RadioButton) view.findViewById(R.id.quanzi_radioButton1);
		bt2 = (RadioButton) view.findViewById(R.id.quanzi_radioButton2);
		bt3 = (RadioButton) view.findViewById(R.id.quanzi_radioButton3);
		rdgroup.setOnCheckedChangeListener(checkedChangeListener);
		listview.setOnItemClickListener(onItemClickListener);
	}
	OnTouchListener l=new OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.quanzi_img1:
				Toast.makeText(getActivity(), "你所在位置为"+"view的id为"+view.getId(),
						Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
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
			case 0:
				Toast.makeText(getActivity(), "你所在位置为"+position+" AdapterView"+parent+
						"  view的id为"+view.getId()+"  id"+id,
						Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Toast.makeText(getActivity(), "你所在位置为"+position+"view的id为"+view.getId(),
						Toast.LENGTH_SHORT).show();
				break;
			case 2:
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
		quanzi.setXiaotouxiang(R.drawable.quanzi_xiaotouxiang);
		quanzi.setNicheng("Bacio");
		quanzi.setHuifu("48回复");
		list.add(quanzi);

		quanzi = new QuanZiListview();
		quanzi.setName("美食厨房");
		quanzi.setTitle("【为爱下厨】我的东北大拉皮");
		quanzi.setNeirong("夏天到了，天气炎热，都不想下厨房炒菜了，都想吃点凉的，来点凉拌菜，东北的大拉皮...");
		quanzi.setTouxiang(R.drawable.datouxiang2);
		quanzi.setXiaotouxiang(R.drawable.quanzi_xiaotouxiang);
		quanzi.setNicheng("糖果味");
		quanzi.setHuifu("82回复");
		list.add(quanzi);

		quanzi = new QuanZiListview();
		quanzi.setName("美妆小编推荐");
		quanzi.setTitle("【太赞了！祛斑+美白7小技巧，再...");
		quanzi.setNeirong("夏天到了，天气炎热，都不想下厨房炒菜了，都想吃点凉的，来点凉拌菜，东北的大拉皮...");
		quanzi.setTouxiang(R.drawable.datouxiang3);
		quanzi.setXiaotouxiang(R.drawable.guanggao1);
		quanzi.setNicheng("糖果味");
		quanzi.setHuifu("82回复");
		list.add(quanzi);
	}
}
