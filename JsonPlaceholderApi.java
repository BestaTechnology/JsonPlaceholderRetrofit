package com.brahmsmart.jsonplaceholder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceholderApi {


    @GET("posts")
    Call<List<PostClass>> getposts();

}
