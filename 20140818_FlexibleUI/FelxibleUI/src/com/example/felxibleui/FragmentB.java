package com.example.felxibleui;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentB extends Fragment{
	
	private TextView textView1;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_b, container, false);
		textView1 = (TextView) rootView.findViewById(R.id.textView1);
		
		return rootView;
	}
	
	public void changeData(int index){
        String[] descriptions=getResources().getStringArray(R.array.descriptions);
        Log.d("debug","Descriptions retreieved");
        textView1.setText(descriptions[index]);
	}
}
