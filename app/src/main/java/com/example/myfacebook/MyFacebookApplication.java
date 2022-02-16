package com.example.myfacebook;

import android.app.Application;

import com.example.myfacebook.data.APIManager;
import com.example.myfacebook.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyFacebookApplication extends Application {

    Gson gson;
    Retrofit retrofit;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Gson getGson() {

        if(gson==null) {
            gson = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gson;
    }

    public Retrofit getRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(getBaseURL())
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build();
        return retrofit;
    }

    public static OkHttpClient getClient(){
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        return client;
    }

    public String getBaseURL() {
        String baseurl= Constants.BASE_URL;
        return baseurl;
    }

    public APIManager getAPIManager() {
        if(retrofit == null) {
            retrofit = getRetrofit();
        }
        return retrofit.create(APIManager.class);
    }
}
