package com.example.mysample_interfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentB extends Fragment{
	
	private TextView textView;
	private String data;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_b, container, false);
		
		if(savedInstanceState == null)
		{
			
		}
		else
		{
			data = savedInstanceState.getString("text");
			TextView mytextView = (TextView) rootView.findViewById(R.id.textView1);
			mytextView.setText(data);
		}
		
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		textView = (TextView) getActivity().findViewById(R.id.textView1);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putString("text", data);
	}
	
	public void changeText(String data){
		this.data = data;
		textView.setText(data);
	}
}
