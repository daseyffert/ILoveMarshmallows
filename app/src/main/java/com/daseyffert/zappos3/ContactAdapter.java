package com.daseyffert.zappos3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<ProductInfo> productList;
    private ImageLoader imageLoader;

    public ContactAdapter(List<ProductInfo> contactList) {
        this.productList = contactList;
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder contactViewHolder, int i) {
        ProductInfo pi = productList.get(i);
        contactViewHolder.brandName.setText(pi.brandName);
        contactViewHolder.productName.setText(pi.productName);
        if(pi.originalPrice == "null")
            contactViewHolder.originalPrice.setText("Original Price: not Available");
        else
            contactViewHolder.originalPrice.setText("Original Price: " + pi.originalPrice);

        contactViewHolder.price.setText("Our Price: " + pi.price);
        contactViewHolder.productRating.setText(pi.productRating + "/5.0");

        //contactViewHolder.productRating.setText(pi.productRating + "/5.0");
        contactViewHolder.asin.setText(pi.asin);

        Picasso.with(contactViewHolder.imageUrl.getContext()).load(pi.imageURL).into(contactViewHolder.imageUrl);
        //      contactViewHolder.imageUrl.setImageBitmap(pi.bitmap);
        // example   contactViewHolder.vTitle.setText(ci.name + " " + ci.surname);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        protected TextView brandName;
        protected TextView productName;
        protected TextView originalPrice;
        protected TextView price;
        protected TextView productRating;
        protected TextView asin;
        protected ImageView imageUrl;

        public ContactViewHolder(View v) {
            super(v);
            brandName =  (TextView) v.findViewById(R.id.card_layout_brand_name);
            productName = (TextView)  v.findViewById(R.id.card_layout_product_name);
            originalPrice = (TextView)  v.findViewById(R.id.card_layout_original_price);
            price = (TextView) v.findViewById(R.id.card_layout_price);
            productRating = (TextView) v.findViewById(R.id.card_layout_product_rating);
            asin = (TextView) v.findViewById(R.id.card_layout_asin);
            imageUrl = (ImageView) v.findViewById(R.id.card_layout_image);
        }
    }



}