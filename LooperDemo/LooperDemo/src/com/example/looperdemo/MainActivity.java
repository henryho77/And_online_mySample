package com.example.looperdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;


public class MainActivity extends ActionBarActivity {

	private RatingBar ratingBar1;
	private Button button1;
	private Handler handler;

	private float fRatingValue;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ratingBar1 = (RatingBar) findViewById(R.id.ratingBar1);
        button1 = (Button) findViewById(R.id.button1);
        
        ratingBar1.setOnRatingBarChangeListener(new RatingBarListener());
        button1.setOnClickListener(new ButtonListener());
        
        System.out.println("onCreate()-->" + Thread.currentThread().getName()); 
        Thread wt = new WorkerThread();
        wt.start();
        
    }

    class RatingBarListener implements OnRatingBarChangeListener {
    	@Override
    	public void onRatingChanged(RatingBar ratingBar, float rating,
    			boolean fromUser) {
    		System.out.println("onRatingChanged()-->" + Thread.currentThread().getName()); 
    		fRatingValue = rating;
    		System.out.println("fRatingValue-->" + fRatingValue); 
    	}
    }
    
    class ButtonListener implements OnClickListener {
    	@Override
    	public void onClick(View v) {
    		System.out.println("onClick()-->" + Thread.currentThread().getName()); 
    		
    		Message msg = handler.obtainMessage();
    		msg.obj = fRatingValue;
    		
    		/* 注意這個handler是在WorkerThread生成的,
    		 * 所以當這個handler現在在MainThread中調用sendMessage()方法時,
    		 * 會將該消息對象發送到WorkerThread中的Looper所掌管的消息隊列裡面 */
    		//handler.sendMessage(msg);
    		
    		/* 上面的handler.sendMessage(msg)可以改寫成msg.sendToTarget()
    		 * 兩種方法是一樣的動作,去看原始碼你就會發現msg.sendToTarget()
    		 * public void sendToTarget() {
        	 *	  target.sendMessage(this);
    		 * }
    		 * 其中的target指的就是這個Message所屬的handler對象
    		 * this就是Message */
    		msg.sendToTarget();
    	}
    }
    
    class WorkerThread extends Thread {
    	@Override
    	public void run() {
    		
    		//準備Looper對象
    		Looper.prepare();
    		
    		//在WorkerThread當中生成一個Handler對象
    		handler = new Handler() {
    			@Override
    			public void handleMessage(Message msg) {
    				super.handleMessage(msg);
    				
    				System.out.println("handleMessage()-->" + Thread.currentThread().getName()); 
    				System.out.println("收到消息對象"); 
    				
    				Float fReceiveRatingVaule = (Float) msg.obj;		
    				System.out.println("fReceiveRatingVaule-->" + fReceiveRatingVaule); 
    			}
    		};
    		
    		/* 調用Looper的loop()方法之後,Looper對象將不斷的從消息隊列當中取出消息對象,
    		 * 然後調用handler的handleMessage()方法,處理該消息對象,
    		 * 如果消息隊列當中沒有對象,則該線程阻塞 */
    		Looper.loop();
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
