package iamshiao.sample;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MyListView extends ListActivity {
	
	//�w���w�q���Ǳ`��
	protected static final int MyListView_camera = 0, MyListView_album = 1, MyListView_map = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
        //�]�w��Activity�ϥΪ�res layout
        setContentView(R.layout.list);
        
        //�]�wListView�����o���e����ܪ�view, empty�غc�blist.xml���C
        getListView().setEmptyView(findViewById(R.id.empty));
        
        //�ۭq��k���JListView��
        fillData();
    }
	
	//��ListView�����سQ���U
	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
		
		//��Ĳ�o��View����v(�Y���U�����@�C)���oadapter��checkbox
		CheckBox cbx = (CheckBox)v.findViewById(R.id.MyAdapter_CheckBox_checkBox);
		//���oadapter��textview
		TextView title = (TextView)v.findViewById(R.id.MyAdapter_TextView_title);
		
		if(cbx.isChecked()){
			//�]�w�i��checkbox�����A
			cbx.setChecked(false);
			Toast.makeText(this, title.getText().toString() + "�w�����֨�!", Toast.LENGTH_SHORT).show();
		}else{
			cbx.setChecked(true);
			Toast.makeText(this, title.getText().toString() + "�w�֨�!", Toast.LENGTH_SHORT).show();
		}
		super.onListItemClick(l, v, position, id);
    }
	
	void fillData(){
		//�qres string.xmlŪ�X�w���g�n���r��}�C
		CharSequence[] list = getResources().getStringArray(R.array.list);
		setListAdapter(new MyAdapter(this, list));
	}
}