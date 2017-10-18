package com.modestoappln;

import android.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListMenuItems extends ListActivity {

	String selection[]={"Explore","Review","Login","MainActivity","GroupReview","SearchCity"};
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String page=selection[position];
		 
		try {
			@SuppressWarnings("rawtypes")
			Class cla = Class.forName("com.modestoappln."+page);
			Intent in=new Intent(ListMenuItems.this,cla);
			startActivity(in);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(ListMenuItems.this,R.layout.simple_list_item_1, selection));
		
	}

}
