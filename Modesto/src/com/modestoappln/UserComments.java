package com.modestoappln;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class UserComments extends ListActivity {
	ListView list;
	Bundle ar;
	ArrayList<ArrayList<String>> al=new ArrayList<ArrayList<String>>();
	ArrayList<String> review=new ArrayList<String>();
    ArrayList<String> comment=new ArrayList<String>();
    
	@SuppressWarnings("unchecked")
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	    Intent in=getIntent();
	    ar=in.getExtras();
	    al=(ArrayList<ArrayList<String>>) ar.get("DataReviews");
	    comment=al.get(1);
	    list = getListView();
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_list_item_activated_1,comment);
        list.setAdapter(adapter);
        }

    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		AlertDialog.Builder dialog=new AlertDialog.Builder(this);
		dialog.setTitle("Comments");
		dialog.setMessage(l.getItemAtPosition(position).toString());
		dialog.show();
		
	}

}
