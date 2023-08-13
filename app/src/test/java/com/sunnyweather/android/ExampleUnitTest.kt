package com.sunnyweather.android
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
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
        val date = formatter.parse("2023-08-13T00:00+08:00")
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val sDate = sdf.format(date)
        println("======"+sDate)

    }
}