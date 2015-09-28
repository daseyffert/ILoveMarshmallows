package com.daseyffert.zappos3;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    Button searchButton;
    TextView searchItem;
    RequestQueue requestQueue;
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize searchItem TextView in mainActivity along with method to search upon pressing enter
        searchItem = (TextView) findViewById(R.id.activity_main_search_item);
        searchItem.setOnEditorActionListener(enterListener);

        //test = (TextView) findViewById(R.id.activity_main_test_text_view);
        searchButton= (Button) findViewById(R.id.activity_main_search_button);
        requestQueue = Volley.newRequestQueue(this);

        final RecyclerView recVList = (RecyclerView) findViewById(R.id.cardList);
        recVList.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this);


        //actions performed after searchItem has been selected
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //get text from search textView and add it to end of url
                Editable itemSearched = (Editable) searchItem.getText();
                String url = "https://zappos.amazon.com/mobileapi/v1/search?term=" + itemSearched;

                //Json request to get objects from array
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,

                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                List<ProductInfo> resultList = new ArrayList<ProductInfo>();
                                try {
                                    JSONArray jsonArray = response.getJSONArray("results");

                                    //got through each object and assaing the value of the object to data model then add that model to the list
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject result = jsonArray.getJSONObject(i);
                                        ProductInfo productInfo = new ProductInfo();

                                        productInfo.brandName = result.getString("brandName");
                                        productInfo.productName = result.getString("productName");
                                        productInfo.originalPrice = result.getString("originalPrice");
                                        productInfo.price = result.getString("price");
                                        productInfo.productRating = result.getString("productRating");
                                        productInfo.asin = result.getString("asin");
                                        productInfo.imageURL = result.getString("imageUrl");

                                        resultList.add(productInfo);


                                    }
                                    //create linearlayout managers for recycler views
                                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                                    recVList.setLayoutManager(llm);
                                    //send the result list to the adapters so they can make the recycler view layouts
                                    ContactAdapter adapter = new ContactAdapter(resultList);
                                    recVList.setAdapter(adapter);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("VOLLEY", "ERROR");
                            }
                        }
                );
                requestQueue.add(jsonObjectRequest);
            }
        });

    }

//PURPOSE: execute searchButton by pressing enter in editText View
    private TextView.OnEditorActionListener enterListener = new TextView.OnEditorActionListener(){
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN) {
                searchButton.callOnClick();
            }
            return true;
        }
    };

}