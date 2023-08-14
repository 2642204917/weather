package com.sunnyweather.android.network

import com.sunnyweather.android.logic.model.AirQuality
import com.sunnyweather.android.logic.model.Aqi
import com.sunnyweather.android.logic.model.Avg
import com.sunnyweather.android.logic.model.BaseResponseBean
import com.sunnyweather.android.logic.model.Daily
import com.sunnyweather.android.logic.model.DailyWeatherResponseBean

import com.sunnyweather.android.logic.model.Description
import com.sunnyweather.android.logic.model.LifeDescription
import com.sunnyweather.android.logic.model.LifeIndex

import com.sunnyweather.android.logic.model.Local
import com.sunnyweather.android.logic.model.Location
import com.sunnyweather.android.logic.model.Max
import com.sunnyweather.android.logic.model.Min
import com.sunnyweather.android.logic.model.Nearest
import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.logic.model.PlaceResponseBean

import com.sunnyweather.android.logic.model.PrecipitationForRealTime
import com.sunnyweather.android.logic.model.Realtime
import com.sunnyweather.android.logic.model.RealtimeResponseBean
import com.sunnyweather.android.logic.model.Result
import com.sunnyweather.android.logic.model.Skycon
import com.sunnyweather.android.logic.model.Temperature
import com.sunnyweather.android.logic.model.WindForDailyWeather
import com.sunnyweather.android.logic.model.WindForRealtime
import com.sunnyweather.android.logic.network.NetApi
import kotlinx.coroutines.delay

@Suppress("unused")
class NetMock : NetApi {
    override suspend fun searchPlaces(query: String): PlaceResponseBean {
        val places = ArrayList<Place>()
        repeat(10) {

            val place = Place("$it", "${query}$it", "广东省广州市天河区", Location(1.0, 1.0))
            places.add(place)
        }
        return PlaceResponseBean("ok", query, places)
    }

    override suspend fun realTime(
        lng: String,
        lag: String
    ): BaseResponseBean<RealtimeResponseBean> {
        delay(2000)
        val realtimeResponseBean =
            RealtimeResponseBean(
                realtime = Realtime(
                    status = "ok",
                    temperature = 35,
                    humidity = 0.09,
                    cloudrate = 0.41,
                    skycon = "PARTLY_CLOUDY_DAY",
                    visibility = 30,
                    dswrf = 594.2,
                    wind = WindForRealtime(speed = 2.52, direction = 129),
                    pressure = 83613.75,
                    apparentTemperature = 34.3,
                    precipitation = PrecipitationForRealTime(
                        local = Local("ok", "rader", 0.0),
                        nearest = Nearest("ok", 60.87, 0.1875)
                    ),
                    airQuality = AirQuality(
                        pm25 = 4,
                        pm10 = 0,
                        o3 = 0,
                        so2 = 0,
                        no2 = 0,
                        co = 0,
                        aqi = Aqi(chn = 44, usa = 0),
                        description = Description(chn = "", usa = "优")
                    ),
                ), primary = 0
            )
        return BaseResponseBean(status = "ok", result = Result(realtimeResponseBean))

    }

    override suspend fun dailyWeather(
        lng: String,
        lag: String
    ): BaseResponseBean<DailyWeatherResponseBean> {
        delay(2000)
        val daily = Daily(
            status = "ok",
            temperature = listOf(
                Temperature(date = "2023-08-13T00:00+08:00", max = 33.34, min = 18.43, avg = 25.86),
                Temperature(date = "2023-08-14T00:00+08:00", max = 33.34, min = 18.43, avg = 25.86),
                Temperature(date = "2023-08-15T00:00+08:00", max = 33.34, min = 18.43, avg = 25.86),
                Temperature(date = "2023-08-16T00:00+08:00", max = 33.34, min = 18.43, avg = 25.86),
                Temperature(date = "2023-08-17T00:00+08:00", max = 33.34, min = 18.43, avg = 25.86),
            ),
            wind = listOf(
                WindForDailyWeather(
                    date = "2023-08-13T00:00+08:00",
                    max = Max(30.8, 132.39),
                    min = Min(2.88, 39.22),
                    avg = Avg(19.92, 125.21)
                )
            ),
            skycon = listOf(
                Skycon(date = "2023-08-13T00:00+08:00", value = "PARTLY_CLOUDY_NIGHT"),
                Skycon(date = "2023-08-14T00:00+08:00", value = "PARTLY_CLOUDY_NIGHT"),
                Skycon(date = "2023-08-15T00:00+08:00", value = "PARTLY_CLOUDY_NIGHT"),
                Skycon(date = "2023-08-16T00:00+08:00", value = "PARTLY_CLOUDY_NIGHT"),
                Skycon(date = "2023-08-17T00:00+08:00", value = "PARTLY_CLOUDY_NIGHT"),
            ),
            lifeIndex = listOf(
                LifeIndex(
                    ultraviolet = LifeDescription(
                        data = "2023-08-13T00:00+08:00",
                        index = "3",
                        desc = "中等"
                    ),
                    comfort = LifeDescription(
                        data = "2023-08-13T00:00+08:00",
                        index = "3",
                        desc = "较不适宜"
                    ),
                    carWashing = LifeDescription(
                        data = "2023-08-13T00:00+08:00",
                        index = "3",
                        desc = "热"
                    ),
                    dressing = LifeDescription(
                        data = "2023-08-13T00:00+08:00",
                        index = "4",
                        desc = "温暖"
                    ),
                    coldRisk = LifeDescription(
                        data = "2023-08-13T00:00+08:00",
                        index = "4",
                        desc = "极易发"
                    ),
                )
            ),
        )
        return BaseResponseBean(status = "ok", result = Result(DailyWeatherResponseBean(daily, 0)))
    }


}