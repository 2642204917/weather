package com.sunnyweather.android.logic.reporstry


import com.sunnyweather.android.logic.network.NetInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpRepository : ApiRepository {

    private const val url = "https://api.caiyunapp.com/"


    private val retrofit =
        Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()

    private val httpService = retrofit.create(NetInterface::class.java)


    suspend fun startQuery(query: String) = runCatching {
        httpService.searchPlaces(query).places
    }
}