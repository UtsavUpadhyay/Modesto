package com.modestoappln;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import bean.SessionVars;

import com.DaoOBJ.Daoaccess;

import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.Settings;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.Toast;



@SuppressWarnings("deprecation")
public class MainActivity extends Activity implements OnDrawerOpenListener,OnItemClickListener,OnTouchListener,OnTabChangeListener,OnClickListener,OnItemSelectedListener {
Button btn1,btn2,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15;
Button gbtn,fbtn,tbtn;
TabHost thr;
TimerTask tt;
ListView listCom;
ArrayList<TabHost.TabSpec> tabHostSpecs=new ArrayList<TabHost.TabSpec>();
ArrayList<TabHost.TabSpec> tabHostRestartSpecs=new ArrayList<TabHost.TabSpec>();
ImageView iv,iv1,iv2,iv3;
String[] items = new String[]{"Select","Restaurant", "Theatres", "Dining","Hangout Places"};
Bitmap bmp;
EditText uName,uPass;
ArrayList<String> uReview=new ArrayList<String>();
ArrayList<String> uComment=new ArrayList<String>();
Daoaccess dao=new Daoaccess();
Uri selectedImage;
SlidingDrawer sd,sdSearch;
public int num=0,random=0;
int[] mark={R.drawable.food2,R.drawable.food8,R.drawable.food7,R.drawable.food6,R.drawable.food5,R.drawable.food1,R.drawable.food,R.drawable.food3};
ListView lst,lst1,lst2;
TabSpec t1,t2,t3,t4,t5,t6;
Intent i,j;
ImageButton ib1,ib2,ib3,ib4;
ImageButton ibglo1,ibglo2,ibglo3,ibglo4,ibzen1,ibzen2,ibzen3,ibzen4,ibthet1,ibthet2,ibthet3,ibthet4,ibdin1,ibdin2,ibdin3,ibdin4;
final static int camera_data=0;
Typeface font;
String selection[]={"Login","Your Review","Your Account","Your Comments","Special Activities","Search","Logout"};
String selection1[]={"Contact Community","Build your own","Group Reviewing"};
String selection2[]={"Make Your Own Recipe","Take a Snap","Choose Your DP"};
HorizontalScrollView hs;
int imageHeight,imageWidth;
String imageType;
View previousView;
View currentView;
static final int ANIMATION_TIME = 240;
GestureDetector gestureDetector;
int currentTab=0;
TabWidget tw;
boolean flag=true,flag1=false;
RelativeLayout r1,r2,r3,r4;
LinearLayout l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19;
int newTab;
BitmapFactory.Options options = new BitmapFactory.Options();
Boolean loginflag=false;
SessionVars sv=new SessionVars();
public static final String LOGN_PREF = "LoginPrefs";
SharedPreferences sharedPrefs;
ArrayList<ArrayList<String>> al_user_data=new ArrayList<ArrayList<String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	    sharedPrefs=getSharedPreferences(LOGN_PREF, 0);
	    instantiate();
	    check();
	}
	
		private void check() {
		// TODO Auto-generated method stub
			ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

			boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
			            .isConnectedOrConnecting();
			boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
			            .isConnectedOrConnecting();

			System.out.println(is3g + " net " + isWifi);
			if (!is3g && !isWifi) 
			{
		    	Toast.makeText(getApplicationContext(),"Please make sure your Network Connection is ON ",Toast.LENGTH_LONG).show();
		    	AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
				dialog.setTitle("connection");
				dialog.setMessage("Please make sure your Network Connection is ON ");
				
				dialog.setPositiveButton("ok",
			            new DialogInterface.OnClickListener() {
			                public void onClick(DialogInterface arg0, int arg1) {
			                	startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
			                	finish();
			                }
			            });
				dialog.setNegativeButton("exit",
			            new DialogInterface.OnClickListener() {
			                public void onClick(DialogInterface arg0, int arg1) {
			                finish();
			                }
			            });
				dialog.show();
			} 
			 else 
			{ 
			loginCheck();
			}
	}
		
		private void loginCheck() {
		// TODO Auto-generated method stub
		try
		{
			if(sharedPrefs.getString("ID", "").equals("LOGGED IN"))
			{
				Log.d("hello", "  "+sharedPrefs.getString("ID", "").toString());
				al_user_data.clear();
				al_user_data=dao.getReviewCommentData(sharedPrefs.getString("USERNAME",""));
				Log.d("hello", "data is "+al_user_data.get(1));
				listCom.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,al_user_data.get(1)));
				listCom.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
						dialog.setTitle("Comments");
						dialog.setMessage(listCom.getItemAtPosition(position).toString());
						dialog.show();
					}
				});
				removeTab();
			}
		}
		catch(NullPointerException nl)
		{
			nl.printStackTrace();
		}
	}

	public void instantiate(){
		
		Spinner dropdown = (Spinner)findViewById(R.id.spinnerexp);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
		dropdown.setAdapter(adapter);
		dropdown.setOnItemSelectedListener(this);
		
		listCom=(ListView) findViewById(R.id.listViewCommentOther);
		uName=(EditText) findViewById(R.id.LogineditText1);
		uPass=(EditText) findViewById(R.id.LogineditText2);
		gbtn=(Button) findViewById(R.id.Btngoogle);
		tbtn=(Button) findViewById(R.id.Btntwitter);
		fbtn=(Button) findViewById(R.id.Btnfacebook);
		b12= (Button) findViewById(R.id.LoginSignupbutton1);
		b13= (Button) findViewById(R.id.Loginsignupbutton2);
		b14= (Button) findViewById(R.id.Loginsignupbtn3);
		b15= (Button) findViewById(R.id.Homebtn2);
		b2= (Button) findViewById(R.id.btnSearchCity);
		b5= (Button) findViewById(R.id.btnSearchComment);
		b6= (Button) findViewById(R.id.btnSearchCommunity);
		b7= (Button) findViewById(R.id.btnSearchContact);
		b8= (Button) findViewById(R.id.btnSearchGroupReviews);
		b9= (Button) findViewById(R.id.btnSearchInterest);
		b10= (Button) findViewById(R.id.btnSearchlogin);
		b11= (Button) findViewById(R.id.btnSearchReviews);
		l5= (LinearLayout) findViewById(R.id.LinearYourReview);
		r1= (RelativeLayout) findViewById(R.id.GroupRelaLayout);
		iv=(ImageView)findViewById(R.id.imgViewTab1);
		l4= (LinearLayout) findViewById(R.id.LineaarSearchFirst);
		l10= (LinearLayout) findViewById(R.id.LinearLayFirstButton1);
		l11= (LinearLayout) findViewById(R.id.LinearLayFirstButton2);
		l12= (LinearLayout) findViewById(R.id.ReviewsGlobLay1);
		l13= (LinearLayout) findViewById(R.id.ReviewsGlobLay2);
		l14= (LinearLayout) findViewById(R.id.ReviewsGlobLay3);
		l15= (LinearLayout) findViewById(R.id.ReviewsGlobLay4);
		l16= (LinearLayout) findViewById(R.id.ReviewsGlobLay5);
		l17= (LinearLayout) findViewById(R.id.ReviewsGlobLay6);
		l18= (LinearLayout) findViewById(R.id.ReviewsGlobLay7);
		l19= (LinearLayout) findViewById(R.id.ReviewsGlobLay8);
		l2= (LinearLayout) findViewById(R.id.tab4);
		r2= (RelativeLayout) findViewById(R.id.tab5);
		l3= (LinearLayout) findViewById(R.id.tab6);
		l6= (LinearLayout) findViewById(R.id.img1Layout);
		l7= (LinearLayout) findViewById(R.id.img2Layout);
		l8= (LinearLayout) findViewById(R.id.img3Layout);
		l9= (LinearLayout) findViewById(R.id.img4Layout);
		r3= (RelativeLayout) findViewById(R.id.YourInterestRelLayout);
		r4= (RelativeLayout) findViewById(R.id.explore);
		ib1= (ImageButton) findViewById(R.id.imgRes1);
		ib2= (ImageButton) findViewById(R.id.imgDining1);
		ib3= (ImageButton) findViewById(R.id.imgTheatre1);
		ib4= (ImageButton) findViewById(R.id.imgHangout1);
		lst2=(ListView)findViewById(R.id.listViewInterest1);
		lst1=(ListView)findViewById(R.id.listViewComm1);
		iv1=(ImageView) findViewById(R.id.imageViewInt1);
		iv2=(ImageView) findViewById(R.id.imageViewInt2);
		iv3=(ImageView) findViewById(R.id.imageViewInt3);
		ibglo1= (ImageButton)findViewById(R.id.imageexp2);
		ibglo2= (ImageButton)findViewById(R.id.imageexp3);
		ibglo3= (ImageButton)findViewById(R.id.imageexp1);
		ibglo4= (ImageButton)findViewById(R.id.imageexp4);
		ibzen1= (ImageButton)findViewById(R.id.imageexp13);
		ibzen2= (ImageButton)findViewById(R.id.imageexp14);
		ibzen3= (ImageButton)findViewById(R.id.imageexp15);
		ibzen4= (ImageButton)findViewById(R.id.imageexp16);
		ibthet1= (ImageButton)findViewById(R.id.imageexp9);
		ibthet2= (ImageButton)findViewById(R.id.imageexp10);
		ibthet3= (ImageButton)findViewById(R.id.imageexp11);
		ibthet4= (ImageButton)findViewById(R.id.imageexp12);
		ibdin1= (ImageButton)findViewById(R.id.imageexp5);
		ibdin2= (ImageButton)findViewById(R.id.imageexp6);
		ibdin3= (ImageButton)findViewById(R.id.imageexp7);
		ibdin4= (ImageButton)findViewById(R.id.imageexp8);
		b3=(Button) findViewById(R.id.submitInt);
		b4=(Button) findViewById(R.id.clearInt);
		thr=(TabHost)findViewById(R.id.tabhost1);
		lst=(ListView)findViewById(R.id.listSlider);
		b1=(Button) findViewById(R.id.SlidingButtonList);
		sd=(SlidingDrawer) findViewById(R.id.slidingDrawer2);
		hs= (HorizontalScrollView) findViewById(R.id.HrScrollView);
		sdSearch=(SlidingDrawer) findViewById(R.id.slidingDrawer1);		
		
		hs.setClickable(false);
		gbtn.setClickable(true);
		tbtn.setClickable(true);
		fbtn.setClickable(true);
		gbtn.setOnClickListener(this);
		tbtn.setOnClickListener(this);
		fbtn.setOnClickListener(this);
		b15.setOnClickListener(this);
		b13.setOnClickListener(this);
		ib4.setOnClickListener(this);
		ib1.setOnClickListener(this);
		ib2.setOnClickListener(this);
		ib3.setOnClickListener(this);
		
		
		b12.setOnTouchListener(this);
		b2.setOnClickListener(this);
		b5.setOnClickListener(this);
		b6.setOnClickListener(this);
		b7.setOnClickListener(this);
		b8.setOnClickListener(this);
		b9.setOnClickListener(this);
		b10.setOnClickListener(this);
		b11.setOnClickListener(this);
		l5.setOnTouchListener(this);
		iv.setOnTouchListener(this);
		l4.setOnTouchListener(this);
		l10.setOnTouchListener(this);
		l11.setOnTouchListener(this);
		l2.setOnTouchListener(this);
		l3.setOnTouchListener(this);
		l6.setOnTouchListener(this);
		l7.setOnTouchListener(this);
		l8.setOnTouchListener(this);
		l9.setOnTouchListener(this);
		r1.setOnTouchListener(this);
		r2.setOnTouchListener(this);
		r3.setOnTouchListener(this);
		r4.setOnTouchListener(this);
		
		
		ibglo1.setOnTouchListener(this);
		ibglo2.setOnTouchListener(this);
		ibglo3.setOnTouchListener(this);
		ibglo4.setOnTouchListener(this);
		ibzen1.setOnTouchListener(this);
		ibzen2.setOnTouchListener(this);
		ibzen3.setOnTouchListener(this);
		ibzen4.setOnTouchListener(this);
		ibthet1.setOnTouchListener(this);
		ibthet2.setOnTouchListener(this);
		ibthet3.setOnTouchListener(this);
		ibthet4.setOnTouchListener(this);
		ibdin1.setOnTouchListener(this);
		ibdin2.setOnTouchListener(this);
		ibdin3.setOnTouchListener(this);
		ibdin4.setOnTouchListener(this);
		
		thr.setOnTabChangedListener(this);
		
		sd.setOnDrawerOpenListener(this);
		b14.setOnClickListener(this);
		l13.setClickable(true);
		l12.setClickable(true);
		l14.setClickable(true);
		l15.setClickable(true);
		l16.setClickable(true);
		l17.setClickable(true);
		l18.setClickable(true);
		l19.setClickable(true);
		l13.setOnClickListener(this);
		l12.setOnClickListener(this);
		l14.setOnClickListener(this);
		l15.setOnClickListener(this);
		l16.setOnClickListener(this);
		l17.setOnClickListener(this);
		l18.setOnClickListener(this);
		l19.setOnClickListener(this);
				
		decoderImages();
		
		thr.setup();
		t1=thr.newTabSpec("tab1");
		t1.setContent(R.id.tab1);
		t1.setIndicator("Exploration");
		thr.addTab(t1);
		t2=thr.newTabSpec("tab2");
		t2.setContent(R.id.tab2);
		t2.setIndicator("Search");
		thr.addTab(t2);
		t3=thr.newTabSpec("tab3");
		t3.setContent(R.id.tab3);
		t3.setIndicator("Reviews");
		thr.addTab(t3);
		
		t4=thr.newTabSpec("tab4");
		t4.setContent(R.id.tab4);
		t4.setIndicator("Group Activities");
		thr.addTab(t4);
		t5=thr.newTabSpec("tab5");
		t5.setContent(R.id.tab5);
		t5.setIndicator("Login");
		thr.addTab(t5);
		t6=thr.newTabSpec("tab6");
		t6.setContent(R.id.tab6);
		t6.setIndicator("Your Interest");
		thr.addTab(t6);
		tabHostSpecs.add(t1);
		tabHostSpecs.add(t2);
		tabHostSpecs.add(t3);
		tabHostSpecs.add(t4);
		tabHostSpecs.add(t6);
		tabHostRestartSpecs.add(t1);
		tabHostRestartSpecs.add(t2);
		tabHostRestartSpecs.add(t3);
		tabHostRestartSpecs.add(t4);
		tabHostRestartSpecs.add(t5);
		tabHostRestartSpecs.add(t6);
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(flag==true)
				{
					sd.open();
					flag=false;
				}
				else
				{
					sd.close();
					flag=true;
				}
			}
		});
		
		b4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				iv1.setImageResource(R.drawable.news_feed1_150x150);
				iv2.setImageResource(R.drawable.meal);
				iv3.setImageResource(R.drawable.username);
				b3.setVisibility(View.INVISIBLE);
				b4.setVisibility(View.INVISIBLE);
			}
		
		});
		
//		previousView = thr.getCurrentView();
		gestureDetector = new GestureDetector(getApplicationContext(), new MyGestureDetector());
		
		lst.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,selection));
		lst1.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,selection1));
		lst2.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,selection2));
		
		lst2.setOnItemClickListener(this);
		
		lst.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Log.d("hello", "in listener on item click of lst:    ");
				switch(position)
				{
				case 0:
					sdSearch.close();
					try{
					thr.setCurrentTabByTag("tab5");
					}catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case 1:
					try
					{
						if(sharedPrefs.getString("ID", "").equals("LOGGED IN"))
						{
							Intent in=new Intent("com.modestoappln.UserReviews");
							in.putExtra("DataReviews", al_user_data);
		        			startActivity(in);
						}
						else
						{
							AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(MainActivity.this);
							myAlertDialog.setTitle("Error");
							myAlertDialog.setMessage("login error");
							myAlertDialog.show();
						}
					}
					catch(NullPointerException nl)
					{
						AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(MainActivity.this);
						myAlertDialog.setTitle("Error");
						myAlertDialog.setMessage("login first");
						myAlertDialog.setPositiveButton("Login",
						            new DialogInterface.OnClickListener() {
						                public void onClick(DialogInterface arg0, int arg1) {
						                	thr.setCurrentTab(4);		        			
						                }
						            });
						 myAlertDialog.show();
					}
					break;
				case 2:
					try
					{
						if(sharedPrefs.getString("ID", "").equals("LOGGED IN"))
						{
							Intent in=new Intent("com.modestoappln.UserDataProfile");
							startActivity(in);
						}
						else
						{
							AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(MainActivity.this);
							myAlertDialog.setTitle("Error");
							myAlertDialog.setMessage("login error");
							myAlertDialog.show();
						}
					}
					catch(NullPointerException nl)
					{
						AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(MainActivity.this);
						myAlertDialog.setTitle("Error");
						myAlertDialog.setMessage("login first");
						myAlertDialog.setPositiveButton("Login",
						            new DialogInterface.OnClickListener() {
						                public void onClick(DialogInterface arg0, int arg1) {
						                	thr.setCurrentTab(4);		        			
						                }
						            });
						 myAlertDialog.show();
					}
					break;
				case 3:
					try
					{
						if(sharedPrefs.getString("ID", "").equals("LOGGED IN"))
						{
							Intent in=new Intent("com.modestoappln.UserComments");
							in.putExtra("DataReviews", al_user_data);
		        			startActivity(in);
						}
						else
						{
							AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(MainActivity.this);
							myAlertDialog.setTitle("Error");
							myAlertDialog.setMessage("login error");
							myAlertDialog.show();
						}
					}
					catch(NullPointerException nl)
					{
						AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(MainActivity.this);
						myAlertDialog.setTitle("Error");
						myAlertDialog.setMessage("login first");
						myAlertDialog.setPositiveButton("Login",
						            new DialogInterface.OnClickListener() {
						                public void onClick(DialogInterface arg0, int arg1) {
						                	thr.setCurrentTab(4);		        			
						                }
						            });
						 myAlertDialog.show();
					}
					break;
				case 4:
					try
					{
						if(sharedPrefs.getString("ID", "").equals("LOGGED IN"))
						{
							sdSearch.close();
							thr.setCurrentTab(4);
						}
						else
						{
							sdSearch.close();
							thr.setCurrentTab(5);
						}
					}
					catch(NullPointerException nl)
					{
					}
					break;
				case 5:
					Intent in=new Intent("com.modestoappln.NearByMap");
					startActivity(in);
					break;
				case 6:
					try
					{
						if(sharedPrefs.getString("ID", "").equals("LOGGED IN"))
						{
							finishSession();
							Log.d("hello", "prefs cleared"+sharedPrefs.getString("ID", "").toString());
							sdSearch.close();
							listCom.setVisibility(View.INVISIBLE);
							restartTab();
						}
						else
						{
							AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(MainActivity.this);
							myAlertDialog.setTitle("Error");
							myAlertDialog.setMessage("login error");
							myAlertDialog.show();
						}
					}
					catch(NullPointerException nl)
					{
						AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(MainActivity.this);
						myAlertDialog.setTitle("Error");
						myAlertDialog.setMessage("login first");
						myAlertDialog.setPositiveButton("Login",
						            new DialogInterface.OnClickListener() {
						                public void onClick(DialogInterface arg0, int arg1) {
						                	thr.setCurrentTab(4);		        			
						                }
						            });
						 myAlertDialog.show();
					}
					break;
				}
			}
		});
		lst1.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Log.d("hello", "in listener on item click     ");
				switch(position)
				{
				case 0:
					try
					{
						if(sharedPrefs.getString("ID", "").equals("LOGGED IN"))
						{
							Log.d("hello", "OBJECTS for login : "+sharedPrefs.getString("USERNAME", ""));Log.d("hello", "OBJECTS for login : "+sharedPrefs.getString("USERNAME", ""));		
							Intent intent = new Intent("com.modestoappln.MobileCon");
							startActivity(intent);
							
						}
						else
						{
							AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(MainActivity.this);
							myAlertDialog.setTitle("Error");
							myAlertDialog.setMessage("login or server error");
							myAlertDialog.show();
						}
					}
					catch(NullPointerException nl)
					{
						AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(MainActivity.this);
						myAlertDialog.setTitle("Error");
						myAlertDialog.setMessage("login first");
						myAlertDialog.setPositiveButton("Login",
						            new DialogInterface.OnClickListener() {
						                public void onClick(DialogInterface arg0, int arg1) {
						                	thr.setCurrentTab(4);		        			
						                }
						            });
						 myAlertDialog.show();
					}
					break;
				}
			}
		});
		final Handler mhandle=new Handler();
		final Runnable irun=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				slideMarquee();
			}
		};
			int delay=1000;
			int period=4000;
			Timer tr=new Timer();
			tr.scheduleAtFixedRate(new TimerTask(){
				public void run(){
					mhandle.post(irun);
				}
			}, delay, period);
	}
	public void onClick(){
		finish();
		android.os.Process.killProcess(android.os.Process.myPid());
	}
	
	private void slideMarquee() {
				// TODO Auto-generated method stub
		
		iv.setImageBitmap(decodeSampledBitmapFromResource(mark[num%mark.length],100,100));
		num++;
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater mi=getMenuInflater();
		mi.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	public void decoderImages(){
		ibglo1.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.one650,150,150));
		ibglo2.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.two650,150,150));
		ibglo3.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.three650,100,100));
		ibglo4.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.four650,100,100));
		ibdin1.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.waterside1,100,100));
		ibdin2.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.waterside2,100,100));
		ibdin3.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.waterside3,100,100));
		ibdin4.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.waterside4,100,100));
		ibthet1.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.pvr1,100,100));
		ibthet2.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.pvr2,100,100));
		ibthet3.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.pvr3,100,100));
		ibthet4.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.pvr4,100,100));
		ibzen1.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.zen1,150,150));
		ibzen2.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.zen2,150,150));
		ibzen3.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.zen3,100,100));
		ibzen4.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.zen4,100,100));
	}
	
	@Override
	public void onDrawerOpened() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(data != null)
		{
		switch(requestCode) {
		case 0:
		    if(resultCode == RESULT_OK){  
		    	Bundle extra=data.getExtras();
				bmp=(Bitmap) extra.get("data");
				b3.setVisibility(View.VISIBLE);
				b4.setVisibility(View.VISIBLE);
				
				switch(random)
				{
				case 1:
					iv1.setImageBitmap(bmp);
					break;
				case 2:
					iv2.setImageBitmap(bmp);
					break;
				case 3:
					iv3.setImageBitmap(bmp);
					break;
				}
		    }

		break; 
		case 1:
		    if(resultCode == RESULT_OK){  
		        selectedImage = data.getData();
		        String[] filePath = { MediaStore.Images.Media.DATA };
	            Cursor cursor = getContentResolver().query(selectedImage, filePath, null, null, null);
	            cursor.moveToFirst();
	            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
	            b3.setVisibility(View.VISIBLE);
				b4.setVisibility(View.VISIBLE);
				
				options.inJustDecodeBounds = true;
				BitmapFactory.decodeFile(imagePath, options);
				imageHeight = options.outHeight;
				imageWidth = options.outWidth;
				imageType = options.outMimeType;
				switch(random)
				{
				case 1:
					iv1.setImageBitmap(decodeSampledBitmapFromFile(imagePath, 100, 100));
					break;
				case 2:
					iv2.setImageBitmap(decodeSampledBitmapFromFile(imagePath, 100, 100));
					break;
				case 3:
					iv3.setImageBitmap(decodeSampledBitmapFromFile(imagePath, 100, 100));
					break;
				}
				cursor.close();
		    }
		break;
	}
		
		random=0;
		}
	}
	
	public void onTabChanged(String tabId)
	{
		sd.close();
		Log.d("hii", tabId);
		currentTab=thr.getCurrentTab();
		
		
		if(tabId.equals("tab1"))
		{
			Log.d("hi again", "executing if in on touch");
			b1.setVisibility(View.VISIBLE);
			b1.setClickable(true);
		}
		else
		{
			b1.setVisibility(View.INVISIBLE);
			b1.setClickable(false);
		}
	}
	
	
	class MyGestureDetector extends SimpleOnGestureListener
	{
		private static final int SWIPE_THRESHOLD_VELOCITY = 100;
		private int maxTabs;
 
		public MyGestureDetector()
		{
			maxTabs = thr.getTabContentView().getChildCount();
		}
 
		public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY)
		{	
			Log.d("hello", "hhh     OnFLING    "+"hhh");
			
			if (event1.getY() - event2.getY() > 10.00 && event1.getY() - event2.getY() < -10.00)
			{
				Log.d("hello", "hhh     OnFLING    IF Y AXIS   "+"hhh");
				return false;
			}
			if (event1.getX() - event2.getX() > 60.00 && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)
			{
				newTab = currentTab + 1;
				if(newTab>0 && newTab<4)
					hs.smoothScrollBy((thr.getTabWidget().getWidth()/6), 0);
				else if(newTab==4)
					hs.smoothScrollBy(hs.getWidth(), 0);
				Log.d("hello", "hhh"+event1.getX()+"hhh   "+(event1.getX() - event2.getX())+"    next   "+event2.getX()+"    "+currentTab+"hhh");
				if (newTab < 0 || newTab > (maxTabs - 1))
				{
					return false;
				}
			}
			else if (event1.getX() - event2.getX() < -60.00
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)
			{
				newTab = currentTab - 1;
				if(newTab>1 && newTab<5)
					hs.smoothScrollBy(-(thr.getTabWidget().getWidth()/6), 0);
				else if(newTab==1)
					hs.smoothScrollBy(-(hs.getWidth()), 0);
				Log.d("hello", "hhh"+event1.getX()+"hhh    "+(event1.getX() - event2.getX())+"    previous"+currentTab+"hhh");
				if (newTab < 0 || newTab > (maxTabs - 1))
				{
					return false;
				}
			}
			thr.setCurrentTab(newTab);
			currentTab=newTab;
			
			return super.onFling(event1, event2, velocityX, velocityY);
		}
	}
	
	 public void startDialog() {
		    AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
		    myAlertDialog.setTitle("Upload Pictures Option");
		    myAlertDialog.setMessage("How do you want to set your picture?");

		    myAlertDialog.setPositiveButton("Camera",
		            new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface arg0, int arg1) {
		                	i=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		        			startActivityForResult(i, 0);		        			
		                }
		            });

		    myAlertDialog.setNegativeButton("Gallery",
		            new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface arg0, int arg1) {
		                	j=new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		        			startActivityForResult(j, 1);
		                }
		            });
		    myAlertDialog.show();
		}
	 	
	 public static int calculateInSampleSize(
	            BitmapFactory.Options options, int reqWidth, int reqHeight) {
	    
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    int inSampleSize = 1;

	    if (height > reqHeight || width > reqWidth) {

	        final int halfHeight = height / 2;
	        final int halfWidth = width / 2;

	    
	        while ((halfHeight / inSampleSize) > reqHeight
	                && (halfWidth / inSampleSize) > reqWidth) {
	            inSampleSize *= 2;
	        }
	    }

	    return inSampleSize;
	}

	 public static Bitmap decodeSampledBitmapFromFile(String path,
		        int reqWidth, int reqHeight) {

		
		    final BitmapFactory.Options options = new BitmapFactory.Options();
		    options.inJustDecodeBounds = true;
		    BitmapFactory.decodeFile(path, options);

		
		    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

		
		    options.inJustDecodeBounds = false;
		    return BitmapFactory.decodeFile(path, options);
		}
	 
	 public Bitmap decodeSampledBitmapFromResource(int draw,
		        int reqWidth, int reqHeight) {
		 	
		
		    final BitmapFactory.Options options = new BitmapFactory.Options();
		    options.inJustDecodeBounds = true;
		    BitmapFactory.decodeResource(getResources(), draw, options);

		
		    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

		
		    options.inJustDecodeBounds = false;
		    return BitmapFactory.decodeResource(getResources(), draw, options);
		}
	 
	 public void startSession(){
		    //settings = getSharedPreferences(LOGN_PREF, 0);
		    SharedPreferences.Editor editor = sharedPrefs.edit();
		    editor.putString("USERNAME", uName.getText().toString());
		    editor.putString("PASWORD", uPass.getText().toString());
		    editor.putString("ID", "LOGGED IN");
		    editor.commit();

		}
	 
	 public void finishSession(){
		    //settings = getSharedPreferences(LOGN_PREF, 0);
		    SharedPreferences.Editor editor = sharedPrefs.edit();
		    editor.putString("USERNAME", "");
		    editor.putString("PASWORD", "");
		    editor.putString("ID", "");
		    editor.commit();

		}
	 
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		String selected_string=selection2[arg2];
		Log.d("hello", "in listener on item click     ");
		if(selected_string.equals("Make Your Own Recipe"))
		{
			random=1;
			startDialog();
			
		}
		if(selected_string.equals("Take a Snap"))
		{
			random=2;
			startDialog();
		}
		if(selected_string.equals("Choose Your DP"))
		{
			random=3;
			startDialog();
		}
	}
	
	public void removeTab(){
		thr.setCurrentTab(0);
	    thr.clearAllTabs();  // clear all tabs from the tabhost
	    for(TabHost.TabSpec spec : tabHostSpecs) // add all that you remember back
	        thr.addTab(spec);
	    thr.setCurrentTabByTag("tab1");
	}
	
	public void restartTab(){
		thr.setCurrentTab(0);
	    thr.clearAllTabs();  // clear all tabs from the tabhost
	    for(TabHost.TabSpec spec : tabHostRestartSpecs) // add all that you remember back
	        thr.addTab(spec);
	    thr.setCurrentTabByTag("tab5");
	}
	
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		Log.d("hiiii", "executing on touch");
		
		if (gestureDetector.onTouchEvent(event))
		{
			Log.d("hello", "hhh     OnTOUCH IF    "+"hhh");
			return false;
		}
		else
		{
			Log.d("hello", "hhh     OnTOUCH ELSE    "+"hhh");
			return true;
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.Loginsignupbtn3 :
			Intent in=new Intent("com.modestoappln.Signup");
			startActivity(in);
			break;
		case R.id.imgRes1 :
			Intent inr=new Intent("com.modestoappln.Restaurant");
			startActivity(inr);
			break;
		case R.id.imgDining1 :
			Intent ind=new Intent("com.modestoappln.Dining");
			startActivity(ind);
			break;
		case R.id.imgHangout1 :
			Intent inh=new Intent("com.modestoappln.Hangout");
			startActivity(inh);
			break;
		case R.id.imgTheatre1 :
			Intent inth=new Intent("com.modestoappln.Theatre");
			startActivity(inth);
			break;
		case R.id.Btnfacebook :
			Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.facebook.com"));
            startActivity(browserIntent);
            break;
		case R.id.Btngoogle :
			Intent browserIntent1 = new Intent(Intent.ACTION_VIEW,Uri.parse("http://plus.google.com"));
            startActivity(browserIntent1);
            break;
		case R.id.Btntwitter :
			Intent browserIntent2 = new Intent(Intent.ACTION_VIEW,Uri.parse("https://twitter.com"));
            startActivity(browserIntent2);
            break;
		case R.id.btnSearchCity :
			Intent int1 = new Intent("com.modestoappln.NearByMap");
            startActivity(int1);
            break;
		case R.id.btnSearchCommunity :
			thr.setCurrentTab(3);
			break;
		case R.id.btnSearchInterest :
			thr.setCurrentTab(5);
			break;
		case R.id.btnSearchlogin :
			thr.setCurrentTab(4);
			break;
		case R.id.btnSearchComment :
			thr.setCurrentTab(2);
			break;
		case R.id.btnSearchReviews :
			thr.setCurrentTab(2);
            break;
		case R.id.btnSearchGroupReviews :
			thr.setCurrentTab(3);
            break;
		case R.id.btnSearchContact :
			sd.open();
			break;
		case R.id.ReviewsGlobLay1 :
			Intent inrev=new Intent("com.modestoappln.Dining");
			startActivity(inrev);
			break;
		case R.id.ReviewsGlobLay2 :
			Intent inre=new Intent("com.modestoappln.Dining");
			startActivity(inre);
			break;
		case R.id.ReviewsGlobLay3 :
			Intent inres=new Intent("com.modestoappln.Restaurant");
			startActivity(inres);
			break;
		case R.id.ReviewsGlobLay4 :
			Intent inrest=new Intent("com.modestoappln.Restaurant");
			startActivity(inrest);
			break;
		case R.id.ReviewsGlobLay5 :
			Intent inthe=new Intent("com.modestoappln.Theatre");
			startActivity(inthe);
			break;
		case R.id.ReviewsGlobLay6 :
			Intent inthea=new Intent("com.modestoappln.Theatre");
			startActivity(inthea);
			break;
		case R.id.ReviewsGlobLay7 :
			Intent inha=new Intent("com.modestoappln.Hangout");
			startActivity(inha);
			break;
		case R.id.ReviewsGlobLay8 :
			Intent inhan=new Intent("com.modestoappln.Hangout");
			startActivity(inhan);
			break;
		case R.id.Homebtn2 :
			thr.setCurrentTabByTag("tab1");
			break;
		case R.id.Loginsignupbutton2 :
			Log.d("hello", "inside Listener");
			if(!uName.getText().toString().isEmpty())
				if(!uPass.getText().toString().isEmpty())
				{
					loginflag=dao.chkLogin(uName.getText().toString(),uPass.getText().toString());
					if(loginflag)
					{
					AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
					myAlertDialog.setTitle("Login");
					myAlertDialog.setMessage("Go On!!");
					myAlertDialog.show();
					startSession();
					loginCheck();					
					b10.setVisibility(View.INVISIBLE);
					b10.setClickable(false);
					android.view.ViewGroup.LayoutParams lp=b6.getLayoutParams();
					lp.width=android.view.ViewGroup.LayoutParams.MATCH_PARENT;
					Log.d("hello", "OBJECTS are" +sharedPrefs.toString());
					}
					else
					{
						AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
						myAlertDialog.setTitle("Login");
						myAlertDialog.setMessage("Error for password or username!!");
						myAlertDialog.show();
							
					}
				}
				else
				{
					AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
					myAlertDialog.setTitle("Login");
					myAlertDialog.setMessage("password empty!!");
					myAlertDialog.show();
					
				}
			else
			{
				AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
				myAlertDialog.setTitle("Login");
				myAlertDialog.setMessage("username empty!!");
				myAlertDialog.show();
			}
			break;
        }
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		Log.d("hello", "in listener on selected     ");
		String selected_string1=items[arg2];
		
		if(selected_string1.equals("Restaurant"))
		{
			Intent in=new Intent("com.modestoappln.Restaurant");
			startActivity(in);
		}
		if(selected_string1.equals("Theatres"))
		{
			Intent in=new Intent("com.modestoappln.Theatre");
			startActivity(in);
		}
		if(selected_string1.equals("Dining"))
		{
			Intent in=new Intent("com.modestoappln.Dining");
			startActivity(in);
		}
		if(selected_string1.equals("Hangout Places"))
		{
			Intent in=new Intent("com.modestoappln.Hangout");
			startActivity(in);
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}