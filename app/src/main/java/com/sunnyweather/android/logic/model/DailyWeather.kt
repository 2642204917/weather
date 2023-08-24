package com.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName

data class DailyWeatherResponseBean(val daily: Daily) {

    data class Daily(
        val temperature: List<Temperature>,
        val skycon: List<Skycon>,
        val lifeIndex: LifeIndex
    )

    data class Temperature(val max: Float, val min: Float)

    data class Skycon(val value: String, val date: String)

    data class LifeIndex(
        @SerializedName("coldRisk")
        val coldRisk: List<LifeDescription>,
        @SerializedName("carWashing")
        val carWashing: List<LifeDescription>,
        val ultraviolet: List<LifeDescription>,
        val dressing: List<LifeDescription>
    )

    data class LifeDescription(val desc: String)

}
