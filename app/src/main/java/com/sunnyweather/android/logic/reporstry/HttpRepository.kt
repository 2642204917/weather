package com.sunnyweather.android.logic.reporstry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sunnyweather.android.BuildConfig
import com.sunnyweather.android.logic.PlaceDataStore
import com.sunnyweather.android.logic.model.DailyWeatherResponseBean
import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.logic.model.Realtime
import com.sunnyweather.android.logic.model.RealtimeResponseBean
import com.sunnyweather.android.logic.network.NetApi
import com.sunnyweather.android.logic.util.gsonFormat
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpRepository : ApiRepository {

    private const val url = BuildConfig.API_URL

    @Suppress("KotlinConstantConditions")
    private const val mock: Boolean = BuildConfig.FLAVOR == "mock"
    private val placeMutableData: MutableLiveData<Place> = MutableLiveData()

    val placeLiveData: LiveData<Place> = placeMutableData


    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create(gsonFormat))
            .build()
    }


    private val httpService: NetApi = if (mock) {
        Class.forName("com.sunnyweather.android.network.NetMock").getConstructor()
            .newInstance() as NetApi
    } else {
        retrofit.create(NetApi::class.java)
    }


    suspend fun getSavePlace(): Place? {

        val place = PlaceDataStore.getPlace() ?: return null

        return place.also { placeMutableData.value = place }
    }

    suspend fun startQuery(query: String) = runCatching {
        httpService.searchPlaces(query).places
    }

    suspend fun savePlace(place: Place): Result<Boolean> = runCatching {
        val isFirstSave = placeLiveData.value == null
        placeMutableData.value = place
        PlaceDataStore.savePlace(place)
        isFirstSave
    }

    suspend fun realTime(): Result<RealtimeResponseBean> = runCatching {
        httpService.realTime(
            placeLiveData.value!!.location.lng.toString(),
            placeLiveData.value!!.location.lat.toString()
        ).result.data
    }

    suspend fun dailyWeather(): Result<DailyWeatherResponseBean> = runCatching {
        httpService.dailyWeather(
            placeLiveData.value!!.location.lng.toString(),
            placeLiveData.value!!.location.lat.toString()
        ).result.data
    }
}



