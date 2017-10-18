package com.modestoappln;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
 
public class PlaceJSONParser {
 
    public List<HashMap<String,String>> parse(JSONObject jObject){
 
        JSONArray jPlaces = null;
        try {
            jPlaces = jObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getPlaces(jPlaces);
    }
 
    private List<HashMap<String, String>> getPlaces(JSONArray jPlaces){
        int placesCount = jPlaces.length();
        List<HashMap<String, String>> placesList = new ArrayList<HashMap<String,String>>();
        HashMap<String, String> place = null;
 
        for(int i=0; i<placesCount;i++){
            try {
                place = getPlace((JSONObject)jPlaces.get(i));
                placesList.add(place);
 
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
 
        return placesList;
    }
 
    private HashMap<String, String> getPlace(JSONObject jPlace){
 
        HashMap<String, String> place = new HashMap<String, String>();
        String placeName = "-NA-";
        String vicinity="-NA-";
        String latitude="";
        String longitude="";
        String photoRef="-NA-";
        String ratings="0";
        String width = "0";
        
        try {
            if(!jPlace.isNull("name")){
                placeName = jPlace.getString("name");
            }
 
            if(!jPlace.isNull("vicinity")){
                vicinity = jPlace.getString("vicinity");
            }

            latitude = jPlace.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = jPlace.getJSONObject("geometry").getJSONObject("location").getString("lng");
            Log.d("hello", "photoref begin : "+photoRef);
            if(jPlace.has("photos"))
            {
            photoRef=jPlace.getJSONArray("photos").getJSONObject(0).getString("photo_reference");
            Log.d("hello", "photoref : "+photoRef);
            	width=jPlace.getJSONArray("photos").getJSONObject(0).getString("width");
            	Log.d("hello", "width : "+width);
            }
            if(!jPlace.isNull("rating")){
            ratings=jPlace.getString("rating");
            Log.d("hello", "rating : "+ratings);
            }
            place.put("place_name", placeName);
            place.put("vicinity", vicinity);
            place.put("lat", latitude);
            place.put("lng", longitude);
            place.put("photo_ref", photoRef);
            place.put("width", width);
            place.put("rating", ratings);
 
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return place;
    }
}