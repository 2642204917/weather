package com.sunnyweather.android.logic

import android.widget.Toast
import androidx.lifecycle.Observer
import java.lang.ref.WeakReference

sealed class ContextEvent {
    val time = System.currentTimeMillis()
}


data class ShowToast(val text: String, val duration: Int) : ContextEvent() {

    companion object {
        fun long(text: String) = ShowToast(text, Toast.LENGTH_LONG)
        fun short(text: String) = ShowToast(text, Toast.LENGTH_SHORT)
    }
}


interface ContextEventListener {
    fun showToast(event: ShowToast)

}


class ContextEventObserver(listener: ContextEventListener) : Observer<ContextEvent> {
    private val contextEventListener = WeakReference(listener)

    override fun onChanged(value: ContextEvent) {
        val listener = contextEventListener.get() ?: return

        when (value) {
            is ShowToast -> listener.showToast(value)
        }
    }


}