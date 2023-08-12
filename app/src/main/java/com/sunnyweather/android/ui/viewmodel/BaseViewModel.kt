package com.sunnyweather.android.ui.viewmodel

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import com.sunnyweather.android.logic.ContextEvent
import com.sunnyweather.android.logic.Navigate
import com.sunnyweather.android.logic.ShowFragment
import com.sunnyweather.android.logic.ShowToast
import com.sunnyweather.android.mApplication.Companion.application
import com.sunnyweather.android.ui.base.BaseFragment

open class BaseViewModel : ViewModel() {

    protected val TAG: String = this.javaClass.simpleName
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
@Suppress("unused")
    @MainThread
    protected fun showFragment(clazz: Class<out BaseFragment>) {

        val fragment = clazz.getConstructor().newInstance()
        val event = ShowFragment(fragment, fragment.tag)
        sendContextEvent(event)
    }

    @MainThread
    protected fun navigate(@IdRes id: Int, args: Bundle?=null, options: NavOptions?=null) {

        sendContextEvent(Navigate(id, args, options))
    }
}