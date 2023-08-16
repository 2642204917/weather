package com.sunnyweather.android.logic.network

import com.sunnyweather.android.BuildConfig
import com.sunnyweather.android.logic.model.BaseResponseBean
import com.sunnyweather.android.logic.model.DailyWeatherResponseBean
import com.sunnyweather.android.logic.model.PlaceResponseBean
import com.sunnyweather.android.logic.model.RealtimeResponseBean
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetApi {


    @GET("v2/place?token=${BuildConfig.TOKEN}&lang=zh_CN")
    suspend fun searchPlaces(@Query("query") query: String): PlaceResponseBean

    @GET("v2.6/${BuildConfig.TOKEN}/{lng},{lag}/realtime")
    suspend fun realTime(
        @Path("lng") lng: String,
        @Path("lag") lag: String
    ): BaseResponseBean<RealtimeResponseBean>

    @GET("v2.6/${BuildConfig.TOKEN}/{lng},{lag}/daily")
    suspend fun dailyWeather(
        @Path("lng") lng: String,
        @Path("lag") lag: String
    ): BaseResponseBean<DailyWeatherResponseBean>
}