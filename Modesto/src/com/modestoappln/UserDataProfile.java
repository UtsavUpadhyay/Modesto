package com.modestoappln;

import java.util.ArrayList;

import bean.Userbean;

import com.DaoOBJ.Daoaccess;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;

public class UserDataProfile extends Activity implements OnClickListener,OnKeyListener {

	EditText etuser1,etuser2,etuser3,etuser4,etuser5,etuser6;
	Button btnuserSubmit;
	Button gbtn,fbtn,tbtn;
	Button b1,b2,b3,b4,b5,b6;
	ArrayList<String> al=new ArrayList<String>();
	ArrayList<String> al_return=new ArrayList<String>();
	Daoaccess dao=new Daoaccess();
	SharedPreferences shared;
	public static final String LOGN_PREF = "LoginPrefs";
	Userbean ub=new Userbean();
	int i=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	    shared=getSharedPreferences(LOGN_PREF, 0);
	    al=dao.getUserData(shared.getString("USERNAME", ""));
		initialize();
		btnuserSubmit.setOnClickListener(this);
	}

	private void initialize() {
		// TODO Auto-generated method stub
		
		etuser2=(EditText) findViewById(R.id.ProfileSignupLoginUsereditText2);
		etuser3=(EditText) findViewById(R.id.ProfileSignupLoginUsereditText3);
		etuser4=(EditText) findViewById(R.id.ProfileSignupLoginUsereditText4);
		etuser5=(EditText) findViewById(R.id.ProfileSignupLoginUsereditText5);
		etuser6=(EditText) findViewById(R.id.ProfileSignupLoginUsereditText6);
		btnuserSubmit=(Button) findViewById(R.id.ProfileSignupUserSubmiitBtn1);
		
		etuser2.setText(al.get(1));
		etuser3.setText(al.get(2));
		etuser4.setText(al.get(5));
		etuser5.setText(al.get(4));
		etuser6.setText(al.get(3));
		
		etuser2.setOnKeyListener(this);
		etuser3.setOnKeyListener(this);
		etuser4.setOnKeyListener(this);
		etuser5.setOnKeyListener(this);
		etuser6.setOnKeyListener(this);
		
		b2=(Button) findViewById(R.id.ProfileBtn2);
		b3=(Button) findViewById(R.id.ProfileBtn3);
		b4=(Button) findViewById(R.id.ProfileBtn4);
		b5=(Button) findViewById(R.id.ProfileBtn5);
		b6=(Button) findViewById(R.id.ProfileBtn6);
		
		b2.setClickable(false);
		b2.setVisibility(View.INVISIBLE);
		b3.setClickable(false);
		b3.setVisibility(View.INVISIBLE);
		b4.setClickable(false);
		b4.setVisibility(View.INVISIBLE);
		b5.setClickable(false);
		b5.setVisibility(View.INVISIBLE);
		b6.setClickable(false);
		b6.setVisibility(View.INVISIBLE);
		
		
		gbtn=(Button) findViewById(R.id.Btngoogle);
		tbtn=(Button) findViewById(R.id.Btntwitter);
		fbtn=(Button) findViewById(R.id.Btnfacebook);
		
		gbtn.setClickable(true);
		tbtn.setClickable(true);
		fbtn.setClickable(true);
		
		gbtn.setOnClickListener(this);
		tbtn.setOnClickListener(this);
		fbtn.setOnClickListener(this);
		
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		b5.setOnClickListener(this);
		b6.setOnClickListener(this);
		
		btnuserSubmit.setOnClickListener(this);
	}
	
	 public void submitDialog() {
		    AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
		    myAlertDialog.setTitle("Successfully Edited!");
		    myAlertDialog.setMessage("you will be redirected on login page or Continue Editing");

		    myAlertDialog.setPositiveButton("Login",
		            new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface arg0, int arg1) {
		                	Intent i=new Intent("com.modestoappln.MainActivity");
		        			startActivity(i);		        			
		                }
		            });

		    myAlertDialog.setNegativeButton("Edit",
		            new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface arg0, int arg1) {
		                	Intent j=new Intent("com.modestoappln.UserDataProfile");
		        			startActivity(j);
		                }
		            });
		    myAlertDialog.show();
		}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		ub.clearUsername();
		ub.setUsername(shared.getString("USERNAME", ""));
		
		switch(v.getId())
		{
		case R.id.ProfileBtn2 :
			ub.clearPassword();
			ub.setPassword(etuser2.getText().toString());
			break;
		case R.id.ProfileBtn3 :
			ub.clearName();
			ub.setName(etuser3.getText().toString());
			break;
		case R.id.ProfileBtn4 :
			ub.clearEmail();
			ub.setEmail(etuser4.getText().toString());
			break;
		case R.id.ProfileBtn5 :
			ub.clearUser_contact();
			ub.setUser_contact(etuser5.getText().toString());
			break;
		case R.id.ProfileBtn6 :
			ub.clearAddress();
			ub.setAddress(etuser6.getText().toString());
			break;
		case R.id.ProfileSignupUserSubmiitBtn1 :
			int iLogin;
			Log.d("hello", "in click methid");
			if(!etuser2.getText().toString().isEmpty())
			{
			Log.d("username", "is "+shared.getString("USERNAME", "").toString());
			ub.setUsername(shared.getString("USERNAME", "").toString());
			ub.setPassword(etuser2.getText().toString());
			ub.setName(etuser3.getText().toString());
			ub.setEmail(etuser4.getText().toString());
			ub.setUser_contact(etuser5.getText().toString());
			ub.setAddress(etuser6.getText().toString());
			iLogin=dao.connectIP(ub,i);
			Log.d("hello", "Variable got is" +iLogin);
			if(iLogin==1)
				{
				submitDialog();
				}
			}
			else
			{
				 	AlertDialog.Builder myalertDialog = new AlertDialog.Builder(this);
				    myalertDialog.setTitle("Error");
				    myalertDialog.setMessage("username or password is empty!");
				    myalertDialog.show();
			}
			break;
		}
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.ProfileSignupLoginUsereditText2:
			b2.setClickable(true);
			b2.setVisibility(View.VISIBLE);
			
			break;
		case R.id.ProfileSignupLoginUsereditText3:
			b3.setClickable(true);
			b3.setVisibility(View.VISIBLE);
			
			break;
		case R.id.ProfileSignupLoginUsereditText4:
			b4.setClickable(true);
			b4.setVisibility(View.VISIBLE);
			
			break;
		case R.id.ProfileSignupLoginUsereditText5:
			b5.setClickable(true);
			b5.setVisibility(View.VISIBLE);
			
			break;
		case R.id.ProfileSignupLoginUsereditText6:
			b6.setClickable(true);
			b6.setVisibility(View.VISIBLE);
			
			break;
	
		}
		return false;
	}
}
