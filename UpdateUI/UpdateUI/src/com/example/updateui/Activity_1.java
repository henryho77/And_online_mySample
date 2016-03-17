package com.example.updateui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/* 剛剛MainActivity示範的是使用handler.sendMessage(Message)與handleMessage(Message)實現更新UI的方式,
 * 現在Activity_1示範的是改用handler.post(Runnable)實現更新UI的方式 */

public class Activity_1 extends ActionBarActivity{

	private static final int COUNT_OK = 100;
	
	private TextView textView1;
	private ProgressBar progressBar1;
	private Button button1;
	
	private Handler handler = new Handler();
	Message receiveMsg;
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_1);
		
		System.out.println("Activity_1 onCreate() --> " + Thread.currentThread().getName());
		
		textView1 = (TextView) findViewById(R.id.textView1);
        button1 = (Button) findViewById(R.id.button1);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        
        button1.setOnClickListener(new ButtonListener());
	}
	
	class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// 當用戶點擊按鈕時,我們開啟一個WorkerThread在背後執行計數的動作
						Thread t = new MyThread();
						t.start();
						
						button1.setClickable(false);
						button1.setTextColor(Color.GRAY);
		}
    }
	
	class MyThread extends Thread {
    	@Override
    	public void run() {
    		super.run();
    		
    		System.out.println("Activity_1 MyThread run() --> " + Thread.currentThread().getName());
    		
    		//這個Runnable r的run()方法不會在這裡被執行,直到handler.post(r)的時候才會進到這裡的run()方法執行,所以我們將要更新UI的程式碼寫在這裡面
    		Runnable r = new Runnable() {
				@Override
				public void run() {
					System.out.println("Activity_1 Runnable run() --> " + Thread.currentThread().getName());
					
					int count = receiveMsg.arg1;
		    		String status = (String) receiveMsg.obj;
		    		
		    		progressBar1.setProgress(count);
		    		progressBar1.setSecondaryProgress(count + 20);
		    		textView1.setText(status);
		    		
		    		if (receiveMsg != null) {
		    			switch(receiveMsg.what) {
		    				case COUNT_OK:
		    					button1.setClickable(true);
		    					button1.setTextColor(Color.BLACK);
		    					break;
		    				default:
		    					break;
		    			}
		    		}
				}
			};
    		
    		
    		String str ;
    		for (int i = 1; i < 101; i++) {
    			try {
					Thread.sleep(20);
					
					Message msg = handler.obtainMessage();
					msg.arg1 = i;
					
					if (i < 100) {
						//前面N輪
						str = "計數中..." + i + "%";
					} else {
						//最後一輪
						str = "完成!";
						msg.what = COUNT_OK;
					}
					msg.obj = str;
					
					receiveMsg = msg;
					handler.post(r);
					
					/* handler.post(r)方法將r對象放置到消息隊列當中,但是如何把一個Runnable對象放置在消息隊列當中?
					 * 實際上是生成了一個Message對象,並將r賦值給Message對象的callback屬性,然後再將Message對象
					 * 放置到消息隊列當中,接著Looper對象(Main Thread)從消息隊列取出攜帶有r對象的Message對象之後,
					 * 調用了dispatchMessage方法,然後判斷Message的callback屬性是否為空,此時的callback屬性有值,
					 * 所以執行了handleCallback(Message msg),在該方法當中執行了msg.callback.run(),
					 * 而msg.callback就是你夾帶的r對象,所以handler.post(r)實際上是執行了r.run(),
					 * 而這個handler又因為是在MainThread生成對象的,所以r.run()就是在MainThread上面執行(而不是在MyThread執行),
					 * 由於IOS有提供一種將整段程式碼當作參數傳到另一端去做執行的這種寫法,而Android沒有這種寫法,
					 * 所以Android提供了handler.post(Runnable)這樣的一種方式來變相的實現你想將整段程式碼丟到MainThread執行
					 * 來更新UI這樣的一種動作 */
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    	}
    }
	
	
}
