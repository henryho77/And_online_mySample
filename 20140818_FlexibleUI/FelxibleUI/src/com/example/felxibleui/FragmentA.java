package com.example.felxibleui;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentA extends Fragment implements AdapterView.OnItemClickListener{
	
	private ListView listView1;
	Communicator comm;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_a, container, false);
		
		listView1 = (ListView) getActivity().findViewById(R.id.listView1);
		ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.titles, android.R.layout.simple_list_item_1);
		listView1.setAdapter(adapter);
		listView1.setOnItemClickListener(this);
		
		return rootView;
	}
	
	public void setCommunicator(Communicator comm){
		this.comm = comm;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		// TODO Auto-generated method stub
        Log.d("debug",(comm==null)+"");
        comm.respond(position);
	}
	
	public interface Communicator{
		
		public void respond(int index);
	}
}
