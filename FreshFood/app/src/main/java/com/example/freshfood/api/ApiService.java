package com.example.freshfood.api;

import com.example.freshfood.model.AsiaFood;
import com.example.freshfood.model.Bill;
import com.example.freshfood.model.PopularFood;
import com.example.freshfood.model.SearchFood;
import com.example.freshfood.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

//http://localhost/freshfood/

public interface ApiService {
    Gson gson = new GsonBuilder().create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.0.36/freshfood/")//1.159
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService.class);
    @GET("Product.php")
    Call<List<PopularFood>> popularFoodCall();

    @GET("Product.php")
    Call<List<AsiaFood>> asiaFoodCall();

    @FormUrlEncoded
    @POST("addProduct.php")
    Call<AsiaFood> addProduct(@Field("tenhang") String tenhang,@Field("dongia") String dongia,@Field("image") String image,@Field("mota") String mota);

    @FormUrlEncoded
    @POST("updateProduct.php")
    Call<AsiaFood> updateProduct(@Field("tenhang") String tenhang,@Field("dongia") String dongia,@Field("image") String image,@Field("mota") String mota,@Field("id") String id);

    @FormUrlEncoded
    @POST("login.php")
    Call<User> userCall(@Field("username") String username,@Field("pass") String pass);

    @FormUrlEncoded
    @POST("signup.php")
    Call<User> signup(@Field("username") String username,@Field("email") String mail,@Field("phone") String num,@Field("pass") String pass);


    @GET("Search.php")
    Call<List<SearchFood>> searchFood(@Query("key") String keyword);

    @FormUrlEncoded
    @POST("bill.php")
    Call<Bill> bill(@Field("json") String json,@Field("tongtien") float total,@Field("id_user") String id_user);
}
