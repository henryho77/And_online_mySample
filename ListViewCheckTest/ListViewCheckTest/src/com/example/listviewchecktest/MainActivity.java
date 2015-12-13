package com.example.listviewchecktest;

import java.util.ArrayList;
import java.util.List;

import com.example.listviewchecktest.adapter.MyAdpater;
import com.example.listviewchecktest.model.BookInfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;


public class MainActivity extends Activity {

	ListView bookList;
	MyAdpater myAdpater;
	List<BookInfo> listGroupItem; // �إ߲M��
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//Set layout resource id for MainActivity
        
        populateBookData();//Populate Book Data into List       
        initView();        
        setListeners();
    }

	private void populateBookData() {
		
		listGroupItem = new ArrayList<BookInfo>();
		
		//add book data into list
		listGroupItem.add(new BookInfo("BookA","PriceA","2015/12/01"));
		listGroupItem.add(new BookInfo("BookB","PriceB","2015/12/02"));
		listGroupItem.add(new BookInfo("BookC","PriceC","2015/12/03"));
		listGroupItem.add(new BookInfo("BookD","PriceD","2015/12/04"));
	}

	private void initView() {
		bookList = (ListView) findViewById(R.id.bookList);
		
		myAdpater = new MyAdpater(MainActivity.this, listGroupItem);
		bookList.setAdapter(myAdpater);
	}
	
	private void setListeners() {
		bookList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				ImageView imCheckPicture = (ImageView) view.findViewById(R.id.imCheckPicture);
				
				// �p�G�Q�I�����ӦC,��Ϥ������Q�Ŀ�,�N�N�Ϥ������w�Ŀ�;�Ϥ��Y���w�Ŀ�,�N�������Q�Ŀ�Ϥ�
				if (imCheckPicture.getDrawable().getConstantState().equals(
						getResources().getDrawable(R.drawable.icon_select_blank).getConstantState())) {
					
					imCheckPicture.setImageResource(R.drawable.icon_select_checked);				
				} else {
					imCheckPicture.setImageResource(R.drawable.icon_select_blank);
				}
				
			}
		});
		
	}

}
