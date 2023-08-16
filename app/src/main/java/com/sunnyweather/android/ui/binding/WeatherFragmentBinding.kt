package com.sunnyweather.android.ui.binding

import android.content.Context
import android.hardware.input.InputManager
import android.inputmethodservice.InputMethodService
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.GravityCompat

import androidx.databinding.BindingAdapter
import androidx.drawerlayout.widget.DrawerLayout
import com.sunnyweather.android.R
import com.sunnyweather.android.logic.model.DailyWeatherResponseBean
import com.sunnyweather.android.logic.model.getSky
import java.text.SimpleDateFormat
import java.util.Locale

@BindingAdapter("currentTempBg")
fun RelativeLayout.currentTempBg(skycon: String?) {

    skycon ?: return
    setBackgroundResource(getSky(skycon).bg)

}

@BindingAdapter("currentTemp")
fun TextView.currentTemp(temp: Int) {
    text = context.getString(R.string.current_weather_temp, temp)

}

@BindingAdapter("currentSky")
fun TextView.currentSky(skycon: String?) {
    text = getSky(skycon).info

}

@BindingAdapter("currentAqi")
fun TextView.currentAqi(aqi: Int) {
    text = context.getString(R.string.current_weather_aqi, aqi)

}

@BindingAdapter("currentDate")
fun TextView.currentDate(date: String?) {
    date ?: return
    val formatter =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault(Locale.Category.FORMAT))
    val time = formatter.parse(date)
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault(Locale.Category.FORMAT))
    text = time?.let { sdf.format(it) }
}

@BindingAdapter("skyIcon")
fun ImageView.skyIcon(skyCon: String) {
    setBackgroundResource(getSky(skyCon).icon)
}

@BindingAdapter("skyInfo")
fun TextView.skyInfo(skyCon: String) {
    text = getSky(skyCon).info
}

@BindingAdapter("temperatureInterval")
fun TextView.temperatureInterval(temperature: DailyWeatherResponseBean.Temperature) {
    text = context.getString(
        R.string.current_temperature_interval,
        temperature.min.toInt(),
        temperature.max.toInt()
    )
}

@BindingAdapter("closeSideslip")
fun DrawerLayout.closeSideslip(startQuery: Boolean) {
    if (startQuery && this.isDrawerOpen(GravityCompat.START)) {
        val systemService = this.context.getSystemService(Context.INPUT_METHOD_SERVICE)  as InputMethodManager
        systemService.hideSoftInputFromWindow(this.windowToken,0)
        this.closeDrawer(GravityCompat.START)
    }
}