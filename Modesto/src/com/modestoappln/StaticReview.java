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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

@SuppressLint("NewApi")
public class StaticReview extends Activity implements OnClickListener{

	Daoaccess dao=new Daoaccess();
	ArrayList snd=new ArrayList();
	ArrayList<String> intData=new ArrayList<String>();
	String type,place;
	int k=0;
	Intent i=new Intent();
	public static final String LOGN_PREF = "LoginPrefs";
	
	SharedPreferences sharedPrefs;
	EditText eReview,eComment;
	ImageView iv;
	RatingBar r2;
	Button b1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_static_review);
		Intent in=getIntent();
		intData=in.getStringArrayListExtra("data");
		sharedPrefs=getSharedPreferences(LOGN_PREF, 0);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		iv=(ImageView) findViewById(R.id.StaticReviewimageView1);
		iv.setImageBitmap(decodeSampledBitmapFromResource(R.drawable.food4, 200, 200));
		b1=(Button) findViewById(R.id.StaticReviewbutton1);
		r2=(RatingBar) findViewById(R.id.StaticratingBar1);
		eReview=(EditText) findViewById(R.id.StaticCommentSecondeditText1);
		eComment=(EditText) findViewById(R.id.StaticCommentSecondeditText2);
		place=intData.get(0);
		type=intData.get(1);
		b1.setOnClickListener(this);
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
		case R.id.StaticReviewbutton1 :
			Log.d("hello", "inside Listener Of Review  ");
			if(r2.getRating()>0.0f)
			{
			snd.clear();
			
			
			snd.add(place);
			snd.add(r2.getRating());
			snd.add(type);
			snd.add("rating");
			r2.setIsIndicator(true);
			Log.d("hello", "snd data array : "+snd.toString());
			dao.sendRating(snd);
			
			
			try
			{
				if(sharedPrefs.getString("ID", "").equals("LOGGED IN"))
				{
					snd.clear();
					Log.d("hello", "OBJECTS for login : "+sharedPrefs.getString("USERNAME", ""));
					
					snd.add(place);
					snd.add(sharedPrefs.getString("USERNAME", ""));
					snd.add(eReview.getText().toString());
					snd.add("comment");
					snd.add(eComment.getText().toString());
					snd.add(type);
					snd.add(r2.getRating());
					dao.sendRating(snd);
					b1.setClickable(false);
					b1.setText("done");
				}
				else
				{
					AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
					myAlertDialog.setTitle("Error");
					myAlertDialog.setMessage("login error");
					myAlertDialog.show();
				}
			}
			catch(NullPointerException nl)
			{
				AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
				myAlertDialog.setTitle("Error");
				myAlertDialog.setMessage("Ratings Submitted. for comment and review login first");
				myAlertDialog.setPositiveButton("Login",
				            new DialogInterface.OnClickListener() {
				                public void onClick(DialogInterface arg0, int arg1) {
				                	i=new Intent("com.modestoappln.MainActivity");
				        			startActivity(i);		        			
				                }
				            });
				 myAlertDialog.show();
			}
			}
			break;
			
		}
	}

}
