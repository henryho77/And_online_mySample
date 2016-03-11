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

/* ���d�Ҧb�ܽd�qHttp�U���@�i�Ϥ�,
 * �ϥΤ@��Button�I��������W�U���Ϥ�,
 * �U���n������ܦbImageView�W
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
			
			/* �q�����W�U���@�i�Ϥ����ʧ@,�q�`�ݭn�}�@��WorkerThread�I��B�z�o���,�B�z���A�Ndata�Ǧ^��
			 * MainThread���,�o�˳q�`�N�ݭn�bMainThread�إ�WorkerThread�H�ΫŧiHandler�B�z,
			 * �o�N���ۦp�G�ڭ̨C�ݭn�@���o�˪��B�z,�N�o���ư��o��Ʊ�,�̫�|�o�{WorkerThread��Handler
			 * ���X�N��MainThread�d�o�ܭ��,�]������n���g�k�O�ڭ̱N�U���Ϥ��o�Ӱʧ@�һݭn��
			 * WorkerThread�PHandler�ʸ˰_��,�o��ڭ̫ʸ˨�DownloadContent.java
			 * �o��MainThread���{���X�N�|���o�ܰ��b,�ҥH�ڭ̦b�o�䪺onClick�ʧ@�ܱo��²��,
			 * �u�ݭn�ŧiDownloadContent����,�èϥΥ�����k�N�Ϥ����|�Ƕi�h,�åB��@�^�ձ��f,
			 * �bMainThread�ݰ_�ӴN�u�n�u�u�X��N�����F��ӤU���Ϥ����ʧ@ */
			
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
