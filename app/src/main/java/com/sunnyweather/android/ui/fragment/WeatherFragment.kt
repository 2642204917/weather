package com.sunnyweather.android.ui.fragment

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.sunnyweather.android.databinding.FragmentWeatherBinding
import com.sunnyweather.android.logic.adapter.ForecastAdapter
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
            it.setUp()


        }.root
    }

    override fun onLifecycleOwnerCreated(owner: LifecycleOwner) {
        super.onLifecycleOwnerCreated(owner)
        observeContext(weatherViewModel.contextEvent)
    }

    private fun FragmentWeatherBinding.setUp() {



        layoutCardNow.searchAddress.setOnClickListener {
            sidebar.openDrawer(GravityCompat.START)
        }
        val adapter = ForecastAdapter(emptyList(), emptyList())
        layoutCardForecast.forecastRv.adapter = adapter
        weatherViewModel.weatherLiveData.observe(viewLifecycleOwner) {
            adapter.weatherList = it.daily.skycon
            adapter.temperatureList = it.daily.temperature
            adapter.notifyItemChanged(0)
        }


    }

}