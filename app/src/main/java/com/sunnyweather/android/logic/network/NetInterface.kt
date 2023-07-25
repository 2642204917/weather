package com.sunnyweather.android.logic.network

import com.sunnyweather.android.logic.constant.Token
import com.sunnyweather.android.logic.model.PlaceResponseBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetInterface {


    @GET("v2/place?token=${Token}&lang=zh_CN")
    suspend fun searchPlaces(@Query("query") query: String): Result<PlaceResponseBean>
}