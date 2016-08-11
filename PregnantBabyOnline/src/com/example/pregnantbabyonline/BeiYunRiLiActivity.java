package com.example.pregnantbabyonline;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BeiYunRiLiActivity extends Activity{
	ImageView back;
	ImageView rightData;
	ImageView leftData;
	TextView xiugaijingqi;
	CheckBox yimaXuanze;
	CheckBox yesuan;
	CheckBox tongfang;
	CheckBox wendu;
	CheckBox pailuanshizhi;
	CheckBox baidai;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beiyunrili);
		initView();
		
	}
	 // 重写onActivityResult()方法处理返回的日期
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK) {
            int year = data.getIntExtra("year", 0);   // 得到年
            int month = data.getIntExtra("month", 0); // 得到月
            int day = data.getIntExtra("day", 0);     // 得到天

            // 格式化日期显示
            final Calendar dat = Calendar.getInstance();
            dat.set(Calendar.YEAR, year);
            dat.set(Calendar.MONTH, month);
            dat.set(Calendar.DAY_OF_MONTH, day);
            
            // 显示日期结果
            SimpleDateFormat format = new SimpleDateFormat("yyyy MMM dd");
            Toast.makeText(BeiYunRiLiActivity.this, format.format(dat.getTime()), Toast.LENGTH_LONG).show();     
        }
    }
    
    
    
	/**
	 * 通过ID找控件
	 */
	public void initView(){
		back=(ImageView)findViewById(R.id.rili_back);
		rightData=(ImageView)findViewById(R.id.rili_data_right);
		leftData=(ImageView)findViewById(R.id.rili_data_left);
		xiugaijingqi=(TextView)findViewById(R.id.rili_xiugaijingqi);
		yimaXuanze=(CheckBox)findViewById(R.id.rili_yima_xuanze);
		yesuan=(CheckBox)findViewById(R.id.rili_yesuan);
		tongfang=(CheckBox)findViewById(R.id.rili_tongfang);
		wendu=(CheckBox)findViewById(R.id.rili_wendu);
		pailuanshizhi=(CheckBox)findViewById(R.id.rili_pailuanshizhi);
		baidai=(CheckBox)findViewById(R.id.rili_baidai);
		back.setOnClickListener(listener);
		leftData.setOnClickListener(listener);
		rightData.setOnClickListener(listener);
		xiugaijingqi.setOnClickListener(listener);
		yimaXuanze.setOnClickListener(listener);
		yesuan.setOnClickListener(listener);
		tongfang.setOnClickListener(listener);
		wendu.setOnClickListener(listener);
		pailuanshizhi.setOnClickListener(listener);
		baidai.setOnClickListener(listener);
	}
	OnClickListener listener=new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.rili_back:
				Intent intent=new Intent(BeiYunRiLiActivity.this,
						ShouyeActivity.class);
				startActivity(intent);
				break;
			case R.id.rili_wendu:
				Toast.makeText(getApplicationContext(), "跳转到温度界面", Toast.LENGTH_SHORT).show();
				/*intent=new Intent(BeiYunRiLiActivity.this, "");
				startActivity(intent);*/
				break;

			default:
				break;
			}
		}
	};

}
