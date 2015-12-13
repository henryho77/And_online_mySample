package com.example.listviewchecktest.adapter;

import java.util.List;

import com.example.listviewchecktest.R;
import com.example.listviewchecktest.model.BookInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdpater extends BaseAdapter{
	
	Context mContext;
	List<BookInfo> listGroupItem;
	
	public MyAdpater(Context mContext, List<BookInfo> listGroupItem) {
		this.mContext = mContext;
		this.listGroupItem = listGroupItem;
	}
	
	@Override
	public int getCount() {
		return listGroupItem.size();
	}

	@Override
	public Object getItem(int position) {
		return listGroupItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewTag mViewTag;
		
		if (convertView == null) {
			//取得listItem容器 view
			convertView = LayoutInflater.from(mContext).inflate(R.layout.myadapter, null);
			
			//建構listItem內容view
			mViewTag = new ViewTag(
					(TextView) convertView.findViewById(R.id.txtBook),
					(TextView) convertView.findViewById(R.id.txtPrice),
					(TextView) convertView.findViewById(R.id.txtDate),
					(ImageView) convertView.findViewById(R.id.imCheckPicture)
					);
			
			convertView.setTag(mViewTag);
			
		} else {
			mViewTag = (ViewTag) convertView.getTag();
		}
		
		//依照每次進來的position不同,而取得listGroupItem不同的listItem之內容,將之設給mViewTag做顯示
		mViewTag.txtTitle.setText(listGroupItem.get(position).getTitle());
		mViewTag.txtPrice.setText(listGroupItem.get(position).getPrice());
		mViewTag.txtDate.setText(listGroupItem.get(position).getDate());
		
		return convertView;
	}

	//自訂類別,用來維護清單中每一列listItem的view物件集合
	class ViewTag {
		TextView txtTitle;
		TextView txtPrice;
		TextView txtDate;
		ImageView imCheckPicture;
		
		public ViewTag(TextView txtTitle, TextView txtPrice, TextView txtDate, ImageView imCheckPicture) {
			this.txtTitle = txtTitle;
			this.txtPrice = txtPrice;
			this.txtDate = txtDate;
			this.imCheckPicture = imCheckPicture;
		}
	}
}
