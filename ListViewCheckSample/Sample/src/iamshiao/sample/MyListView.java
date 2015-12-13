package iamshiao.sample;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MyListView extends ListActivity {
	
	//預先定義順序常數
	protected static final int MyListView_camera = 0, MyListView_album = 1, MyListView_map = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
        //設定此Activity使用的res layout
        setContentView(R.layout.list);
        
        //設定ListView未取得內容時顯示的view, empty建構在list.xml中。
        getListView().setEmptyView(findViewById(R.id.empty));
        
        //自訂方法載入ListView值
        fillData();
    }
	
	//當ListView的項目被按下
	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
		
		//由觸發的View物件v(即按下的那一列)取得adapter的checkbox
		CheckBox cbx = (CheckBox)v.findViewById(R.id.MyAdapter_CheckBox_checkBox);
		//取得adapter的textview
		TextView title = (TextView)v.findViewById(R.id.MyAdapter_TextView_title);
		
		if(cbx.isChecked()){
			//設定可見checkbox的狀態
			cbx.setChecked(false);
			Toast.makeText(this, title.getText().toString() + "已取消核取!", Toast.LENGTH_SHORT).show();
		}else{
			cbx.setChecked(true);
			Toast.makeText(this, title.getText().toString() + "已核取!", Toast.LENGTH_SHORT).show();
		}
		super.onListItemClick(l, v, position, id);
    }
	
	void fillData(){
		//從res string.xml讀出預先寫好的字串陣列
		CharSequence[] list = getResources().getStringArray(R.array.list);
		setListAdapter(new MyAdapter(this, list));
	}
}