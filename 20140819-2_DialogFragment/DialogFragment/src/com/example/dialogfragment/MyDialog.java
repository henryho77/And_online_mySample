package com.example.dialogfragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MyDialog extends DialogFragment implements View.OnClickListener{
	
	private Button yes, no;
	Communicator communicator;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		communicator = (Communicator) activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		View rootView = inflater.inflate(R.layout.my_dialog, null);
		
		yes = (Button) rootView.findViewById(R.id.yes);
		no = (Button) rootView.findViewById(R.id.no);
		yes.setOnClickListener(this);
		no.setOnClickListener(this);
		setCancelable(false);
		
		return rootView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.yes)
		{
			dismiss();
			communicator.onDialogMessage("Yes button was clicked");
		}
		else
		{
			dismiss();
			communicator.onDialogMessage("No button was clicked");
		}
	}
	
	interface Communicator
	{
		public void onDialogMessage(String message);
	}
}
