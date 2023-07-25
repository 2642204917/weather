package com.sunnyweather.android.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.sunnyweather.android.R

abstract class BaseFragment<DB : ViewDataBinding> : DialogFragment() {
    lateinit var binding: DB


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = DataBindingUtil.inflate<DB>(
            inflater,
            getLayoutId(),
            container,
            false
        ).also {

            it.lifecycleOwner = this

            binding = it
        }.root
        return bind
    }


    protected abstract fun getLayoutId(): Int

    protected abstract fun initView()
    protected abstract fun initData()
}