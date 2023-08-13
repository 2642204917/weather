package com.sunnyweather.android.ui.binding

import android.view.inputmethod.EditorInfo
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.sunnyweather.android.R
import com.sunnyweather.android.logic.model.Skycon
import com.sunnyweather.android.logic.model.getSky
import java.text.SimpleDateFormat
import java.util.Date
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
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
    val date = formatter.parse("2023-08-13T00:00+08:00")
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    text = sdf.format(date)
}

@BindingAdapter("currentSkyIcon")
fun TextView.currentSkyIcon(date: Date?) {
    date ?: return
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    text = simpleDateFormat.format(date)
}
