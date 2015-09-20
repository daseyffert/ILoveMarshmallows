package com.daseyffert.zappos3;

import android.app.Activity;
import android.app.DownloadManager;
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


        test = (TextView) findViewById(R.id.activity_main_test_text_view);
        searchButton= (Button) findViewById(R.id.activity_main_search_button);
        requestQueue = Volley.newRequestQueue(this);

        final RecyclerView recVList = (RecyclerView) findViewById(R.id.cardList);
        recVList.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this);


        //actions performed after searchItem has been selected
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Editable itemSearched = (Editable) searchItem.getText();
                String url = "https://zappos.amazon.com/mobileapi/v1/search?term=" + itemSearched;

                //llm.setOrientation(LinearLayoutManager.VERTICAL);
               // recVList.setLayoutManager(llm);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,

                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                List<ProductInfo> resultList = new ArrayList<ProductInfo>();
                                try {
                                    JSONArray jsonArray = response.getJSONArray("results");

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject result = jsonArray.getJSONObject(i);
                                        //resultList = new ArrayList<ProductInfo>();
                                        ProductInfo productInfo = new ProductInfo();


                                        productInfo.brandName = result.getString("brandName");
                                        productInfo.productName = result.getString("productName");
                                        productInfo.originalPrice = result.getString("originalPrice");
                                        productInfo.price = result.getString("price");
                                        productInfo.productRating = result.getString("productRating");
                                        productInfo.asin = result.getString("asin");


                                        resultList.add(productInfo);


                                        test.append(productInfo.brandName + "|" + productInfo.productName + "|" + productInfo.originalPrice + "|" + productInfo.price + "|" + productInfo.productRating + "|" + productInfo.asin + "\n");
                                    }
                                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                                    recVList.setLayoutManager(llm);

                                    ContactAdapter adapter = new ContactAdapter(resultList);
                                    recVList.setAdapter(adapter);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                //llm.setOrientation(LinearLayoutManager.VERTICAL);
                                //recVList.setLayoutManager(llm);

                               // ContactAdapter adapter = new ContactAdapter(resultList);
                                //recVList.setAdapter(adapter);

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
                //*************************************************************************************************************
//                llm.setOrientation(LinearLayoutManager.VERTICAL);
//                recVList.setLayoutManager(llm);
//
//
//
//                ContactAdapter adapter = new ContactAdapter(createList(30));
               // recVList.setAdapter(adapter);
            }
        });

    }



    private List<ProductInfo> createList(int size) {

        List<ProductInfo> result = new ArrayList<ProductInfo>();
        for (int i=1; i <= size; i++) {
            ProductInfo productInfo = new ProductInfo();
            productInfo.brandName = ProductInfo.BRAND_PREFIX + i;
            productInfo.productName = ProductInfo.PRODUCT_PREFIX + i;
            productInfo.originalPrice = ProductInfo.OPRICE_PREFIX + i;
            productInfo.price = ProductInfo.PRICE_PREFIX + i;
            productInfo.productRating = ProductInfo.RATING_PREFIX + i;
            productInfo.asin = ProductInfo.ASIN_PREFIX + i;

            result.add(productInfo);

        }

        return result;
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