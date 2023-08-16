package com.sunnyweather.android.ui.base


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

import com.sunnyweather.android.logic.event.ContextEvent
import com.sunnyweather.android.logic.event.ContextEventListener
import com.sunnyweather.android.logic.event.ContextEventObserver
import com.sunnyweather.android.logic.event.Navigate
import com.sunnyweather.android.logic.event.ShowFragment
import com.sunnyweather.android.logic.event.ShowToast

abstract class BaseFragment : Fragment(), ContextEventListener {


    private val mContextEventObserver by lazy { ContextEventObserver(this) }

    protected fun observeContext(event: LiveData<ContextEvent>) {
        event.observe(this, mContextEventObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onLifecycleOwnerCreated(viewLifecycleOwner,savedInstanceState)

    }
    protected open fun onLifecycleOwnerCreated(
        owner: LifecycleOwner,
        savedInstanceState: Bundle?
    ) {
        onLifecycleOwnerCreated(owner)
    }

    protected open fun onLifecycleOwnerCreated(owner: LifecycleOwner) {}
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