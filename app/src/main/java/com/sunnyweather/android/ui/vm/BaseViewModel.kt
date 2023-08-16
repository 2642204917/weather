package com.sunnyweather.android.ui.vm

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import com.kunminx.architecture.domain.message.MutableResult
import com.sunnyweather.android.MyApplication.Companion.application
import com.sunnyweather.android.logic.event.ContextEvent
import com.sunnyweather.android.logic.event.Navigate
import com.sunnyweather.android.logic.event.ShowFragment
import com.sunnyweather.android.logic.event.ShowToast
import com.sunnyweather.android.ui.base.BaseFragment

open class BaseViewModel : ViewModel() {

    private val contextEventMutableData = MutableResult<ContextEvent>()

    val contextEvent:LiveData<ContextEvent> = contextEventMutableData


    private fun sendContextEvent(event: ContextEvent) {
        contextEventMutableData.value = event
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