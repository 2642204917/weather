package com.sunnyweather.android.logic.reporstry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sunnyweather.android.logic.PlaceDataStore
import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.logic.network.NetInterface
import com.sunnyweather.android.logic.util.gsonFormat

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object HttpRepository : ApiRepository {

    private const val url = "https://api.caiyunapp.com/"

    private val placeMutableData: MutableLiveData<Place> = MutableLiveData()
    val placeLiveData: LiveData<Place> = placeMutableData


    private val retrofit =
        Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create(gsonFormat))
            .build()

    private val httpService = retrofit.create(NetInterface::class.java)


    suspend fun getSavePlace(): Place? {
        val place = PlaceDataStore.getPlace() ?: return null

        return place.also { placeMutableData.value = place }
    }

    suspend fun startQuery(query: String) = runCatching {
        httpService.searchPlaces(query).places
    }


    suspend fun savePlace(place: Place) {
        PlaceDataStore.savePlace(place)

    }
}