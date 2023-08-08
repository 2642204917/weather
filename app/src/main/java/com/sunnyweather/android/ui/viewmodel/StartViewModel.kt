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
            Log.d(TAG, "getSavePlace: ++++" + savePlace)

            if (savePlace == null) {
                Log.d(TAG, "getSavePlace: 为空")
                navigate(R.id.action_nav_startFragment_to_placeFragment, null, null)
            } else {
                Log.d(TAG, "getSavePlace: 不为空")
            }
        }
    }
}