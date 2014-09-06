package com.example.processshareapp1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

	private EditText edt_messageBox;
	private TextView txt_status;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        edt_messageBox = (EditText) findViewById(R.id.edt_message);
        txt_status = (TextView) findViewById(R.id.txt_status);
    }

    public void saveFile(View view){
    	
    }
    
}
