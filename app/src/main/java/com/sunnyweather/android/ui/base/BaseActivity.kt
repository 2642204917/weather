package com.sunnyweather.android.ui.base

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.LiveData
import com.sunnyweather.android.logic.ContextEvent
import com.sunnyweather.android.logic.ContextEventListener
import com.sunnyweather.android.logic.ContextEventObserver
import com.sunnyweather.android.logic.ShowToast

abstract class BaseActivity : AppCompatActivity(), ContextEventListener {

    protected val TAG: String = this::class.java.simpleName

    private val mContextEventObserver by lazy { ContextEventObserver(this) }



    protected fun observeContext(event: LiveData<ContextEvent>) {
        event.observe(this, mContextEventObserver)
    }





    override fun showToast(event: ShowToast) {
        Log.d(TAG, "showToast: activity")
        Toast.makeText(this, event.text, event.duration).show()
    }
}