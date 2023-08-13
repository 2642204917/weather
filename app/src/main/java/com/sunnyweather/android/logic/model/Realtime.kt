package com.sunnyweather.android.logic.model

data class RealtimeResponseBean(val realtime: Realtime, val primary: Int)
data class Realtime(
    val status: String,
    val temperature: Int,
    val humidity: Double,
    val cloudrate: Double,
    val skycon: String,
    val visibility: Int,
    val dswrf: Double,
    val wind: WindForRealtime,
    val pressure: Double,
    val apparentTemperature: Double,
    val precipitation: PrecipitationForRealTime,
    val airQuality: AirQuality,
)

data class WindForRealtime(val speed: Double, val direction: Int)
data class PrecipitationForRealTime(val local: Local, val nearest: Nearest)
data class Local(val status: String, val datasource: String, val intensity: Double)
data class Nearest(val status: String, val distance: Double, val intensity: Double)
data class AirQuality(
    val pm25: Int,
    val pm10: Int,
    val o3: Int,
    val so2: Int,
    val no2: Int,
    val co: Int,
    val aqi: Aqi,
    val description: Description,

    )

data class Aqi(val chn: Int, val usa: Int)
data class Description(val chn: String, val usa: String)

