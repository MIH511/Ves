package com.delivery.ves.Apis;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL= "http:///Veš.com/apis/";
    public static String BASE_URL_PHOTO="http://Veš.com/dist/images/";
    public static String BASE_URL_PHOTO_PROFILE="http://Veš.com/apis/profile/";

    public static Retrofit getDataClient() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .client(provideOkHttp())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static Interceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public static OkHttpClient provideOkHttp() {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(provideLoggingInterceptor())
                .build();
    }

}
