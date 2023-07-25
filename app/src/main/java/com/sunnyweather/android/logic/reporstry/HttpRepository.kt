package com.sunnyweather.android.logic.reporstry


import com.sunnyweather.android.logic.network.NetInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpRepository : ApiRepository {

    private const val url = ""
    val retrofit =
        Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()

    val httpService = retrofit.create(NetInterface::class.java)



}