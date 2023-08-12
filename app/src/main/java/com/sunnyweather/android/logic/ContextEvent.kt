package com.sunnyweather.android.logic

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import com.sunnyweather.android.ui.base.BaseFragment
import java.lang.ref.WeakReference

sealed class ContextEvent {
}


data class ShowToast(val text: String, val duration: Int) : ContextEvent() {

    companion object {
        fun long(text: String) = ShowToast(text, Toast.LENGTH_LONG)
        fun short(text: String) = ShowToast(text, Toast.LENGTH_SHORT)
    }
}

data class ShowFragment(val fragment: BaseFragment, val tag: String?) : ContextEvent()

data class Navigate(@IdRes val resId: Int, var args: Bundle?, var navOptions: NavOptions?) :
    ContextEvent()

interface ContextEventListener {
    fun showToast(event: ShowToast)
    fun showFragment(event: ShowFragment)
    fun navigate(event: Navigate)

}


class ContextEventObserver(listener: ContextEventListener) : Observer<ContextEvent> {
    private val contextEventListener = WeakReference(listener)

    override fun onChanged(value: ContextEvent) {
        val listener = contextEventListener.get() ?: return
        when (value) {
            is ShowToast -> listener.showToast(value)
            is ShowFragment -> listener.showFragment(value)
            is Navigate -> listener.navigate(value)
        }
    }


}