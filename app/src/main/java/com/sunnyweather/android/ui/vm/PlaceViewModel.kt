package com.sunnyweather.android.ui.vm
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sunnyweather.android.R
import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.logic.network.HttpRepository
import kotlinx.coroutines.launch


class PlaceViewModel : BaseViewModel() {
    private val _placeList = MutableLiveData<List<Place>>()
    val placelist: LiveData<List<Place>> = _placeList
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

        HttpRepository.startQuery(queryStr).onSuccess {
            _placeList.value = it
        }.onFailure {
            Log.d("TAG", "startQuery: +$it")
            showToast(R.string.query_fail)
        }


    }

    fun savePlace(place: Place) {
        viewModelScope.launch {
            Log.d("TAG", "savePlace:+$place ")
            HttpRepository.savePlace(place).onSuccess { isFirstSave ->


                showToast(R.string.save_place_success)

                if (!isFirstSave) {
                    return@launch
                }
                navigate(R.id.action_placeFragment_to_weatherFragment)
            }.onFailure {
                showToast(R.string.sava_place_fail)
            }


        }
    }

}