package com.example.handlerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

	Thread thread;
	Handler handler;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* here is run in main thread */
        setContentView(R.layout.activity_main);
        
        thread = new Thread(new MyThread());
        thread.start();
        
    }

    class MyThread implements Runnable{

		@Override
		public void run() {
			/* here is run in second thread */
			for(int i = 0; i < 10000;i++){
				
			}
		}	
    }
}
