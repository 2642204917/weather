package com.sunnyweather.android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sunnyweather.android.databinding.FragmentWeatherBinding
import com.sunnyweather.android.ui.base.BaseFragment
import com.sunnyweather.android.ui.vm.WeatherViewModel

class WeatherFragment : BaseFragment() {

    private val weatherViewModel by viewModels<WeatherViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentWeatherBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.weatherVm = weatherViewModel
            weatherViewModel.refreshWeather()
            observeContext(weatherViewModel._contextEvent)
        }.root
    }

}