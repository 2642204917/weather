package com.sunnyweather.android.ui.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sunnyweather.android.R
import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.logic.reporstry.HttpRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.suspendCoroutine

class PlaceViewModel : BaseViewModel() {
    private val _placeLiveData = MutableLiveData<Result<List<Place>>>()
    val placeLiveData: LiveData<Result<List<Place>>> = _placeLiveData
    val query = ObservableField<String>()


    fun query() {
        viewModelScope.launch { startQuery() }
    }


    private suspend fun startQuery() {
        val queryStr = query.get()
        if (queryStr.isNullOrEmpty()) {
            showToast(R.string.search_hint)
            return
        }

        _placeLiveData.value = HttpRepository.startQuery(queryStr)


    }


    fun savePlace(place: Place) {
        runBlocking {
            HttpRepository.savePlace(place)
        }

    }
}