package com.example.processshareapp2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

	private EditText edt_messageFromApp1;
	private TextView txt_status;
	
	String packageName = "com.example.processshareapp1";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        edt_messageFromApp1 = (EditText) findViewById(R.id.edt_messageFromApp1);
        txt_status = (TextView) findViewById(R.id.txt_status);
    }

    public void loadFile(View view){
    	PackageManager packageManager = getPackageManager();
    	try {
    		ApplicationInfo applicationInfo = packageManager
    				.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
//    		txt_status.setText(applicationInfo.dataDir);
    		String fullPath = applicationInfo.dataDir + "/files/mytest.text";
    		readFile(fullPath);
		
    	} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
    		txt_status.setTextColor(Color.RED);
    		txt_status.setText(e + "");
		}
    }
    
    public void readFile(String fullPath){
    	FileInputStream fileInputStream = null;
    	try {
			fileInputStream = new FileInputStream(new File(fullPath));
			int read = -1;
			StringBuffer buffer = new StringBuffer();
			while((read = fileInputStream.read()) != -1) {
				buffer.append((char) read);
			}
			
//			L.s(this, "" + buffer);
			edt_messageFromApp1.setText(buffer);
			txt_status.setTextColor(Color.GREEN);
			txt_status.setText(buffer + " \n was read successfully from \n"
						+ fullPath);
			
		} catch (FileNotFoundException e) {
    		txt_status.setTextColor(Color.RED);
    		txt_status.setText(e + "");
    		
		} catch (IOException e){
    		txt_status.setTextColor(Color.RED);
    		txt_status.setText(e + "");
    		
		} finally {
			if (fileInputStream != null){
				try	{
					fileInputStream.close();
				} catch (IOException e){
		    		txt_status.setTextColor(Color.RED);
		    		txt_status.setText(e + "");
				}
			}
		}
    }
    
}
