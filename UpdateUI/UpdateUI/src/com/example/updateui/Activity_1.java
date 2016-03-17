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

/* ���MainActivity�ܽd���O�ϥ�handler.sendMessage(Message)�PhandleMessage(Message)��{��sUI���覡,
 * �{�bActivity_1�ܽd���O���handler.post(Runnable)��{��sUI���覡 */

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
			// ��Τ��I�����s��,�ڭ̶}�Ҥ@��WorkerThread�b�I�����p�ƪ��ʧ@
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
    		
    		//�o��Runnable r��run()��k���|�b�o�̳Q����,����handler.post(r)���ɭԤ~�|�i��o�̪�run()��k����,�ҥH�ڭ̱N�n��sUI���{���X�g�b�o�̭�
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
						//�e��N��
						str = "�p�Ƥ�..." + i + "%";
					} else {
						//�̫�@��
						str = "����!";
						msg.what = COUNT_OK;
					}
					msg.obj = str;
					
					receiveMsg = msg;
					handler.post(r);
					
					/* handler.post(r)��k�Nr��H��m��������C��,���O�p���@��Runnable��H��m�b�������C��?
					 * ��ڤW�O�ͦ��F�@��Message��H,�ñNr��ȵ�Message��H��callback�ݩ�,�M��A�NMessage��H
					 * ��m��������C��,����Looper��H(Main Thread)�q�������C���X��a��r��H��Message��H����,
					 * �եΤFdispatchMessage��k,�M��P�_Message��callback�ݩʬO�_����,���ɪ�callback�ݩʦ���,
					 * �ҥH����FhandleCallback(Message msg),�b�Ӥ�k������Fmsg.callback.run(),
					 * ��msg.callback�N�O�A���a��r��H,�ҥHhandler.post(r)��ڤW�O����Fr.run(),
					 * �ӳo��handler�S�]���O�bMainThread�ͦ���H��,�ҥHr.run()�N�O�bMainThread�W������(�Ӥ��O�bMyThread����),
					 * �ѩ�IOS�����Ѥ@�رN��q�{���X��@�ѼƶǨ�t�@�ݥh�����檺�o�ؼg�k,��Android�S���o�ؼg�k,
					 * �ҥHAndroid���ѤFhandler.post(Runnable)�o�˪��@�ؤ覡���ܬ۪���{�A�Q�N��q�{���X���MainThread����
					 * �ӧ�sUI�o�˪��@�ذʧ@ */
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    	}
    }
	
	
}
