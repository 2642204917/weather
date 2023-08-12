package com.sunnyweather.android.network

import com.sunnyweather.android.logic.model.Location
import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.logic.model.PlaceResponseBean
import com.sunnyweather.android.logic.network.NetApi


class NetMock : NetApi {
    override suspend fun searchPlaces(query: String): PlaceResponseBean {
        val places = ArrayList<Place>()
        repeat(10) {

            val place = Place("$it", "${query}$it", "广东省广州市天河区", Location(1.0, 1.0))
            places.add(place)
        }
        return PlaceResponseBean("ok", "$query", places)
    }


}