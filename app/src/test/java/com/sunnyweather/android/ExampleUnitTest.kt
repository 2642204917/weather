package com.sunnyweather.android

import com.google.gson.Gson

import com.sunnyweather.android.logic.model.RealtimeResponseBean
import org.junit.Test
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val test =
            "{\"status\":\"ok\",\"api_version\":\"v2.6\",\"api_status\":\"alpha\",\"lang\":\"zh_CN\",\"unit\":\"metric\",\"tzshift\":28800,\"timezone\":\"Asia/Shanghai\",\"server_time\":1692201729,\"location\":[23.130061,113.264499],\"result\":{\"realtime\":{\"status\":\"ok\",\"temperature\":30.0,\"humidity\":0.82,\"cloudrate\":0.78,\"skycon\":\"PARTLY_CLOUDY_NIGHT\",\"visibility\":22.66,\"dswrf\":0.0,\"wind\":{\"speed\":3.6,\"direction\":167.0},\"pressure\":100400.98,\"apparent_temperature\":34.8,\"precipitation\":{\"local\":{\"status\":\"ok\",\"datasource\":\"radar\",\"intensity\":0.0},\"nearest\":{\"status\":\"ok\",\"distance\":205.09,\"intensity\":0.1875}},\"air_quality\":{\"pm25\":17,\"pm10\":36,\"o3\":31,\"so2\":6,\"no2\":36,\"co\":0.8,\"aqi\":{\"chn\":36,\"usa\":61},\"description\":{\"chn\":\"优\",\"usa\":\"良\"}},\"life_index\":{\"ultraviolet\":{\"index\":0.0,\"desc\":\"无\"},\"comfort\":{\"index\":0,\"desc\":\"闷热\"}}},\"primary\":123}}"
        val bean = Gson().fromJson(test, RealtimeResponseBean::class.java)
        println("==========$bean")
    }
}