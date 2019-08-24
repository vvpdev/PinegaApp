package com.hfad.pinegaapp.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {


    private static NetworkService mInstance;

    // базовый URL
    private static final String BASE_URL = "https://api.vk.com";

    Retrofit mRetrofit;



    // конструктор
    public NetworkService(){

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    // паттерн singleton для возврата единственного экземпляра класса
    public static NetworkService getInstance() {
        if (mInstance == null){
            mInstance = new NetworkService();
        }
        return mInstance;
    }



    // метод для возврата класса на основе интерфейса
    public GetPostsVK getJSON_Create (){
        return mRetrofit.create(GetPostsVK.class);
    }







}
