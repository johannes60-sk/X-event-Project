package com.example.arroundme.maps;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class ParserTask extends AsyncTask<String,Integer, List<HashMap<String,String>>> {
    @Override
    protected List<HashMap<String, String>> doInBackground(String... strings) {
        // Create json parser class
        JsonParser jsonParser = new JsonParser();
        // Initialize hash map list
        List<HashMap<String, String>> mapList = null;
        JSONObject object = null;
        try {
            // Initialize json object
             object = new JSONObject(strings[0]);
             // Parse json object
            mapList = jsonParser.parseResult(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mapList;
    }

//    @Override
//    protected void onPostExecute(List<HashMap<String, String>> hashMaps) {
//        super.onPostExecute(hashMaps);
//        map.clear();
//    }
}
