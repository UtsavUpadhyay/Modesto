package com.modestoappln;



import java.util.ArrayList;

import com.DaoOBJ.Daoaccess;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class Dining extends Activity implements OnItemSelectedListener,OnClickListener{
	int i=0;
	Spinner dropdown;
	TabSpec t1,t2,t3;
	TabHost th;
	ImageView iv1,iv2,iv3;
	String[] items = new String[]{"Select","650-The Global Kitchen", "70 Degrees East", "Barbeque Nation","Nini Kitchen","Patang","Rajwadu","Souq Bistro & Grills"};
	TextView textView,textView1,textView2,textView3;
	ArrayAdapter<String> adapter_drdn; 
	boolean flag=false,flag1=false;
	int position;
	@SuppressWarnings("rawtypes")
	ArrayList al=new ArrayList();
	@SuppressWarnings("rawtypes")
	ArrayList al1=new ArrayList();
	boolean flag2=false;
	ArrayList<String> alIntent=new ArrayList<String>();
	Daoaccess dao=new Daoaccess();
	Button bMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hangout);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		startInitialize();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void startInitialize(){
		
		dropdown= (Spinner)findViewById(R.id.Restaurantspinnerexp);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
		dropdown.setAdapter(adapter);
		textView = (TextView) findViewById(R.id.RestaurantSecondtextView1);
		textView1 = (TextView) findViewById(R.id.RestaurantSecondtextView2);
		textView2 = (TextView) findViewById(R.id.RestaurantSecondtextView3);
		dropdown.setOnItemSelectedListener(this);
		iv1=(ImageView) findViewById(R.id.RestaurantSecondimgView1);
		textView3 = (TextView) findViewById(R.id.RestaurantSecondtextView4);
		bMap=(Button) findViewById(R.id.RestaurantSecondButton);
		bMap.setOnClickListener(this);
		textView.setClickable(true);
		textView.setOnClickListener(this);
	}
	
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
		position= dropdown.getSelectedItemPosition();
	    switch(position){
	    case 0:
	    	break;
        case 1:
        	al1=dao.connectRestaurant("650-The Global Kitchen","dining");
        	Log.d("hello", "after method completion "+al1);
        	textView.setText(Html.fromHtml("<b><u><font color='blue'>"+al1.get(0).toString()+"</font></u></b>"));
        	textView1.setText(Html.fromHtml("<b>Address : </b>"+al1.get(1).toString()));
        	textView2.setText(Html.fromHtml("<b>Cost for single person : </b>"+al1.get(3).toString()+"Rs."));
        	textView3.setText(Html.fromHtml("<b>Contact details : </b>"+al1.get(4).toString()));
        	iv1.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.globalit1,200, 200));
			
			flag2=true;
		    break;
        case 2:
        	al1=dao.connectRestaurant("70 Degrees East","dining");
        	textView.setText(Html.fromHtml("<b><u><font color='blue'>"+al1.get(0).toString()+"</font></u></b>"));
        	textView1.setText(Html.fromHtml("<b>Address : </b>"+al1.get(1).toString()));
        	textView2.setText(Html.fromHtml("<b>Cost for single person : </b>"+al1.get(3).toString()+"Rs."));
        	textView3.setText(Html.fromHtml("<b>Contact details : </b>"+al1.get(4).toString()));
        	iv1.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.eventyast,200,200));
        	
        	flag2=true;
        	break;
        case 3:
        	al1=dao.connectRestaurant("Barbeque Nation","dining");
        	textView.setText(Html.fromHtml("<b><u><font color='blue'>"+al1.get(0).toString()+"</font></u></b>"));
        	textView1.setText(Html.fromHtml("<b>Address : </b>"+al1.get(1).toString()));
        	textView2.setText(Html.fromHtml("<b>Cost for single person : </b>"+al1.get(3).toString()+"Rs."));
        	textView3.setText(Html.fromHtml("<b>Contact details : </b>"+al1.get(4).toString()));
        	iv1.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.barbq3,200,200));
        	
        	flag2=true;
        	break;
        case 4:
        	al1=dao.connectRestaurant("Nini Kitchen","dining");
        	textView.setText(Html.fromHtml("<b><u><font color='blue'>"+al1.get(0).toString()+"</font></u></b>"));
        	textView1.setText(Html.fromHtml("<b>Address : </b>"+al1.get(1).toString()));
        	textView2.setText(Html.fromHtml("<b>Cost for single person : </b>"+al1.get(3).toString()+"Rs."));
        	textView3.setText(Html.fromHtml("<b>Contact details : </b>"+al1.get(4).toString()));
        	iv1.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.ini2,200,200));
        	
        	flag2=true;
        	break;
        case 5:
        	al1=dao.connectRestaurant("Patang","dining");
        	textView.setText(Html.fromHtml("<b><u><font color='blue'>"+al1.get(0).toString()+"</font></u></b>"));
        	textView1.setText(Html.fromHtml("<b>Address : </b>"+al1.get(1).toString()));
        	textView2.setText(Html.fromHtml("<b>Cost for single person : </b>"+al1.get(3).toString()+"Rs."));
        	textView3.setText(Html.fromHtml("<b>Contact details : </b>"+al1.get(4).toString()));
        	iv1.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.patang,200,200));
        	
        	flag2=true;
        	break;
        case 6:
        	al1=dao.connectRestaurant("Rajwadu","dining");
        	textView.setText(Html.fromHtml("<b><u><font color='blue'>"+al1.get(0).toString()+"</font></u></b>"));
        	textView1.setText(Html.fromHtml("<b>Address : </b>"+al1.get(1).toString()));
        	textView2.setText(Html.fromHtml("<b>Cost for single person : </b>"+al1.get(3).toString()+"Rs."));
        	textView3.setText(Html.fromHtml("<b>Contact details : </b>"+al1.get(4).toString()));
        	iv1.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.ajwadu1,200,200));
        	
        	flag2=true;
        	break;
        case 7:
        	al1=dao.connectRestaurant("Souq Bistro & Grills","dining");
        	textView.setText(Html.fromHtml("<b><u><font color='blue'>"+al1.get(0).toString()+"</font></u></b>"));
        	textView1.setText(Html.fromHtml("<b>Address : </b>"+al1.get(1).toString()));
        	textView2.setText(Html.fromHtml("<b>Cost for single person : </b>"+al1.get(3).toString()+"Rs."));
        	textView3.setText(Html.fromHtml("<b>Contact details : </b>"+al1.get(4).toString()));
        	iv1.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.bisills1,200,200));
        	
        	flag2=true;
        	break;
        	}
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
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

	 @Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId())
			{
			case R.id.RestaurantSecondButton :
				Intent in=new Intent("com.modestoappln.NearByMap");
				startActivity(in);
				break;
			case R.id.RestaurantSecondtextView1 :
				if(flag2)
				{
				alIntent.clear();
				Intent inSite=new Intent("com.modestoappln.SiteMap");
				alIntent.add(al1.get(0).toString());
				alIntent.add(al1.get(1).toString());
				alIntent.add("dining");
				al1.clear();
				inSite.putStringArrayListExtra("data", alIntent);
				Log.d("hello", "intent passed"+alIntent);
				startActivity(inSite);
				}
			}
	 }
}
