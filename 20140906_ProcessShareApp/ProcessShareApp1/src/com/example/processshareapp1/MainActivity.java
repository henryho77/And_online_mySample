package com.example.processshareapp1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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
    	File file = null;
    	String text1 = edt_messageBox.getText().toString();
    	FileOutputStream fileOutputStream = null;
    	try {
    		file = getFilesDir();
    		fileOutputStream = openFileOutput("mytest.txt", Context.MODE_PRIVATE);
    		fileOutputStream.write(text1.getBytes());
    		txt_status.setTextColor(Color.GREEN);
    		txt_status.setText(text1 + " \n written to\n " + file.getAbsolutePath());
    	} catch (FileNotFoundException e){
    		txt_status.setTextColor(Color.RED);
    		txt_status.setText(e.toString());
    	} catch (IOException e){
    		txt_status.setTextColor(Color.RED);
    		txt_status.setText(e.toString());
    	} finally {
    		try {
    			fileOutputStream.close();
    		} catch (IOException e){
        		txt_status.setTextColor(Color.RED);
        		txt_status.setText(e.toString());
    		}
    	}
    }
    
}
