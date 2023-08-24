package com.sunnyweather.android.network
import com.sunnyweather.android.logic.model.BaseResponseBean
import com.sunnyweather.android.logic.model.DailyWeatherResponseBean
import com.sunnyweather.android.logic.model.Location
import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.logic.model.RealtimeResponseBean
import com.sunnyweather.android.logic.network.NetApi
import kotlinx.coroutines.delay

@Suppress("unused")
class NetMock : NetApi {
    override suspend fun searchPlaces(query: String): BaseResponseBean<Any> {
        val places = ArrayList<Place>()
        repeat(10) {

            val place = Place("$it", "${query}$it", "广东省广州市天河区", Location(1.0, 1.0))
            places.add(place)
        }
        return BaseResponseBean(status = "ok", result = null, places = places)
    }

    override suspend fun realTime(
        lng: String,
        lag: String
    ): BaseResponseBean<RealtimeResponseBean> {
        delay(2000)
        val realtimeResponseBean =

            RealtimeResponseBean(
                realtime = RealtimeResponseBean.Realtime(
                    status = "ok",
                    temperature = 35,
                    humidity = 0.09,
                    cloudrate = 0.41,
                    skycon = "PARTLY_CLOUDY_DAY",

                    dswrf = 594.2,
                    wind = RealtimeResponseBean.WindForRealtime(speed = 2.52, direction = 129),
                    pressure = 83613.75,
                    apparentTemperature = 34.3,
                    precipitation = RealtimeResponseBean.PrecipitationForRealTime(
                        local = RealtimeResponseBean.Local("ok", "rader", 0.0),
                        nearest = RealtimeResponseBean.Nearest("ok", 60.87, 0.1875)
                    ),
                    airQuality = RealtimeResponseBean.AirQuality(
                        aqi = RealtimeResponseBean.Aqi(chn = 44, usa = 0),
                    ),
                )
            )
        return BaseResponseBean(
            status = "ok",
            result = realtimeResponseBean,
            places = null
        )

    }

    override suspend fun dailyWeather(
        lng: String,
        lag: String
    ): BaseResponseBean<DailyWeatherResponseBean> {
        delay(2000)
        val daily = DailyWeatherResponseBean(
            DailyWeatherResponseBean.Daily(

                temperature = listOf(
                    DailyWeatherResponseBean.Temperature(
                        max = 33.34F,
                        min = 18.43F,

                        ),
                    DailyWeatherResponseBean.Temperature(
                        max = 33.34F,
                        min = 18.43F,

                        ),
                    DailyWeatherResponseBean.Temperature(
                        max = 33.34F,
                        min = 18.43F,

                        ),
                    DailyWeatherResponseBean.Temperature(
                        max = 33.34F,
                        min = 18.43F,

                        ),
                    DailyWeatherResponseBean.Temperature(
                        max = 33.34F,
                        min = 18.43F,

                        )
                ),

                skycon = listOf(
                    DailyWeatherResponseBean.Skycon(
                        date = "2023-08-13T00:00+08:00",
                        value = "PARTLY_CLOUDY_NIGHT"
                    ),
                    DailyWeatherResponseBean.Skycon(
                        date = "2023-08-14T00:00+08:00",
                        value = "PARTLY_CLOUDY_NIGHT"
                    ),
                    DailyWeatherResponseBean.Skycon(
                        date = "2023-08-15T00:00+08:00",
                        value = "PARTLY_CLOUDY_NIGHT"
                    ),
                    DailyWeatherResponseBean.Skycon(
                        date = "2023-08-16T00:00+08:00",
                        value = "PARTLY_CLOUDY_NIGHT"
                    ),
                    DailyWeatherResponseBean.Skycon(
                        date = "2023-08-17T00:00+08:00",
                        value = "PARTLY_CLOUDY_NIGHT"
                    ),
                ),
                lifeIndex =
                DailyWeatherResponseBean.LifeIndex(
                    ultraviolet = listOf(
                        DailyWeatherResponseBean.LifeDescription(
                            desc = "中等"
                        )
                    ),
                    carWashing = listOf(
                        DailyWeatherResponseBean.LifeDescription(

                            desc = "热"
                        )
                    ),
                    dressing = listOf(
                        DailyWeatherResponseBean.LifeDescription(

                            desc = "温暖"
                        )
                    ),
                    coldRisk = listOf(
                        DailyWeatherResponseBean.LifeDescription(
                            desc = "极易发"

                        )
                    )
                ),
            )
        )
        return BaseResponseBean(status = "ok", result = daily, places = null)
    }


}