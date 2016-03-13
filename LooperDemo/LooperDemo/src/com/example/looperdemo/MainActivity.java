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
    		
    		/* �`�N�o��handler�O�bWorkerThread�ͦ���,
    		 * �ҥH��o��handler�{�b�bMainThread���ե�sendMessage()��k��,
    		 * �|�N�Ӯ�����H�o�e��WorkerThread����Looper�Ҵx�ު��������C�̭� */
    		//handler.sendMessage(msg);
    		
    		/* �W����handler.sendMessage(msg)�i�H��g��msg.sendToTarget()
    		 * ��ؤ�k�O�@�˪��ʧ@,�h�ݭ�l�X�A�N�|�o�{msg.sendToTarget()
    		 * public void sendToTarget() {
        	 *	  target.sendMessage(this);
    		 * }
    		 * �䤤��target�����N�O�o��Message���ݪ�handler��H
    		 * this�N�OMessage */
    		msg.sendToTarget();
    	}
    }
    
    class WorkerThread extends Thread {
    	@Override
    	public void run() {
    		
    		//�ǳ�Looper��H
    		Looper.prepare();
    		
    		//�bWorkerThread���ͦ��@��Handler��H
    		handler = new Handler() {
    			@Override
    			public void handleMessage(Message msg) {
    				super.handleMessage(msg);
    				
    				System.out.println("handleMessage()-->" + Thread.currentThread().getName()); 
    				System.out.println("���������H"); 
    				
    				Float fReceiveRatingVaule = (Float) msg.obj;		
    				System.out.println("fReceiveRatingVaule-->" + fReceiveRatingVaule); 
    			}
    		};
    		
    		/* �ե�Looper��loop()��k����,Looper��H�N���_���q�������C�����X������H,
    		 * �M��ե�handler��handleMessage()��k,�B�z�Ӯ�����H,
    		 * �p�G�������C���S����H,�h�ӽu�{���� */
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
