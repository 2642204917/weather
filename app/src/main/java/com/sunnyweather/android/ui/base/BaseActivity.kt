package com.sunnyweather.android.ui.base
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.sunnyweather.android.logic.event.ContextEvent
import com.sunnyweather.android.logic.event.ContextEventListener
import com.sunnyweather.android.logic.event.ContextEventObserver
import com.sunnyweather.android.logic.event.ShowFragment
import com.sunnyweather.android.logic.event.ShowToast

abstract class BaseActivity : AppCompatActivity(), ContextEventListener {
    private val mContextEventObserver by lazy { ContextEventObserver(this) }

    @Suppress("unused")
    protected fun observeContext(event: LiveData<ContextEvent>) {
        event.observe(this, mContextEventObserver)
    }


    override fun showToast(event: ShowToast) {
        Toast.makeText(this, event.text, event.duration).show()
    }

    override fun showFragment(event: ShowFragment) {
        val supportFragmentManager = this.supportFragmentManager
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(event.fragment, event.tag).commitAllowingStateLoss()

    }
}