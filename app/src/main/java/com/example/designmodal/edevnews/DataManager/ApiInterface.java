package com.example.designmodal.edevnews.DataManager;

import com.example.designmodal.edevnews.Model.MenuModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by compware on 6/15/2018.
 */

public interface ApiInterface
{
    @GET("NewsCategory.php")
    Call<List<MenuModel>> getNewsCategory();

//    @POST("intern.php")
//    @FormUrlEncoded
//    public Call<List<JobAttributes>> sendInternShip(@Field("jobType") String Type);
//
//
//    //prem's portion
//    @POST("contact.php")//this os a interface where we create base to hit url
//    @FormUrlEncoded
//    Call<String> getStringScaler(@Field("fullname") String name, @Field("email") String email,
//                                 @Field("contact_no") String contact_no, @Field("msg") String msg);


}