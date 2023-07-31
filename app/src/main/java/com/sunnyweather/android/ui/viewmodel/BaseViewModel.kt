package com.sunnyweather.android.ui.viewmodel

import androidx.annotation.MainThread
import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sunnyweather.android.logic.ContextEvent
import com.sunnyweather.android.logic.ShowToast
import com.sunnyweather.android.mApplication.Companion.application

open class BaseViewModel : ViewModel() {

    protected val TAG = this.javaClass.simpleName
    private val contextEvent = MutableLiveData<ContextEvent>()
    val _contextEvent = contextEvent


    private fun sendContextEvent(event: ContextEvent) {
        contextEvent.value = event
    }

    @MainThread
    protected fun showToast(@StringRes textRes: Int, short: Boolean = false) {
        val text = application.getString(textRes)
        val event = if (short) {
            ShowToast.short(text)
        } else {
            ShowToast.long(text)
        }
        sendContextEvent(event)


    }

}