package com.sunnyweather.android.logic.model

import java.util.Date

data class Weather(val realtime: Realtime, val daily: Daily)
data class DailyWeatherResponseBean(val daily: Daily, val primary: Int)
data class Daily(
    val status: String,

    val temperature: List<Temperature>,
    val wind: List<WindForDailyWeather>,
    val skycon: List<Skycon>,
    val lifeIndex: List<LifeIndex>,
)

data class Temperature(
    val date: String,
    val max: Double,
    val min: Double,
    val avg: Double,

    )

data class WindForDailyWeather(
    val date: String,
    val max: Max,
    val min: Min,
    val avg: Avg,

    )

data class Max(val speed: Double, val direction: Double)
data class Min(val speed: Double, val direction: Double)
data class Avg(val speed: Double, val direction: Double)
data class Skycon(val date: Date, val value: String)
data class LifeIndex(
    val ultraviolet: LifeDescription,
    val comfort: LifeDescription,
    val carWashing: LifeDescription,
    val dressing: LifeDescription,
    val coldRisk: LifeDescription,
)

data class LifeDescription(val data: String, val index: String, val desc: String)



