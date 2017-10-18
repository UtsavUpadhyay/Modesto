package utility;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;

public class PlayService {
	

	public static boolean isPlayServiceAvailable(Context context) {
		int isAvailable=GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
		switch(isAvailable)
		{
		case ConnectionResult.SUCCESS :
			Log.d("hello", "connected");
			break;
		case ConnectionResult.SERVICE_MISSING :
			Log.d("hello", "missing");
			break;
		case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED :
			Log.d("hello", "update");
			break;
		case ConnectionResult.SERVICE_INVALID :
			Log.d("hello", "not connected");
			break;
			default :
				Log.d("hello", "value is :"+isAvailable);
				break;
		}
		return false;

	}
}
