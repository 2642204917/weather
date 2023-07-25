package com.sunnyweather.android.ui.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sunnyweather.android.R
import com.sunnyweather.android.databinding.ActivityMainBinding
import com.sunnyweather.android.ui.base.BaseActivity
import com.sunnyweather.android.ui.viewmodel.PlaceViewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {


    private val viewModelProvider by lazy { ViewModelProvider(this) }

    private val placeViewModel: PlaceViewModel by lazy { viewModelProvider[PlaceViewModel::class.java] }


    override fun getLayoutId(): Int = R.layout.activity_main


    override fun initView() {

    }

    override fun initData() {
        binding.placeVM = placeViewModel

    }
}