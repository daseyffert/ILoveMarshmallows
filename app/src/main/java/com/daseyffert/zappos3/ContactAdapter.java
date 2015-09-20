package com.daseyffert.zappos3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<ProductInfo> productList;

    public ContactAdapter(List<ProductInfo> contactList) {
        this.productList = contactList;
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        ProductInfo pi = productList.get(i);
        contactViewHolder.brandName.setText(pi.brandName);
        contactViewHolder.productName.setText(pi.productName);
        contactViewHolder.originalPrice.setText(pi.originalPrice);
        contactViewHolder.price.setText(pi.price);
        contactViewHolder.productRating.setText(pi.productRating);
        contactViewHolder.asin.setText(pi.asin);
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
}