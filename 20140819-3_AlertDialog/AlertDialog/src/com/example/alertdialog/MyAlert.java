package com.example.alertdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.widget.Toast;

public class MyAlert extends DialogFragment{
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		builder.setTitle("My Dialog");
		builder.setMultiChoiceItems(R.array.days, null, new OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), 
						"Item from position "+ which + " was selected " + isChecked,
						Toast.LENGTH_SHORT).show();
			}
		});
		
		builder.setNegativeButton(android.R.string.cancel, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "Negative button was clicked", Toast.LENGTH_SHORT).show();
			}
		});

		builder.setPositiveButton(android.R.string.ok, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "Positive button was clicked", Toast.LENGTH_SHORT).show();
			}
		});
		
		Dialog dialog = builder.create();
		
		return dialog;
	}
}
