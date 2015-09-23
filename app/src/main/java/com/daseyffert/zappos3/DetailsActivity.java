package com.daseyffert.zappos3;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 9/23/2015.
 */
public class DetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        Intent intent = getIntent();

//        new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//
//                List<ProductInfo> resultList = new ArrayList<ProductInfo>();
//                try {
//                    JSONArray jsonArray = response.getJSONArray("results");
//
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject result = jsonArray.getJSONObject(i);
//                        //resultList = new ArrayList<ProductInfo>();
//                        ProductInfo productInfo = new ProductInfo();
//
//
//                        productInfo.brandName = result.getString("brandName");
//                        productInfo.productName = result.getString("productName");
//                        productInfo.originalPrice = result.getString("originalPrice");
//                        productInfo.price = result.getString("price");
//                        productInfo.productRating = result.getString("productRating");
//                        productInfo.asin = result.getString("asin");
//                        productInfo.imageURL = result.getString("imageUrl");
//
//
//
//                        resultList.add(productInfo);
//
//
//                        test.append(productInfo.brandName + "|" + productInfo.productName + "|" + productInfo.originalPrice + "|" + productInfo.price + "|" + productInfo.productRating + "|" + productInfo.asin + "|" + productInfo.imageURL +"\n");
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e("VOLLEY", "ERROR");
//                    }
//                };


    }

}
