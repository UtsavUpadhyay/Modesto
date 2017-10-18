package com.modestoappln;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ChatOptions extends ListActivity{
	ListView list;
	ArrayList<String> user=new ArrayList<String>();
    ArrayList<String> chat=new ArrayList<String>();
    
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	    Intent in=getIntent();
	    user=in.getStringArrayListExtra("data1");
	    chat=in.getStringArrayListExtra("data");
	    list = getListView();
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_list_item_activated_1, chat);
        list.setAdapter(adapter);
        }

    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		Intent i=new Intent("com.modestoappln.ChatPage");
		i.putExtra("ChatData", l.getItemAtPosition(position).toString());
		i.putStringArrayListExtra("userData", user);
		startActivity(i);
	}
    

}
