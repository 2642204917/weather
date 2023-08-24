package com.sunnyweather.android.logic.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sunnyweather.android.BuildConfig
import com.sunnyweather.android.logic.PlaceDataStore
import com.sunnyweather.android.logic.model.DailyWeatherResponseBean
import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.logic.model.RealtimeResponseBean
import com.sunnyweather.android.logic.network.adapter.ServiceResponseCallAdapterFactory
import com.sunnyweather.android.logic.util.gsonFormat
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpRepository : ApiRepository {

    private const val url = BuildConfig.API_URL

    @Suppress("KotlinConstantConditions")
    private const val mock: Boolean = BuildConfig.FLAVOR == "mock"
    private val placeMutableData: MutableLiveData<Place> = MutableLiveData()

    val placeLiveData: LiveData<Place> = placeMutableData


    private val retrofit by lazy {
        val client = OkHttpClient.Builder().apply {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            addInterceptor(interceptor)
        }.build()
        Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gsonFormat))
            .client(client)
            .addCallAdapterFactory(ServiceResponseCallAdapterFactory())
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

    suspend fun startQuery(query: String):Result<List<Place>> = runCatching {
        httpService.searchPlaces(query).places!!
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
        ).result!!
    }

    suspend fun dailyWeather(): Result<DailyWeatherResponseBean> = runCatching {
        httpService.dailyWeather(
            placeLiveData.value!!.location.lng.toString(),
            placeLiveData.value!!.location.lat.toString()
        ).result!!
    }
}



