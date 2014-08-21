package com.example.navigationdrawer;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


public class MainActivity extends Activity implements OnItemClickListener{

	private DrawerLayout drawerLayout;
	private ListView listView;
	private String[] planets;
	private ActionBarDrawerToggle drawerToggle;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);      
        
        planets = getResources().getStringArray(R.array.planets);
        
        listView = (ListView) findViewById(R.id.drawerList);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, planets));
        listView.setOnItemClickListener(this);
        
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, 
        										 R.string.drawer_open, R.string.drawer_close){
        	@Override
        	public void onDrawerClosed(View drawerView) {
        		// TODO Auto-generated method stub
        		super.onDrawerClosed(drawerView);
        		Toast.makeText(MainActivity.this, " Drawer Closed ", Toast.LENGTH_LONG).show();
        	}
        	
        	@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerOpened(drawerView);
				Toast.makeText(MainActivity.this, " Drawer Opened ", Toast.LENGTH_LONG).show();
			}
        };
        drawerLayout.setDrawerListener(drawerToggle);
        
        //ActionBar set home button
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onPostCreate(savedInstanceState);
    	drawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if(drawerToggle.onOptionsItemSelected(item)){
        	return true;
        }
    	
    	int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
    	// TODO Auto-generated method stub
    	super.onConfigurationChanged(newConfig);
    	drawerToggle.onConfigurationChanged(newConfig);
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Toast.makeText(this, planets[position] + " was selected", Toast.LENGTH_LONG).show();
		selectItem(position);
	}

	public void selectItem(int position) {
		// TODO Auto-generated method stub
		listView.setItemChecked(position, true);
		setTitle(planets[position]);
	}
	
	public void setTitle(String title){
		getActionBar().setTitle(title);
	}
}
