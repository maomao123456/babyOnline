package com.example.baseadapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.sax.StartElementListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lei.CircularImage;
import com.example.lei.QuanZiListview;
import com.example.pregnantbabyonline.QuanZiChildActivity;
import com.example.pregnantbabyonline.R;
import com.example.pregnantbabyonline.R.drawable;

public class QuanZiAdapter extends BaseAdapter {

	LayoutInflater inflater;

	Context context;
	List<QuanZiListview> list;
	int item;
	int[] id;
	View windView;
	int numb;//判断用户是否关注的数据

	public QuanZiAdapter() {
		// super();
	}

	public QuanZiAdapter(Context context, List<QuanZiListview> list, int item,
			int[] id) {
		super();
		this.context = context;
		this.list = list;
		this.item = item;
		this.id = id;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	PopupWindow popupWindow;
	public void createPopupWindow(){
		//初始化一个popupwindow的对象并给予长宽
		popupWindow=new PopupWindow(windView,LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,true);
		//设置popupwindow的背景 不舍背景无法监听（背景为全透明）
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		//是否可点击窗口外的布局
		popupWindow.setOutsideTouchable(true);
		popupWindow.setTouchable(true);
		//设置是否可以点击
		popupWindow.setTouchInterceptor(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				return false;
			}
		});
		//2展示出来 点buttonshow出来
		popupWindow.showAtLocation(windView, Gravity.FILL, 0, 0);
		TextView popWinBg=(TextView)windView.findViewById(R.id.quanzi_popupw_bg);
		TextView tiaozhuan=(TextView)windView.findViewById(R.id.quanzi_popuwindow_goxiangqingye);
		popWinBg.setOnClickListener(listener);
		tiaozhuan.setOnClickListener(listener);
	}
	/**
	 * popupWindow内部简单控件的点击事件
	 */
	OnClickListener listener=new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.quanzi_popupw_bg:
				popupWindow.dismiss();
				break;
			case R.id.quanzi_popuwindow_goxiangqingye:
				popupWindow.dismiss();
				Intent intent =new Intent(context, QuanZiChildActivity.class);
				context.startActivity(intent);
				break;

			default:
				break;
			}
		}
	};
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = inflater.inflate(item, null);
		}
		TextView name = (TextView) convertView.findViewById(id[0]);
		TextView title = (TextView) convertView.findViewById(id[1]);
		TextView neirong = (TextView) convertView.findViewById(id[2]);
		ImageView touxiang = (ImageView) convertView.findViewById(id[3]);
		//调用画圆类  进行画圆
		CircularImage xiaotouxiang = (CircularImage) convertView
				.findViewById(id[4]);
		TextView nicheng = (TextView) convertView.findViewById(id[5]);
		TextView huifu = (TextView) convertView.findViewById(id[6]);
		QuanZiListview quanzi = list.get(position);
		name.setText(quanzi.getName());
		title.setText(quanzi.getTitle());
		neirong.setText(quanzi.getNeirong());
		touxiang.setImageResource(quanzi.getTouxiang());
		xiaotouxiang.setImageResource(quanzi.getXiaotouxiang());
		nicheng.setText(quanzi.getNicheng());
		huifu.setText(quanzi.getHuifu());
		
		touxiang.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				windView=inflater.inflate(R.layout.quanzi_popupwindow_datouxiang, null);
				TextView shuoming=(TextView)windView.findViewById(R.id.quanzi_popuwindow_shuoming);
				ImageView datouxiang=(ImageView)windView.findViewById(R.id.quanzi_popuwindow_datouxiang);
				datouxiang.setVisibility(View.VISIBLE);
				TextView tiaozhuan=(TextView)windView.findViewById(R.id.quanzi_popuwindow_goxiangqingye);
				tiaozhuan.setOnClickListener(listener);
				switch (position) {
				case 0:
					numb=1;
					getGuanzhu();
					datouxiang.setImageResource(R.drawable.datouxiang1);
					shuoming.setText(str2+"时尚辣妈：关注她们的生活");
					createPopupWindow();
					tiaozhuan.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							popupWindow.dismiss();
							Intent intent =new Intent(context, QuanZiChildActivity.class);
							intent.putExtra("position", numb);
							context.startActivity(intent);
						}
					});
					break;
				case 1:
					numb=2;
					getGuanzhu();
					datouxiang.setImageResource(R.drawable.datouxiang2);
					shuoming.setText(str2+"美食厨房：美食大比拼");
					createPopupWindow();
					tiaozhuan.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							popupWindow.dismiss();
							Intent intent =new Intent(context, QuanZiChildActivity.class);
							intent.putExtra("position", numb);
							context.startActivity(intent);
						}
					});
					break;
				case 2:
					numb=3;
					getGuanzhu();
					datouxiang.setImageResource(R.drawable.datouxiang3);
					shuoming.setText(str2+"娱乐休闲：期盼你的吐槽");
					createPopupWindow();
					tiaozhuan.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							popupWindow.dismiss();
							Intent intent =new Intent(context, QuanZiChildActivity.class);
							intent.putExtra("position", numb);
							context.startActivity(intent);
						}
					});
					break;
				case 3:
					numb=4;
					getGuanzhu();
					datouxiang.setImageResource(R.drawable.datouxiang4);
					shuoming.setText(str2+"美妆小编推荐：夏日必备");
					createPopupWindow();
					tiaozhuan.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							popupWindow.dismiss();
							Intent intent =new Intent(context, QuanZiChildActivity.class);
							intent.putExtra("position", numb);
							context.startActivity(intent);
						}
					});
					break;

				default:
					break;
				}
			}
		});
		xiaotouxiang.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				windView=inflater.inflate(R.layout.quanzi_popupwindow_datouxiang, null);
				CircularImage xiaotouxiang=(CircularImage)windView.findViewById(R.id.quanzi_popuwindow_xiaotouxiang);
				TextView shuoming=(TextView)windView.findViewById(R.id.quanzi_popuwindow_shuoming);
				xiaotouxiang.setVisibility(View.VISIBLE);
				TextView tiaozhuan=(TextView)windView.findViewById(R.id.quanzi_popuwindow_goxiangqingye);
				tiaozhuan.setOnClickListener(listener);
				switch (position) {
				case 0:
					numb=1;
					getGuanzhu();
					xiaotouxiang.setImageResource(R.drawable.xiaotouxiang1);
					shuoming.setText(str+"“Bacio” ");
					createPopupWindow();
					tiaozhuan.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							popupWindow.dismiss();
							Intent intent =new Intent(context, QuanZiChildActivity.class);
							intent.putExtra("position", numb);
							context.startActivity(intent);
						}
					});
					break;
				case 1:
					numb=2;
					getGuanzhu();
					xiaotouxiang.setImageResource(R.drawable.xiaotouxiang2);
					shuoming.setText(str+"“糖果味” ");
					createPopupWindow();
					tiaozhuan.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							popupWindow.dismiss();
							Intent intent =new Intent(context, QuanZiChildActivity.class);
							intent.putExtra("position", numb);
							context.startActivity(intent);
						}
					});
					break;
				case 2:
					numb=3;
					getGuanzhu();
					xiaotouxiang.setImageResource(R.drawable.xiaotouxiang3);
					shuoming.setText(str+"“小新” ");
					createPopupWindow();
					tiaozhuan.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							popupWindow.dismiss();
							Intent intent =new Intent(context, QuanZiChildActivity.class);
							intent.putExtra("position", numb);
							context.startActivity(intent);
						}
					});
					break;
				case 3:
					numb=4;
					getGuanzhu();
					xiaotouxiang.setImageResource(R.drawable.xiaotouxiang4);
					shuoming.setText(str+"“花之语” ");
					createPopupWindow();
					tiaozhuan.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							popupWindow.dismiss();
							Intent intent =new Intent(context, QuanZiChildActivity.class);
							intent.putExtra("position", numb);
							context.startActivity(intent);
						}
					});
					break;

				default:
					break;
				}
			}
		});
		/*huifu.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				switch (position) {
				case 0:
					Toast.makeText(context, "第一项的回复", Toast.LENGTH_SHORT).show();
					break;
				case 1:
					Toast.makeText(context, "第2项的回复", Toast.LENGTH_SHORT).show();
					break;
				case 2:
					Toast.makeText(context, "第3项的回复", Toast.LENGTH_SHORT).show();
					break;
				case 3:
					Toast.makeText(context, "第4项的回复", Toast.LENGTH_SHORT).show();
					break;

				default:
					break;
				}
			}
		});*/

		return convertView;
	}
	String str;
	String str2;
	/**
	 * 根据圈子内容详情数据 来判断用户是否订阅与关注
	 */
	public void getGuanzhu(){
		SharedPreferences sharedPreferences =context.getSharedPreferences
				("quanziChild", Context.MODE_PRIVATE);
		String guanzhu=sharedPreferences.getString(numb+"关注", "");
		String dingyue=sharedPreferences.getString(numb+"订阅", "");
		if(guanzhu.equals("已关注")){
			str="你已关注 ";
		}else{
			str="详情页右上角即可关注 ";
		}
		if(dingyue.equals("已订阅")){
			str2="你已订阅 ";
		}else{
			str2="详情页右上角即可订阅 ";
		}
		
	}
}
