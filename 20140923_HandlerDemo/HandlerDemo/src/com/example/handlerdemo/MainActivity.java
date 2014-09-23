package com.example.handlerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;


public class MainActivity extends Activity {

	Thread thread;
	Handler handler;
	ProgressBar progressBar;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* here is run in main thread */
        setContentView(R.layout.activity_main);
        
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        thread = new Thread(new MyThread());
        thread.start();
        
        //the handler object has been created inside the MainActivity
        handler = new Handler(){
        	@Override
        	public void handleMessage(Message msg) {
        		//super.handleMessage(msg);
        		progressBar.setProgress(msg.arg1);
        	}
        };
    }

    class MyThread implements Runnable{

		@Override
		public void run() {
			/* here is run in second thread */
			for(int i=0; i<100; i++){
				
				Message message = Message.obtain();
				message.arg1 = i;//1,2,...,100
				handler.sendMessage(message);
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
    }
}
