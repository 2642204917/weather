package com.sunnyweather.android.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import com.sunnyweather.android.R
import com.sunnyweather.android.logic.ContextEvent
import com.sunnyweather.android.logic.ContextEventListener
import com.sunnyweather.android.logic.ContextEventObserver
import com.sunnyweather.android.logic.ShowToast

abstract class BaseFragment : DialogFragment(), ContextEventListener {


    private val mContextEventObserver by lazy { ContextEventObserver(this) }

    protected fun observeContext(event: LiveData<ContextEvent>) {
        event.observe(this, mContextEventObserver)
    }

    override fun showToast(event: ShowToast) {
        getActivityListener()?.showToast(event)
    }


    private fun getActivityListener(): ContextEventListener? =
        requireActivity() as? ContextEventListener


}