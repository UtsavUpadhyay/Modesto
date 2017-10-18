package com.modestoappln;



import java.net.Socket;
import java.util.ArrayList;

import com.DaoOBJ.Daoaccess;

import bean.Userbean;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Signup extends Activity implements OnClickListener{
	
	ArrayList al=new ArrayList();
	Button gbtn,fbtn,tbtn;
	Daoaccess dao= new Daoaccess();
	String imageType;
	int imageHeight,imageWidth; 
	BitmapFactory.Options options = new BitmapFactory.Options();
	final static int camera_data=0;
	Uri selectedImage;
	Intent i,j;
	Userbean ub;
	Socket client;
	Bitmap bmp;
	EditText etuser1,etuser2,etuser3,etuser4,etuser5,etuser6;
	Button btnuserSubmit;
	ImageButton ibuserSignup;
	int k=1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		initialize();
		btnuserSubmit.setOnClickListener(this);
		getPara();
		
	}

	private void getPara() {
		// TODO Auto-generated method stub
		
	}

	private void initialize() {
		// TODO Auto-generated method stub
		etuser1=(EditText) findViewById(R.id.SignupLoginUsereditText1);
		etuser2=(EditText) findViewById(R.id.SignupLoginUsereditText2);
		etuser3=(EditText) findViewById(R.id.SignupLoginUsereditText3);
		etuser4=(EditText) findViewById(R.id.SignupLoginUsereditText4);
		etuser5=(EditText) findViewById(R.id.SignupLoginUsereditText5);
		etuser6=(EditText) findViewById(R.id.SignupLoginUsereditText6);
		btnuserSubmit=(Button) findViewById(R.id.SignupUserSubmiitBtn1);
		ibuserSignup=(ImageButton) findViewById(R.id.imageViewUserSignup1);
		gbtn=(Button) findViewById(R.id.Btngoogle);
		tbtn=(Button) findViewById(R.id.Btntwitter);
		fbtn=(Button) findViewById(R.id.Btnfacebook);
		ibuserSignup.setOnClickListener(this);
		gbtn.setClickable(true);
		tbtn.setClickable(true);
		fbtn.setClickable(true);
		gbtn.setOnClickListener(this);
		tbtn.setOnClickListener(this);
		fbtn.setOnClickListener(this);
		ub=new Userbean();
		dao=new Daoaccess();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.signup, menu);
		return true;
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
				
					ibuserSignup.setImageBitmap(bmp);
		    }

		break; 
		case 1:
		    if(resultCode == RESULT_OK){  
		        selectedImage = data.getData();
		        String[] filePath = { MediaStore.Images.Media.DATA };
	            Cursor cursor = getContentResolver().query(selectedImage, filePath, null, null, null);
	            cursor.moveToFirst();
	            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
	            options.inJustDecodeBounds = true;
				BitmapFactory.decodeFile(imagePath, options);
				imageHeight = options.outHeight;
				imageWidth = options.outWidth;
				imageType = options.outMimeType;
				ibuserSignup.setImageBitmap(decodeSampledBitmapFromFile(imagePath, 100, 100));
				cursor.close();
		    }
		break;
		}
		
		}

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
	 
	 public void submitDialog() {
		    AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
		    myAlertDialog.setTitle("Successfully Registered!");
		    myAlertDialog.setMessage("you will be redirected on login page or Continue registration");

		    myAlertDialog.setPositiveButton("Login",
		            new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface arg0, int arg1) {
		                	i=new Intent("com.modestoappln.MainActivity");
		        			startActivity(i);		        			
		                }
		            });

		    myAlertDialog.setNegativeButton("Signup",
		            new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface arg0, int arg1) {
		                	j=new Intent("com.modestoappln.Signup");
		        			startActivity(j);
		                }
		            });
		    myAlertDialog.show();
		}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.SignupUserSubmiitBtn1 :
			int iLogin;
			Log.d("hello", "in click methid");
			if(!etuser1.getText().toString().isEmpty() && !etuser2.getText().toString().isEmpty())
			{
			Log.d("username", "is "+etuser1.getText().toString());
			ub.setUsername(etuser1.getText().toString());
			ub.setPassword(etuser2.getText().toString());
			ub.setName(etuser3.getText().toString());
			ub.setEmail(etuser4.getText().toString());
			ub.setUser_contact(etuser5.getText().toString());
			ub.setAddress(etuser6.getText().toString());
			if(bmp!=null)
			ub.setDisplay(bmp);
			iLogin=dao.connectIP(ub,k);
			Log.d("hello", "Variable got is" +iLogin);
			if(iLogin==2)
				{
				AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
				myAlertDialog.setTitle("Error");
				myAlertDialog.setMessage("username is used once choose another!");
				myAlertDialog.show();
				}
			else
			submitDialog();
			}
			else
			{
				 	AlertDialog.Builder myalertDialog = new AlertDialog.Builder(this);
				    myalertDialog.setTitle("Error");
				    myalertDialog.setMessage("username or password is empty!");
				    myalertDialog.show();
			}
			break;
		case R.id.imageViewUserSignup1 :
			startDialog();
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
		}
	}
}