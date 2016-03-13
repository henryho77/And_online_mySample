package com.example.updateui;

import android.support.v7.app.ActionBarActivity;
import android.content.res.ColorStateList;
import android.graphics.Color;
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

	private static final int COUNT_OK = 100;
	
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
        
        handler = new MyHandler();//MainThread中宣告的handler
        
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
    
    class MyHandler extends Handler {
    	@Override
    	public void handleMessage(Message msg) {
    		// TODO Auto-generated method stub
    		super.handleMessage(msg);
    		// 我們在handleMessage方法中,處理更新UI的動作
    		
    		int count = msg.arg1;
    		String status = (String) msg.obj;
    		
    		progressBar1.setProgress(count);
    		progressBar1.setSecondaryProgress(count + 20);
    		textView1.setText(status);
    		
    		if (msg != null) {
    			switch(msg.what) {
    				case COUNT_OK:
    					button1.setClickable(true);
    					button1.setTextColor(Color.BLACK);
    					break;
    				default:
    					break;
    			}
    		}
    	}
    }
    
    class MyThread extends Thread {
    	@Override
    	public void run() {
    		// TODO Auto-generated method stub
    		super.run();
    		
    		String str ;
    		for (int i = 1; i < 101; i++) {
    			try {
					Thread.sleep(20);
					
					/* 註明Message的用法:
					 * Message內建的變數有arg1,arg2,obj,what等等, 
					 * 其中arg1,arg2,what都是int整數型態,而obj為Object型態
					 * 通常會們會將要夾帶的簡單整數用arg1,arg2去存取,
					 * 而obj則是用來夾帶任意型態的參數,等到要取回的地方再強制轉型為你要的型態即可,
					 * 而what雖然也是夾帶整數,但是what的用意是要讓你在接收端做switch-case
					 * 的判斷,用來告知接收端這次的訊息屬於哪一類的 */
					
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
					
					handler.sendMessage(msg);
					/* 上面一行代碼將消息對象放置到消息隊列當中
					 * 1.Looper將會從消息隊列當中將消息對象取出
					 * 2.Looper將會找到與消息對象對應的handler對象
					 * 3.Looper將會調用handler對象的handleMessage(Message msg)方法,
					 * 用來處理消息對象 */
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
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
