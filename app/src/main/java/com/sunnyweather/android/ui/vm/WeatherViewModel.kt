package com.sunnyweather.android.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.sunnyweather.android.R
import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.logic.model.Weather
import com.sunnyweather.android.logic.reporstry.HttpRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class WeatherViewModel : BaseViewModel() {
    private val httpRepository = HttpRepository
    val placeLiveData: LiveData<Place> = httpRepository.placeLiveData.map {
        refreshWeather()
        it.copy()
    }
    private val weatherMutableLiveData = MutableLiveData<Weather>()
    val weatherLiveData: LiveData<Weather> = weatherMutableLiveData
    val isRefreshing = MutableLiveData<Boolean>()


    fun refreshWeather() {
        isRefreshing.value = true
        viewModelScope.launch {
            val realTimeDeferred = async {
                HttpRepository.realTime()
            }


            val dailyWeatherDeferred = async { HttpRepository.dailyWeather() }
            val realTimeResponse = realTimeDeferred.await()
            val dailyWeatherResponse = dailyWeatherDeferred.await()


            if (realTimeResponse.isSuccess && dailyWeatherResponse.isSuccess) {

                weatherMutableLiveData.value = Weather(
                    realTimeResponse.getOrNull()!!.realtime,
                    dailyWeatherResponse.getOrNull()!!.daily
                )
                showToast(R.string.toast_load_weather_success)
                isRefreshing.value = false
            } else {
                isRefreshing.value = false
                showToast(R.string.toast_load_weather_failed)
            }
        }

    }
}