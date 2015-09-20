package com.daseyffert.zappos3;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Daniel on 9/19/2015.
 */
class ContactViewHolder extends RecyclerView.ViewHolder {
    protected TextView brandName;
    protected TextView productName;
    protected TextView originalPrice;
    protected TextView price;
    protected TextView productRating;
    protected TextView asin;

    public ContactViewHolder(View v) {
        super(v);
        brandName =  (TextView) v.findViewById(R.id.card_layout_brand_name);
        productName = (TextView)  v.findViewById(R.id.card_layout_product_name);
        originalPrice = (TextView)  v.findViewById(R.id.card_layout_original_price);
        price = (TextView) v.findViewById(R.id.card_layout_price);
        productRating = (TextView) v.findViewById(R.id.card_layout_product_rating);
        asin = (TextView) v.findViewById(R.id.card_layout_asin);
    }
}