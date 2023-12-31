package com.sunnyweather.android.logic.model

data class RealtimeResponseBean(val realtime: Realtime){
    data class Realtime(
        val status: String,
        val temperature: Int,
        val humidity: Double,
        val cloudrate: Double,
        val skycon: String,
        val dswrf: Double,
        val wind: WindForRealtime,
        val pressure: Double,
        val apparentTemperature: Double,
        val precipitation: PrecipitationForRealTime,
        val airQuality: AirQuality,
        var primary: Int = 1
    )

    data class WindForRealtime(val speed: Double, val direction: Int)
    data class PrecipitationForRealTime(val local: Local, val nearest: Nearest)
    data class Local(val status: String, val datasource: String, val intensity: Double)
    data class Nearest(val status: String, val distance: Double, val intensity: Double)
    data class AirQuality(
        val aqi: Aqi,

        )

    data class Aqi(val chn: Int, val usa: Int)
}



