package com.example.scrolltabs;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentA extends Fragment{

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		Log.d("debug", "FragA onAttach");
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		if(savedInstanceState == null)
		{
			Log.d("debug", "FragA onCreate FIRST TIME");
		}
		else
		{
			Log.d("debug", "FragA onCreate SUBSEQUENT TIME");
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("debug", "FragA onCreateView");
		View rootView = inflater.inflate(R.layout.fragment_a, container, false);
		return rootView;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.d("debug", "FragA onActivityCreated");
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d("debug", "FragA onStart");
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d("debug", "FragA onResume");
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d("debug", "FragA onPause");
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Log.d("debug", "FragA onSaveInstanceState");
	}
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d("debug", "FragA onStop");
	}
	
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.d("debug", "FragA onDestroyView");
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("debug", "FragA onDestroy");
	}
	
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		Log.d("debug", "FragA onDetach");
	}
}
