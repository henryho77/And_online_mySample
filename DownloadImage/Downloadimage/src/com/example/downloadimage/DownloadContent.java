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
	private ProgressDialog dialog;//下載圖片的等待過程使用ProgressDialog
	
	public DownloadContent(Context context) {
		this.context = context;
		dialog = new ProgressDialog(context);
		dialog.setTitle("提示");//設定ProgressDialog標題
		dialog.setMessage("正在獲取網路數據...");//設定ProgressDialog內文
	}
	
	public void download(final String path, final DownloadCallBack callback) {
		//handler在這邊宣告,則此handler就屬於MainThread的handler(因為它不是在別的線程中宣告的)
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				//下載好的圖片數據msg(是一串byte array)會傳到這邊做顯示處理
				System.out.println("handleMessage-->" + Thread.currentThread().getName());
				
				byte[] result = (byte[]) msg.obj;//將msg夾帶的物件obj強制轉型成byte[],並用一個byte[] result接收圖片數據
				callback.loadContent(result);//調用callback接口的loadContent方法,將得到的圖片數據result丟到負責實作回調接口的MainThread做顯示處理
				
				if (msg.what == 1) {
					//如果msg夾帶的整數what等於1(也就是說這個msg是我們預期想要收到的msg),就將ProgressDialog關閉
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
				
				HttpClient httpClient = new DefaultHttpClient();//使用HttpClient
				HttpPost httpPost = new HttpPost(path);//使用HttpPost對Server端做請求,將請求的路徑先填入HttpPost
				try {
					final HttpResponse response = httpClient.execute(httpPost);//Client對Server端執行Post請求,將得到的響應結果用Response來接
					System.out.println("response code--> " + response.getStatusLine().getStatusCode());
					if (response.getStatusLine().getStatusCode() == 200) {
						//若得到StatusCode為200 OK,就從response取得響應體,並轉成ByteArray,由byte[] result接收
						byte[] result = EntityUtils.toByteArray(response.getEntity());
						
						//將得到的result結果放進Message,透過Handler將Message傳到MainThread做處理
						Message msg = Message.obtain();
						msg.obj = result;
						msg.what = 1;
						handler.sendMessage(msg);
					} else {
						dialog.dismiss();//將ProgressDialog關閉
						
						//由於這是在WorkerThread,所以我們要顯示Toast的時候要回到MainThread
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
					httpClient.getConnectionManager().shutdown();//最後將HttpClient的關閉
				}
			}
		}
		
		new Thread(new MyThread()).start();//開啟WorkerThread
		dialog.show();//跳出ProgressDialog
	}
	
	public interface DownloadCallBack {
		public void loadContent(byte[] result);
	}
	
}
