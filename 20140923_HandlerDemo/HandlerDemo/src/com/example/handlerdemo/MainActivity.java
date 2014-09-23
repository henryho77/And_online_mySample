package com.example.handlerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
        
        //the handler object has been created inside the MainActivity
        handler = new Handler(){
        	@Override
        	public void handleMessage(Message msg) {
        		// TODO Auto-generated method stub
        		super.handleMessage(msg);
        	}
        };
    }

    class MyThread implements Runnable{

		@Override
		public void run() {
			/* here is run in second thread */

			Message message = Message.obtain();
			for(int i = 0; i < 10000;i++){
				handler.sendMessage(message);
			}
		}	
    }
}
