package com.modestoappln;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Start_Page extends Activity {
	MediaPlayer mp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_page);
		mp=MediaPlayer.create(Start_Page.this, R.raw.vo);
		mp.start();
		Thread th=new Thread()
		{
			@Override
			public void run() {
				try{
					sleep(5000);
				}catch(Exception e)
				{
					e.printStackTrace();
				}finally
				{
					Intent in=new Intent("com.modestoappln.MainActivity");
					startActivity(in);
				}
			}
		};
		th.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mp.stop();
		finish();
	}

}
