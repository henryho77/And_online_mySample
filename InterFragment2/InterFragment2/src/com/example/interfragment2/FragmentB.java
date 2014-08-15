package com.example.interfragment2;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
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
		
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		textView1 = (TextView) getActivity().findViewById(R.id.textView1);
	}
	
	public void changeData(int i){
		
		Resources res = getResources();
		String[] descriptions = res.getStringArray(R.array.descriptions);
		textView1.setText(descriptions[i]);
	}
}
