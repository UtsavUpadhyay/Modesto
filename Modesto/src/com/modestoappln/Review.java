package com.modestoappln;

import java.util.ArrayList;

import com.DaoOBJ.Daoaccess;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

@SuppressLint("NewApi")
public class Review extends Activity implements OnClickListener{

	Daoaccess dao=new Daoaccess();
	ArrayList snd=new ArrayList();
	ArrayList<String> intData=new ArrayList<String>();
	String type,data;
	String[] allData={"","","","",""};
	int k=0;
	Intent i=new Intent();
	public static final String LOGN_PREF = "LoginPrefs";
	
	SharedPreferences sharedPrefs;
	EditText eReview,eComment;
	ImageView iv,iv1;
	TextView t1,t2,t3,t4;
	RatingBar r1,r2;
	TabHost thr;
	TabSpec tab1,tab2;
	Button b1,bComment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review);
		Intent in=getIntent();
		intData=in.getStringArrayListExtra("data");
		sharedPrefs=getSharedPreferences(LOGN_PREF, 0);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		iv=(ImageView) findViewById(R.id.ReviewimageView1);
		iv.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.food4, 200, 200));
		b1=(Button) findViewById(R.id.Reviewbutton1);
		iv1=(ImageView) findViewById(R.id.ReviewimageView2);
		t1=(TextView) findViewById(R.id.ReviewtextView1);
		t2=(TextView) findViewById(R.id.ReviewtextView2);
		r1=(RatingBar) findViewById(R.id.ratingBar1);
		r2=(RatingBar) findViewById(R.id.ratingBar2);
		t3=(TextView) findViewById(R.id.ReviewtextView6);
		t4=(TextView) findViewById(R.id.ReviewtextView4);
		thr=(TabHost) findViewById(R.id.tabhostReview);
		eReview=(EditText) findViewById(R.id.CommentSecondeditText1);
		eComment=(EditText) findViewById(R.id.CommentSecondeditText2);
		bComment=(Button) findViewById(R.id.CommentSecondbutton1);
		data=intData.get(0);
		type=intData.get(1);
		thr.setup();
		tab1=thr.newTabSpec("tab1");
		tab1.setContent(R.id.tab1);
		tab1.setIndicator("Ratings");
		thr.addTab(tab1);
		tab2=thr.newTabSpec("tab2");
		tab2.setContent(R.id.tab2);
		tab2.setIndicator("Comments");
		thr.addTab(tab2);
		b1.setOnClickListener(this);
		bComment.setOnClickListener(this);
		for(int i=0;i<=(data.length()-1);i++)
		{
			if(data.charAt(i)==':')
			{
				k++;
			}
			else
			{
				allData[k]=allData[k]+data.charAt(i);
			}
		}
		Log.d("hello", "data values are :  "+allData[0]+"   "+allData[1]+"   "+allData[2]+"   "+allData[3]+"   "+allData[4]);
		t1.setText(Html.fromHtml("<b><u><font color='blue'>"+allData[0]+"</font></u></b>"));
		t2.setText(Html.fromHtml("<b>"+allData[1]+"</b>"));
		if(Float.parseFloat(allData[2])!=0.0f)
		{
			Log.d("hello","ratings are :  "+Float.parseFloat(allData[2]));
			r1.setRating(Float.parseFloat(allData[2]));
			r1.setIsIndicator(true);
			t4.setText(Html.fromHtml("<b>"+Float.parseFloat(allData[2])+"</b>"));
		}
		else
			t3.setText("Sorry!!!");
		if(!allData[4].equals("-NA-"))
		{
		 StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo?");
         sb.append("maxwidth="+Integer.parseInt(allData[3]));
         sb.append("&photoreference="+allData[4]);
         sb.append("&key=AIzaSyCn_AxwoPxZDPLia9K1SlutN4uIuUVricM");
         ImageLoadTask il=(ImageLoadTask) new ImageLoadTask(sb.toString(), iv1).execute();
         
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.review, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
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
		case R.id.Reviewbutton1 :
			if(r2.getRating()>0.0f)
			{
			snd.clear();
			b1.setText("done");
			snd.add(allData[0]);
			snd.add(r2.getRating());
			snd.add(type);
			snd.add("rating");
			Log.d("hello", "snd data array : "+snd.toString());
			dao.sendRating(snd);
			b1.setClickable(false);
			}
			break;
		case R.id.CommentSecondbutton1 :
			Log.d("hello", "inside Listener Of Review  ");
			
			try
			{
				if(sharedPrefs.getString("ID", "").equals("LOGGED IN"))
				{
					snd.clear();
					Log.d("hello", "OBJECTS for login : "+sharedPrefs.getString("USERNAME", ""));
					snd.add(allData[0]);
					snd.add(sharedPrefs.getString("USERNAME", ""));
					snd.add(eReview.getText().toString());
					snd.add("review");
					snd.add(eComment.getText().toString());
					snd.add(type);
					dao.sendRating(snd);
					bComment.setVisibility(View.INVISIBLE);
					bComment.setClickable(false);
					AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
					myAlertDialog.setTitle("Success");
					myAlertDialog.setMessage("Review and comment submitted");
					myAlertDialog.show();					
				}
			}
			catch(NullPointerException nl)
			{
				AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
				myAlertDialog.setTitle("Error");
				myAlertDialog.setMessage("Login First");
				 myAlertDialog.setPositiveButton("Login",
				            new DialogInterface.OnClickListener() {
				                public void onClick(DialogInterface arg0, int arg1) {
				                	i=new Intent("com.modestoappln.MainActivity");
				        			startActivity(i);		        			
				                }
				            });
				 myAlertDialog.show();
			}
			break;
			
		}
	}

}
