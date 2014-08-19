package com.example.alertdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class MyAlert extends DialogFragment{
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		Dialog dialog = builder.create();
		
		return dialog;
	}
}
