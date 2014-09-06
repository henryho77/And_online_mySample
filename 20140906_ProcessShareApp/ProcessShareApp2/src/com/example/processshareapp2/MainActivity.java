package com.example.processshareapp2;

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
    		txt_status.setText(applicationInfo.dataDir);
		
    	} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
    		txt_status.setTextColor(Color.RED);
    		txt_status.setText(e + "");
		}
    }
    
    public void readFile(String fullPath){
    	
    }
    
}
