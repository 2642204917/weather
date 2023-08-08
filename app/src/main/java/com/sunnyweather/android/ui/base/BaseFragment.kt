package com.sunnyweather.android.ui.base


import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

import com.sunnyweather.android.logic.ContextEvent
import com.sunnyweather.android.logic.ContextEventListener
import com.sunnyweather.android.logic.ContextEventObserver
import com.sunnyweather.android.logic.Navigate
import com.sunnyweather.android.logic.ShowFragment
import com.sunnyweather.android.logic.ShowToast

abstract class BaseFragment : Fragment(), ContextEventListener {


    private val mContextEventObserver by lazy { ContextEventObserver(this) }

    protected fun observeContext(event: LiveData<ContextEvent>) {
        event.observe(this, mContextEventObserver)
    }

    override fun showToast(event: ShowToast) {
        getActivityListener()?.showToast(event)
    }

    override fun showFragment(event: ShowFragment) {
        getActivityListener()?.showFragment(event)
    }

    override fun navigate(event: Navigate) {
        getActivityListener()?.navigate(event)
    }

    private fun getActivityListener(): ContextEventListener? =
        requireActivity() as? ContextEventListener


}