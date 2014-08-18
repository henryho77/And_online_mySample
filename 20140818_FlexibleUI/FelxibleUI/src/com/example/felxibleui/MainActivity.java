package com.example.felxibleui;

import android.support.v7.app.ActionBarActivity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Choreographer.FrameCallback;


public class MainActivity extends ActionBarActivity implements FragmentA.Communicator{

//	FragmentA f1;
//	FragmentB f2;
	FragmentManager fm;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        fm = getFragmentManager();
        FragmentA f1 = (FragmentA) fm.findFragmentById(R.id.fragment1);
        f1.setCommunicator(this);
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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void respond(int index) {
		// TODO Auto-generated method stub
        Log.d("debug", " Inside main activity ");
        FragmentB f2= (FragmentB) fm.findFragmentById(R.id.fragment2);
        if(f2!=null && f2.isVisible())
        {
            Log.d("debug", " f2 change data ");
            f2.changeData(index);
        }
        else
        {
            Log.d("debug", " calling intent ");
            Intent intent=new Intent(this, AnotherActivity.class);
            intent.putExtra("index",index);
            startActivity(intent);
        }
	}
}
