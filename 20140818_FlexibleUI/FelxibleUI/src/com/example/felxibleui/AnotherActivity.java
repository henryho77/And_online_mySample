package com.example.felxibleui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class AnotherActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_another);
		
        Intent intent=getIntent();
        Log.d("debug", " Inside another activity ");
        int index=intent.getIntExtra("index",0);
        FragmentB f2= (FragmentB) getFragmentManager().findFragmentById(R.id.fragment2);
        Log.d("debug", "  "+(f2==null));
        if(f2 != null)
        {
        	f2.changeData(index);
        }
	}
}
