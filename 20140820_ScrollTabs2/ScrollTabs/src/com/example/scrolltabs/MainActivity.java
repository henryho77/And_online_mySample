package com.example.scrolltabs;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
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

class MyAdapter extends FragmentStatePagerAdapter
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
	
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		if(position == 0)
		{
			return "Tab 1";
		}
		if(position == 1)
		{
			return "Tab 2";
		}
		if(position == 2)
		{
			return "Tab 3";
		}
		return null;
	}
}