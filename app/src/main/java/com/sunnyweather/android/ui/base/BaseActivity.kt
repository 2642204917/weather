package com.sunnyweather.android.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: DB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val contentView = DataBindingUtil.setContentView<DB>(this, getLayoutId()).also {
            binding = it
            binding.lifecycleOwner = this
        }.root
        setContentView(contentView)

        initView()
        initData()
    }

    // abstract fun sendActivityEvent()

    protected abstract fun getLayoutId(): Int
    protected abstract fun initView()
    protected abstract fun initData()

}