package com.example.downloadimage;

import com.example.downloadimage.DownloadContent.DownloadCallBack;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

/* 本範例在示範從Http下載一張圖片,
 * 使用一個Button點擊到網路上下載圖片,
 * 下載好之後顯示在ImageView上
 * */

public class MainActivity extends ActionBarActivity {

//	private final String image_path = "https://www.google.com.tw/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";
	private final String image_path = "http://imgstatic.baidu.com/img/image/shouye/qichepaoche0822.jpg";
	
	private ImageView imageView1;
	private Button btn_downloadImage;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        btn_downloadImage = (Button) findViewById(R.id.btn_downloadImage);
        
        ButtonListener buttonListener = new ButtonListener();
        btn_downloadImage.setOnClickListener(buttonListener);
    }

    class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			
			/* 從網路上下載一張圖片的動作,通常需要開一個WorkerThread背後處理這件事,處理完再將data傳回至
			 * MainThread顯示,這樣通常就需要在MainThread建立WorkerThread以及宣告Handler處理,
			 * 這意味著如果我們每需要一次這樣的處理,就得重複做這件事情,最後會發現WorkerThread及Handler
			 * 的碼將讓MainThread搞得很凌亂,因此比較好的寫法是我們將下載圖片這個動作所需要的
			 * WorkerThread與Handler封裝起來,這邊我們封裝到DownloadContent.java
			 * 這樣MainThread的程式碼就會切得很乾淨,所以我們在這邊的onClick動作變得很簡單,
			 * 只需要宣告DownloadContent實體,並使用它的方法將圖片路徑傳進去,並且實作回調接口,
			 * 在MainThread看起來就只要短短幾行就完成了整個下載圖片的動作 */
			
			// TODO Auto-generated method stub
			System.out.println("onClick-->" + Thread.currentThread().getName());
			DownloadContent downloadContent = new DownloadContent(MainActivity.this);
			downloadContent.download(image_path, new DownloadCallBack() {
				
				@Override
				public void loadContent(byte[] result) {
					// TODO Auto-generated method stub
					System.out.println("loadContent-->" + Thread.currentThread().getName());
					Bitmap bitmap = BitmapFactory.decodeByteArray(result, 0, result.length);
					imageView1.setImageBitmap(bitmap);
				}
			});
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
