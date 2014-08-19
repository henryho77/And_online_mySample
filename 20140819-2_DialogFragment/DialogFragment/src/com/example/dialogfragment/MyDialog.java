package com.example.dialogfragment;

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
			Toast.makeText(getActivity(), "Yes button was clicked", Toast.LENGTH_LONG).show();
		}
		else
		{
			dismiss();
			Toast.makeText(getActivity(), "No button was clicked", Toast.LENGTH_LONG).show();
		}
	}
}
