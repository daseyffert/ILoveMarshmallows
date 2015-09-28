package com.daseyffert.zappos3;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniel on 9/23/2015.
 */
public class DetailsActivity extends Activity {
    String url;
    RequestQueue requestQueue;

    String jsonString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        requestQueue = Volley.newRequestQueue(this);

        //get asin from recycler chosen then add to end of url to get product description
        Bundle extras = getIntent().getExtras();
        String addUrl = extras.getString("passAsin");
        url = "https://zappos.amazon.com/mobileapi/v1/product/asin/" + addUrl;


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,


                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            //This is where I had problems with reading the information from the json url
                            //My problem was I could only figure out how to read the information properly if
                            //it came in an array
                            //I only worked with one value while trying to get it to work but was unsuccessful
                            JSONObject testDescription = response;
                            testDescription.getString("description");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("Volley","ERROR");
                    }
                }


        );

        //assign the value into the text view but unsuccessfull
        TextView vProductDescription = (TextView) findViewById(R.id.product_details_description);
        if(jsonString!= null)
            vProductDescription.setText(jsonString);


        requestQueue.add(jsonObjectRequest);
    }
}
