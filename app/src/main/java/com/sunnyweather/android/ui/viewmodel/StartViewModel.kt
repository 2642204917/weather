package com.sunnyweather.android.ui.viewmodel


import android.util.Log

import androidx.lifecycle.viewModelScope
import com.sunnyweather.android.R
import com.sunnyweather.android.logic.reporstry.HttpRepository
import kotlinx.coroutines.launch

class StartViewModel : BaseViewModel() {
    private val httpRepository = HttpRepository

    fun getSavePlace() {
        viewModelScope.launch {

            val savePlace = httpRepository.getSavePlace()
            if (savePlace == null) {
                navigate(R.id.action_nav_startFragment_to_placeFragment)
            }else{
                navigate(R.id.action_nav_startFragment_to_weatherFragment)
            }
        }
    }
}