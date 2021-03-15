package com.delivery.ves.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.delivery.ves.Models.SignUp.User;
import com.delivery.ves.Models.product.Product;
import com.google.gson.Gson;

import java.util.ArrayList;

public class PreferenceUtils {

    public PreferenceUtils() {
    }

    public static void SaveId(String id, Context c){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("id",id);
        editor.apply();
    }
    public static String getId(Context c){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(c);

        return sharedPreferences.getString("id",null);
    }

    public static void deleteId(Context c){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.remove("id");
        editor.apply();
    }

    public static void saveLastOrderId(String id,Context c)
    {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("lastOrderId",id);
        editor.apply();
    }

    public static String getLastOrderId(Context c){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(c);

        return sharedPreferences.getString("lastOrderId",null);
    }

    public static void saveUserData(User user, Context c){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(user);
        editor.putString("userDetails",json);
        editor.apply();
    }

    public static String getUserData(Context c){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(c);
        return sharedPreferences.getString("userDetails",null);
    }

    public static void setProductDetailsMen(ArrayList<Product> productName,Context c){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(productName);
        editor.putString("productDetailsMen",json);
        editor.apply();
    }
    public static String getProductDetailsMen(Context context){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString("productDetailsMen",null);
    }


    public static void deleteProductMen(Context context){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.remove("productDetailsMen");
        editor.apply();
    }

    public static void setProductDetailsWomen(ArrayList<Product> setProducts, Context c) {

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(setProducts);
        editor.putString("productDetailsWomen",json);
        editor.apply();
    }

    public static String getProductDetailsWomen(Context context){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString("productDetailsWomen",null);
    }
    public static void deleteProductWomen(Context context){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.remove("productDetailsWomen");
        editor.apply();
    }

    public static void setProductDetailsKids(ArrayList<Product> setProducts, Context c) {

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(setProducts);
        editor.putString("productDetailKids",json);
        editor.apply();
    }
    public static String getProductDetailsKids(Context context){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString("productDetailsKids",null);
    }

    public static void deleteProductKids(Context context){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.remove("productDetailsKids");
        editor.apply();
    }

    public static void setDefaultLanguage(String lang,Context context)
    {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("language",lang);
        editor.apply();
    }
    public static String getDefaultLanguage(Context context)
    {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString("language",null);
    }

}
