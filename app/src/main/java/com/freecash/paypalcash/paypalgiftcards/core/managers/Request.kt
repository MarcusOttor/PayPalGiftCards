package com.freecash.paypalcash.paypalgiftcards.core.managers

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Request {

    @FormUrlEncoded
    @POST("/login.php")
    fun login(
            @Field("user") user: String,
            @Field("pass") pass: String
    ) : Call<Any>

    @FormUrlEncoded
    @POST("/signup.php")
    fun signup(
            @Field("user") user: String,
            @Field("pass") pass: String
    ) : Call<Any>

    @FormUrlEncoded
    @POST("/checkinvite.php")
    fun checkinvite(
            @Field("code") invite: String
    ) : Call<Any>

    @FormUrlEncoded
    @POST("/getinvite.php")
    fun getinvite (
            @Field("user") user: String
    ) : Call<Any>

    @FormUrlEncoded
    @POST("/invitecoins.php")
    fun invitesatoshi(
            @Field("user") user: String,
            @Field("pass") pass: String
    ) : Call<Any>

    @FormUrlEncoded
    @POST("/savedata.php")
    fun savedata(
            @Field("user") user: String,
            @Field("pass") pass: String,
            @Field("userdata") userdata: String
    ) : Call<Any>

    @FormUrlEncoded
    @POST("/getdata.php")
    fun getdata(
            @Field("user") user: String,
            @Field("pass") pass: String
    ) : Call<Any>
}
