package iamshiao.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{	
	
	private LayoutInflater myInflater;
	CharSequence[] list = null;
   
	public MyAdapter(Context ctxt, CharSequence[] list){
		myInflater = LayoutInflater.from(ctxt);
		this.list = list;
	}
	
	@Override
	public int getCount()
	{
		return list.length;
	}

	@Override
	public Object getItem(int position)
	{
		return list[position];
	}
  
	@Override
	public long getItemId(int position)
	{
		return position;
	}
  
	@Override
	public View getView(int position,View convertView,ViewGroup parent)
	{
		//自訂類別，表達個別listItem中的view物件集合。
		ViewTag viewTag;
		
		if(convertView == null){
			//取得listItem容器 view
			convertView = myInflater.inflate(R.layout.adapter, null);
			
			//建構listItem內容view
			viewTag = new ViewTag(
					(ImageView)convertView.findViewById(R.id.MyAdapter_ImageView_icon),
					(TextView) convertView.findViewById(R.id.MyAdapter_TextView_title),
					(CheckBox) convertView.findViewById(R.id.MyAdapter_CheckBox_checkBox)
					);
			
			//設置容器內容
			convertView.setTag(viewTag);
		}
		else{
			viewTag = (ViewTag) convertView.getTag();
		}
		
		//設定內容圖案
		switch(position){
			case MyListView.MyListView_camera:
				viewTag.icon.setBackgroundResource(R.drawable.ic_launcher_camera);
				break;
			case MyListView.MyListView_album:
				viewTag.icon.setBackgroundResource(R.drawable.ic_launcher_gallery);
				break;
			case MyListView.MyListView_map:
				viewTag.icon.setBackgroundResource(R.drawable.ic_launcher_maps);
				break;
		}
		//設定內容文字
		viewTag.title.setText(list[position]);
		
		return convertView;
	}
	
	//自訂類別，表達個別listItem中的view物件集合。
	class ViewTag{
		ImageView icon;
		TextView title;
		CheckBox cbx;
    
		public ViewTag(ImageView icon, TextView title, CheckBox cbx){
			this.icon = icon;
			this.title = title;
			this.cbx = cbx;
		}
	}
}
