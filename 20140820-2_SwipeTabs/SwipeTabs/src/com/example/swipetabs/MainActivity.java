package com.example.swipetabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
//import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends FragmentActivity implements TabListener{

	ViewPager viewPager;
	ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				
				actionBar.setSelectedNavigationItem(arg0);
//				Log.d("debug", "onPageSelected at " + " position " + arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
//				Log.d("debug", "onPageScrolled at " + " position " + arg0 + " from " + arg1 + " with number of pixels= " + arg2);
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
//				if(arg0 == ViewPager.SCROLL_STATE_IDLE)
//				{
//					Log.d("debug", "onPageScrollStateChanged Idle");
//				}
//				if(arg0 == ViewPager.SCROLL_STATE_DRAGGING)
//				{
//					Log.d("debug", "onPageScrollStateChanged Dragging");
//				}
//				if(arg0 == ViewPager.SCROLL_STATE_SETTLING)
//				{
//					Log.d("debug", "onPageScrollStateChanged Settling");
//				}
			}
		});
		
		
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		ActionBar.Tab tab1 = actionBar.newTab();
		tab1.setText("Tab 1");
		tab1.setTabListener(this);
		
		ActionBar.Tab tab2 = actionBar.newTab();
		tab2.setText("Tab 2");
		tab2.setTabListener(this);
		
		ActionBar.Tab tab3 = actionBar.newTab();
		tab3.setText("Tab 3");
		tab3.setTabListener(this);
		
		actionBar.addTab(tab1);
		actionBar.addTab(tab2);
		actionBar.addTab(tab3);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
//		Log.d("debug", "onTabSelected at " + " position " + tab.getPosition() + " name " + tab.getText());
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
//		Log.d("debug", "onTabUnselected at " + " position " + tab.getPosition() + " name " + tab.getText());
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
//		Log.d("debug", "onTabReselected at " + " position " + tab.getPosition() + " name " + tab.getText());
	}

}

class MyAdapter extends FragmentPagerAdapter
{
	
	public MyAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		Fragment fragment = null;
		if(arg0 == 0)
		{
			fragment = new FragmentA();
		}
		if(arg0 == 1)
		{
			fragment = new FragmentB();
		}
		if(arg0 == 2)
		{
			fragment = new FragmentC();
		}
		
		return fragment;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}
}
