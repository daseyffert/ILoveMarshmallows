package com.daseyffert.zappos3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.w3c.dom.Node;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Daniel on 9/19/2015.
 */

public class ProductInfo {

    protected String brandName;
    protected String productName;
    protected String originalPrice;
    protected String price;
    protected String productRating;
    protected String asin;
    protected String imageURL;
    protected Bitmap bitmap = getBitmapFromURL(imageURL) ;



    protected static final String BRAND_PREFIX = "Brand: ";
    protected static final String PRODUCT_PREFIX = "Product: ";
    protected static final String OPRICE_PREFIX = "Orig. Price: ";
    protected static final String PRICE_PREFIX = "Price: ";
    protected static final String RATING_PREFIX = "Rating: ";
    protected static final String ASIN_PREFIX = "Asin: ";



    public Bitmap getBitmapFromURL(String src){
        try{

            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductRating() {
        return productRating;
    }

    public void setProductRating(String productRating) {
        this.productRating = productRating;
    }
}