package com.example.arroundme.maps;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PlaceTask extends AsyncTask<String,Integer,String> {
    @Override
    protected String doInBackground(String... strings) {
        String data = null;
        try {
            // Initialize data
             data = downloadUrl(strings[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        new ParserTask().execute(s);
    }

    public String downloadUrl(String string) throws IOException {
        // Initialize url
        URL url = new URL(string);
        // Initialize connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Connect connection
        connection.connect();
        // Initialize input stream
        InputStream stream = connection.getInputStream();
        // Initialize buffer reader
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        // Initialize string builder
        StringBuilder builder = new StringBuilder();
        // Initialize string variable
        String line = "";
        // Use while loop
        while((line = reader.readLine()) != null){
            // Append line
            builder.append(line);
        }
        // Get append data
        String data = builder.toString();
        // close reader
        reader.close();
        // return data
        return data;
    }
}
