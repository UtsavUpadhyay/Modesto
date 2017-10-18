package com.modestoappln;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.DaoOBJ.Daoaccess;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ChatPage extends Activity {

	ArrayList<String> al=new ArrayList<String>();
	boolean flag=false;
	private Handler handler = new Handler();
	public ListView msgView;
	public ArrayAdapter<String> msgList;
	SharedPreferences shared;
	String username;
	String placedata;
	ArrayList<String> al_revw=new ArrayList<String>();
	public static final String LOGN_PREF = "LoginPrefs";
//	public ArrayAdapter<String> msgList=new ArrayAdapter<String>(this,
//			android.R.layout.simple_list_item_1);;
	Button bt_recv;
	Daoaccess dao=new Daoaccess();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		Intent i=getIntent();
		Bundle place=i.getExtras();
		placedata=(String) place.get("ChatData");
		al=place.getStringArrayList("userData");
		shared=getSharedPreferences(LOGN_PREF, 0);
		username=shared.getString("USERNAME", "");
		msgView = (ListView) findViewById(R.id.chatlistView);
		
		msgList = new ArrayAdapter<String>(this,
		android.R.layout.simple_list_item_1);
		msgView.setAdapter(msgList);

//		msgView.smoothScrollToPosition(msgList.getCount() - 1);

		Button btnSend = (Button) findViewById(R.id.chatbtn_Send);
		
		receiveMsg();
		btnSend.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				final EditText txtEdit = (EditText) findViewById(R.id.chattxt_inputText);
				//msgList.add(txtEdit.getText().toString());
				sendMessageToServer(txtEdit.getText().toString());
				txtEdit.setText(null);
				msgView.smoothScrollToPosition(msgList.getCount() - 1);
			
			}			
		});
		bt_recv=(Button) findViewById(R.id.chatbtn_revw);
		bt_recv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final EditText txtEdit1 = (EditText) findViewById(R.id.chattxt_inputText);
				al_revw.clear();
				al_revw.add(username);
				al_revw.add(placedata);
				al_revw.add(txtEdit1.getText().toString());
				dao.sendComRevw(al_revw);
				AlertDialog.Builder dialog=new AlertDialog.Builder(ChatPage.this);
				dialog.setTitle("Navigate");
				dialog.setMessage("Review submitted");
				
				dialog.setPositiveButton("home",
			            new DialogInterface.OnClickListener() {
			                public void onClick(DialogInterface arg0, int arg1) {
			                	Intent in=new Intent("com.modestoappln.MainActivity");
								startActivity(in);	
			                }
			            });
				dialog.setNegativeButton("Search more",
			            new DialogInterface.OnClickListener() {
			                public void onClick(DialogInterface arg0, int arg1) {
			                	Intent in=new Intent("com.modestoappln.Restaurant");
								startActivity(in);
			                }
			            });
				dialog.show();
			}
		});
	}
	
	public void sendMessageToServer(String str) {
		
		final String str1=str;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String host="192.168.1.103";
				PrintWriter out;
				Socket socket;
				try {
					if(!flag)
					{
					socket = new Socket(host, 58000);
					out = new PrintWriter(socket.getOutputStream());
					out.println(InetAddress.getLocalHost());
					out.println(placedata);
					flag=true;
					}
					else
					{
					socket = new Socket(host, 58000);
					out = new PrintWriter(socket.getOutputStream());
					out.println(str1);
					}
					Log.d("hello", "  "+flag);
					Log.d("hello", "hello");
					out.flush();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.d("hello", "error");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.d("hello", "IO error");
				}
	
			}
		}).start();
			}

	
	
	public void receiveMsg()
	{
		new Thread(new Runnable()
		{
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				final String host="192.168.1.103";
		    	Socket socket = null ;
		    	BufferedReader in = null;
		        try {
					socket = new Socket(host,58000);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NullPointerException ne){
					ne.printStackTrace();
				}

		    	while(true)
		    	{
		    		String msg = null;
					try {
						msg = in.readLine();
						Log.d("","MSGGG:  "+ msg);
						
						//msgList.add(msg);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		if(msg == null)
		    		{
		    			break;
		    		}
		    		else
		    		{
		    			displayMsg(username+" : "+msg);
		    		}
		    	}
			
			}
		}).start();
		
		
	}
	
	public void displayMsg(String msg)
	{ 
		final String mssg=msg;
	    handler.post(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				msgList.add(mssg);
				msgView.setAdapter(msgList);
				msgView.smoothScrollToPosition(msgList.getCount() - 1);
				Log.d("hello","hi");
			}
		});
	}
}