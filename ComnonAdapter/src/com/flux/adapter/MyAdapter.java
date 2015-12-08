package com.flux.adapter;

import java.util.List;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.flux.bean.CommonAdapter;
import com.flux.bean.LostInfo;
import com.flux.bean.ViewHolder;
import com.flux.comnonadapter.R;

public class MyAdapter extends CommonAdapter<LostInfo> {

	public MyAdapter(List<LostInfo> beans, Context context, int itemLayoutResId) {
		super(beans, context, itemLayoutResId);
	}

	
	@Override
	protected void convert(ViewHolder viewHolder, final LostInfo item) {
		CheckBox checkBox = viewHolder.getView(R.id.cb);
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
				if(isChecked){
					item.setType(LostInfo.TYPE_CHECKED);
				}else{
					item.setType(LostInfo.TYPE_NOCHECKED);
				}
			}
			
		});
		viewHolder.setText(R.id.tv_title, item.getTitle())
		.setText(R.id.tv_desc, item.getDesc())
		.setText(R.id.tv_time, item.getTime())
		.setText(R.id.tv_phone, item.getPhone());
		if(item.getType()==LostInfo.TYPE_CHECKED){
			checkBox.setChecked(true);
		}else{
			checkBox.setChecked(false);
		}
	}
}
