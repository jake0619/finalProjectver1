package com.example.finalproject;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    private static final String BASE_URL =  "https://api.rainforestapi.com/request?";
    private static final String API_KEY = "api_key";
    private static final String TYPE = "type";
    private static final String AMAZON_DOMAIN = "amazon_domain";
    private static final String SEARCH_TERM = "search_term";


    private static final String LOG_TAG =
            NetworkUtils.class.getSimpleName();

    static String getBookInfo(String queryString){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;
        String search_term = queryString;

        try {
            //...
            //
            Uri builtURI = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter(API_KEY, "748A4ADF840041769E696F5125CC4836")
                    .appendQueryParameter(TYPE, "search")
                    .appendQueryParameter(AMAZON_DOMAIN, "amazon.com")
                    .appendQueryParameter(SEARCH_TERM, search_term)
                    .build();

            URL requestURL2 = new URL(builtURI.toString());

            urlConnection = (HttpURLConnection) requestURL2.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Get the InputStream.
            InputStream inputStream = urlConnection.getInputStream();

            // Create a buffered reader from that input stream.
            reader = new BufferedReader(new InputStreamReader(inputStream));

            // Use a StringBuilder to hold the incoming response.
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                // Since it's JSON, adding a newline isn't necessary (it won't
                // affect parsing) but it does make debugging a *lot* easier
                // if you print out the completed buffer for debugging.
                builder.append("\n");
            }
            bookJSONString = builder.toString();
            Log.d(LOG_TAG, bookJSONString);
            if (builder.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //...
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return bookJSONString;
    }
}
