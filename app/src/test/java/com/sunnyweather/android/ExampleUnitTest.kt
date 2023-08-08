package com.sunnyweather.android

import com.google.gson.Gson
import com.sunnyweather.android.logic.model.Location
import com.sunnyweather.android.logic.model.Place
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
       // val place = Place("1", "name", "asdsa", Location(0.0000, 0.00000))
        val str = "{\"formattedAddress\":\"中国 广东省 广州市 越秀区 越秀区\",\"id\":\"B00141JEHS\",\"location\":{\"lat\":23.130061,\"lng\":113.264499},\"name\":\"广州市\"}"
        val placeStr = Gson().toJson(str)
        println("---------------$placeStr")

        val bean = Gson().fromJson(placeStr, Place::class.java)
        println("============+$bean")

    }
}