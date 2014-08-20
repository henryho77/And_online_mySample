package com.example.scrolltabs;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends FragmentActivity{

	private ViewPager viewPager = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        viewPager = (ViewPager) findViewById(R.id.pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyAdapter(fragmentManager));
    }

}

class MyAdapter extends FragmentPagerAdapter
{

	public MyAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Fragment getItem(int i) {
		// TODO Auto-generated method stub
		Fragment fragment = null;
//		Log.d("debug", "get Item is called " + i);
		if(i == 0)
		{
			fragment = new FragmentA();
		}
		if(i == 1)
		{
			fragment = new FragmentB();
		}
		if(i == 2)
		{
			fragment = new FragmentC();
		}
		return fragment;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
//		Log.d("debug", "get Count is called");
		return 3;
	}
}