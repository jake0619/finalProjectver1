package com.example.finalproject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class FetchBook extends AsyncTask<String, Void, String> {


    private fragment_feed mfragment_feed;
    private static final String LOG_TAG =
            FetchBook.class.getSimpleName();


    private ArrayList<Product> products = new ArrayList<>();

    FetchBook(fragment_feed fragment_feed) {
        this.mfragment_feed = fragment_feed;
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getBookInfo(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            //...
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("search_results");
            int i = 0;
            String title = null;
            String link = "";
            String imageLink = "";
            String price = "";
            //get up to 5 first results
            while (i < itemsArray.length() && (i < 10) ){
                // Get the current item information.
                JSONObject result = itemsArray.getJSONObject(i);

                // Try to get product details from json
                // catch if either field is empty and move on.
                try {
                    price = result.getJSONArray("prices").getJSONObject(0).getString("raw");
                    title = result.getString("title");
                    link = result.getString("link");
                    imageLink = result.getString("image");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //create product from json info
                Product currProduct = new Product(title, price, link, true, imageLink);
                Log.d(LOG_TAG, currProduct.getImageLink() );
                products.add(currProduct);
                // Move to the next item.
                i++;
            }
            Log.d(LOG_TAG, "Products size: "+products.size() );
            //update the fragment's
            mfragment_feed.updateData(products);
            //build products

        } catch (JSONException e) {
            // If onPostExecute does not receive a proper JSON string,
            // update the UI to show failed results.
            e.printStackTrace();
        }

    }

}
