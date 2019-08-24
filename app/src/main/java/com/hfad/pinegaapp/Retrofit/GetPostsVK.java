package com.hfad.pinegaapp.Retrofit;

import com.hfad.pinegaapp.POJO.POJO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetPostsVK {

    @GET("/method/wall.get?owner_id=-128584547&count=30&access_token=ac485adcac485adcac485adcdfac23f20daac48ac485adcf161b55b9d131cef553f44c4&v=5.101")
    Call<POJO> getAllPosts();

}
