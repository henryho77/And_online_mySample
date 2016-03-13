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
        
        handler = new MyHandler();//MainThread���ŧi��handler
        
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
    
    class MyHandler extends Handler {
    	@Override
    	public void handleMessage(Message msg) {
    		// TODO Auto-generated method stub
    		super.handleMessage(msg);
    		// �ڭ̦bhandleMessage��k��,�B�z��sUI���ʧ@
    		
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
					
					/* ����Message���Ϊk:
					 * Message���ت��ܼƦ�arg1,arg2,obj,what����, 
					 * �䤤arg1,arg2,what���Oint��ƫ��A,��obj��Object���A
					 * �q�`�|�̷|�N�n���a��²���ƥ�arg1,arg2�h�s��,
					 * ��obj�h�O�Ψӧ��a���N���A���Ѽ�,����n���^���a��A�j���૬���A�n�����A�Y�i,
					 * ��what���M�]�O���a���,���Owhat���ηN�O�n���A�b�����ݰ�switch-case
					 * ���P�_,�Ψӧi�������ݳo�����T���ݩ���@���� */
					
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
					
					handler.sendMessage(msg);
					/* �W���@��N�X�N������H��m��������C��
					 * 1.Looper�N�|�q�������C���N������H���X
					 * 2.Looper�N�|���P������H������handler��H
					 * 3.Looper�N�|�ե�handler��H��handleMessage(Message msg)��k,
					 * �ΨӳB�z������H */
					
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
