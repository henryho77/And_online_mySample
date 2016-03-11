package com.example.downloadimage;

import java.io.IOException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;



public class DownloadContent {
	private Context context;
	private ProgressDialog dialog;//�U���Ϥ������ݹL�{�ϥ�ProgressDialog
	
	public DownloadContent(Context context) {
		this.context = context;
		dialog = new ProgressDialog(context);
		dialog.setTitle("����");//�]�wProgressDialog���D
		dialog.setMessage("���b��������ƾ�...");//�]�wProgressDialog����
	}
	
	public void download(final String path, final DownloadCallBack callback) {
		//handler�b�o��ŧi,�h��handler�N�ݩ�MainThread��handler(�]�������O�b�O���u�{���ŧi��)
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				//�U���n���Ϥ��ƾ�msg(�O�@��byte array)�|�Ǩ�o�䰵��ܳB�z
				System.out.println("handleMessage-->" + Thread.currentThread().getName());
				
				byte[] result = (byte[]) msg.obj;//�Nmsg���a������obj�j���૬��byte[],�åΤ@��byte[] result�����Ϥ��ƾ�
				callback.loadContent(result);//�ե�callback���f��loadContent��k,�N�o�쪺�Ϥ��ƾ�result���t�d��@�^�ձ��f��MainThread����ܳB�z
				
				if (msg.what == 1) {
					//�p�Gmsg���a�����what����1(�]�N�O���o��msg�O�ڭ̹w���Q�n���쪺msg),�N�NProgressDialog����
					dialog.dismiss();
				}
			}
		};
		
		class MyThread implements Runnable {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//super.run();
				System.out.println("MyThread-->" + Thread.currentThread().getName());
				
				HttpClient httpClient = new DefaultHttpClient();//�ϥ�HttpClient
				HttpPost httpPost = new HttpPost(path);//�ϥ�HttpPost��Server�ݰ��ШD,�N�ШD�����|����JHttpPost
				try {
					final HttpResponse response = httpClient.execute(httpPost);//Client��Server�ݰ���Post�ШD,�N�o�쪺�T�����G��Response�ӱ�
					System.out.println("response code--> " + response.getStatusLine().getStatusCode());
					if (response.getStatusLine().getStatusCode() == 200) {
						//�Y�o��StatusCode��200 OK,�N�qresponse���o�T����,���নByteArray,��byte[] result����
						byte[] result = EntityUtils.toByteArray(response.getEntity());
						
						//�N�o�쪺result���G��iMessage,�z�LHandler�NMessage�Ǩ�MainThread���B�z
						Message msg = Message.obtain();
						msg.obj = result;
						msg.what = 1;
						handler.sendMessage(msg);
					} else {
						dialog.dismiss();//�NProgressDialog����
						
						//�ѩ�o�O�bWorkerThread,�ҥH�ڭ̭n���Toast���ɭԭn�^��MainThread
						((MainActivity)context).runOnUiThread(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								Toast.makeText(context, 
										"response error code: " + response.getStatusLine().getStatusCode(),
										Toast.LENGTH_SHORT).show();
							}
						});						
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					httpClient.getConnectionManager().shutdown();//�̫�NHttpClient������
				}
			}
		}
		
		new Thread(new MyThread()).start();//�}��WorkerThread
		dialog.show();//���XProgressDialog
	}
	
	public interface DownloadCallBack {
		public void loadContent(byte[] result);
	}
	
}
