package com.example.updateui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

	private TextView textView1;
	private ProgressBar progressBar1;
	private Button button1;
	
	private Handler handler;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        textView1 = (TextView) findViewById(R.id.textView1);
        button1 = (Button) findViewById(R.id.button1);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
    }

    class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    class MyHandler extends Handler {
    	@Override
    	public void handleMessage(Message msg) {
    		// TODO Auto-generated method stub
    		super.handleMessage(msg);
    	}
    }
    
    class MyThread extends Thread {
    	@Override
    	public void run() {
    		// TODO Auto-generated method stub
    		super.run();
    	}
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
}
