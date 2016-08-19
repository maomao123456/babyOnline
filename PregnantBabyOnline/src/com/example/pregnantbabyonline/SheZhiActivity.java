package com.example.pregnantbabyonline;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class SheZhiActivity extends Activity{
	ImageView back;
	RadioGroup radiogroup;
	RadioButton beiyun_radiobutton;
	RadioButton huaiyun_radiobutton;
	RadioButton born_radiobutton;
	TextView aboutUs;
	TextView tuichu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shezhi);
		initView();
	}
	public void initView(){//找id
		back=(ImageView)findViewById(R.id.imageview_back_shezhi);
		radiogroup=(RadioGroup)findViewById(R.id.radiogroup_shezhi);
		beiyun_radiobutton=(RadioButton)findViewById(R.id.radiobutton_beiyun);
		huaiyun_radiobutton=(RadioButton)findViewById(R.id.radiobutton_huaiyun);
		born_radiobutton=(RadioButton)findViewById(R.id.radiobutton_born);
		aboutUs=(TextView)findViewById(R.id.aboutus_shezhi);
		tuichu=(TextView)findViewById(R.id.tuichu_shezhi);
		radiogroup.setOnCheckedChangeListener(checkedChangeListener);
		back.setOnClickListener(clickListener);
		aboutUs.setOnClickListener(clickListener);
		tuichu.setOnClickListener(clickListener);
	}
	OnCheckedChangeListener checkedChangeListener=new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch(checkedId){
			case R.id.radiobutton_beiyun:
				beiyun_radiobutton.setTextColor(getResources().getColor(R.color.green));
				huaiyun_radiobutton.setTextColor(getResources().getColor(R.color.blacka));
				born_radiobutton.setTextColor(getResources().getColor(R.color.blacka));
				break;
			case R.id.radiobutton_huaiyun:
				beiyun_radiobutton.setTextColor(getResources().getColor(R.color.blacka));
				huaiyun_radiobutton.setTextColor(getResources().getColor(R.color.green));
				born_radiobutton.setTextColor(getResources().getColor(R.color.blacka));
				break;
			case R.id.radiobutton_born:
				beiyun_radiobutton.setTextColor(getResources().getColor(R.color.blacka));
				huaiyun_radiobutton.setTextColor(getResources().getColor(R.color.blacka));
				born_radiobutton.setTextColor(getResources().getColor(R.color.green));
				break;
			}
		}
	};
	OnClickListener clickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.imageview_back_shezhi:
				startActivity(new Intent(SheZhiActivity.this,ShouYeActivity.class));
				SheZhiActivity.this.finish();
				break;
			case R.id.aboutus_shezhi:
				startActivity(new Intent(SheZhiActivity.this,AboutUsAcitivity.class));
				SheZhiActivity.this.finish();
				break;
			case R.id.tuichu_shezhi:
				startActivity(new Intent(SheZhiActivity.this,LoginActivity.class));
				SheZhiActivity.this.finish();
				break;
			}
		}
	};
}
