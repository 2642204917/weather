package com.sunnyweather.android.ui.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sunnyweather.android.R
import com.sunnyweather.android.databinding.ActivityMainBinding
import com.sunnyweather.android.logic.ShowToast
import com.sunnyweather.android.ui.base.BaseActivity
import com.sunnyweather.android.ui.viewmodel.PlaceViewModel

class MainActivity : BaseActivity() {


    private val viewModelProvider by lazy { ViewModelProvider(this) }

    private val placeViewModel: PlaceViewModel by lazy { viewModelProvider[PlaceViewModel::class.java] }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).also {

            it.lifecycleOwner = this

            observeContext(placeViewModel._contextEvent)
        }


    }


}