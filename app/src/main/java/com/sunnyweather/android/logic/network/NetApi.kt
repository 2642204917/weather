package com.sunnyweather.android.logic.network

import com.sunnyweather.android.BuildConfig
import com.sunnyweather.android.logic.model.PlaceResponseBean
import retrofit2.http.GET
import retrofit2.http.Query

interface NetApi {


    @GET("v2/place?token=${BuildConfig.TOKEN}&lang=zh_CN")
    suspend fun searchPlaces(@Query("query") query: String): PlaceResponseBean
}