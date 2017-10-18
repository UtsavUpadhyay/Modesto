package com.modestoappln;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 
import org.json.JSONObject;
 
import android.app.Dialog;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
 
public class SiteMap extends FragmentActivity implements LocationListener,OnMarkerClickListener,OnClickListener{
 
    GoogleMap gm;
    Button btnReview;
    ArrayList<String> intentData=new ArrayList<String>();   
    Button mapTypeN,mapTypeS,mapTypeT,mapTypeH;
    ArrayList<String> intentData1=new ArrayList<String>();   
    
    String address,addressurl,place;
    double latitude=0;
    double longitude=0;
    String type="";
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitemap);
        initialize();
        
 
    }
 
    private void initialize() {
		// TODO Auto-generated method stub
    	mapTypeN=(Button) findViewById(R.id.dropdownmenusitemapLinearBtn1);
        mapTypeT=(Button) findViewById(R.id.dropdownmenusitemapLinearBtn2);
        mapTypeS=(Button) findViewById(R.id.dropdownmenusitemapLinearBtn3);
        mapTypeH=(Button) findViewById(R.id.dropdownmenusitemapLinearBtn4);
        mapTypeH.setOnClickListener(this);
        mapTypeN.setOnClickListener(this);
        mapTypeT.setOnClickListener(this);
        mapTypeS.setOnClickListener(this);
        Intent in=getIntent();
        intentData=in.getStringArrayListExtra("data");
        place=intentData.get(0);
        address=intentData.get(1);
        type=intentData.get(2);
        addressurl=address.replace(" ", "+");
        btnReview = ( Button ) findViewById(R.id.btn_sitemapReview);
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
        if(status!=ConnectionResult.SUCCESS){
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();
        }else {
            SupportMapFragment fragment = ( SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.sitemap);
            gm = fragment.getMap();
            gm.setMyLocationEnabled(true);
            gm.getUiSettings().setCompassEnabled(true);
            gm.setTrafficEnabled(true);
            gm.getUiSettings().setMapToolbarEnabled(true);
            gm.getUiSettings().setZoomControlsEnabled(true);
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, true);
            Location location = locationManager.getLastKnownLocation(provider);
            if(location!=null){
                onLocationChanged(location);
            }
            StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/geocode/json?");
            sb.append("address="+addressurl);
            sb.append("&key=AIzaSyA2KZVWxq_ntOtdG_J-vy1gl5cI8pJxjDs");
            Log.d("hello", "string url is: "+sb.toString());
            PlacesTask placesTask = new PlacesTask();
            placesTask.execute(sb.toString());
            locationManager.requestLocationUpdates(provider, 20000, 0, this);
            btnReview.setOnClickListener(new OnClickListener() {
 
                @Override
                public void onClick(View v) {
                	intentData1.clear();
                	intentData1.add(place);
					intentData1.add(type);
					Intent in=new Intent("com.modestoappln.StaticReview");
					in.putStringArrayListExtra("data", intentData1);
					startActivity(in);
                    }
            });
 
        }
	}

	private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
 
            urlConnection.connect();
            iStream = urlConnection.getInputStream();
 
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
 
            StringBuffer sb  = new StringBuffer();
 
            String line = "";
            while( ( line = br.readLine())  != null){
                sb.append(line);
            }
 
            data = sb.toString();
 
            br.close();
 
        }catch(Exception e){
            Log.d("Exception while downloading url", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
 
        return data;
    }
 
    private class PlacesTask extends AsyncTask<String, Integer, String>{
 
        String data = null;
 
        @Override
        protected String doInBackground(String... url) {
            try{
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }
 
        @Override
        protected void onPostExecute(String result){
            ParserTask parserTask = new ParserTask();
 
            parserTask.execute(result);
        }
 
    }
 
    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String,String>>>{
 
        JSONObject jObject;
 
        @Override
        protected List<HashMap<String,String>> doInBackground(String... jsonData) {
 
            List<HashMap<String, String>> places = null;
            ReviewJSONParser reviewJsonParser = new ReviewJSONParser();
 
            try{
                jObject = new JSONObject(jsonData[0]);
 
                places = reviewJsonParser.parse(jObject);
 
            }catch(Exception e){
                Log.d("Exception",e.toString());
            }
            return places;
        }
 
        @Override
        protected void onPostExecute(List<HashMap<String,String>> list){
 
            gm.clear();
            Log.d("hello", "on post execute");
            Log.d("hello", "on post execute "+list.size()+"hffgg   "+list.toString());
            for(int i=0;i<list.size();i++){
            	
                MarkerOptions markerOptions = new MarkerOptions();
                
                MarkerOptions mo = new MarkerOptions();
                
                HashMap<String, String> hmPlace = list.get(i);
 
                double lat = Double.parseDouble(hmPlace.get("lat"));
 
                double lng = Double.parseDouble(hmPlace.get("lng"));
 
                LatLng latLng = new LatLng(lat, lng);
 
                markerOptions.position(latLng);
                mo.position(latLng);
                
                markerOptions.anchor(0.25f, 0.34f);
                mo.anchor(0.12f, 0.35f);
                
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
                mo.icon(BitmapDescriptorFactory.fromResource(R.drawable.chart));
                
                markerOptions.title(place + ":" + address +":"+ lat +":"+ lng +":"+ type);
                
                
                gm.addMarker(mo);
                gm.addMarker(markerOptions);
                
            }
        }
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
 
    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
 
        gm.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        gm.animateCamera(CameraUpdateFactory.zoomTo(12));
    }
 
   @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }
 
    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }
 
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

	@Override
	public boolean onMarkerClick(Marker marker) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.dropdownmenuNearbyMapLinearBtn1 :
          	gm.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				break;
		case R.id.dropdownmenuNearbyMapLinearBtn2 :	
          	gm.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
				break;
		case R.id.dropdownmenuNearbyMapLinearBtn3 :
          	gm.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
				break;
		case R.id.dropdownmenuNearbyMapLinearBtn4 :
          	gm.setMapType(GoogleMap.MAP_TYPE_HYBRID);
				break;
		}
	}
}