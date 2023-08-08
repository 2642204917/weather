package com.sunnyweather.android.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.add
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.navigateUp
import com.sunnyweather.android.R
import com.sunnyweather.android.databinding.ActivityMainBinding
import com.sunnyweather.android.logic.Navigate
import com.sunnyweather.android.logic.ShowToast
import com.sunnyweather.android.ui.base.BaseActivity
import com.sunnyweather.android.ui.fragment.PlaceFragment
import com.sunnyweather.android.ui.viewmodel.MainViewModel
import com.sunnyweather.android.ui.viewmodel.PlaceViewModel

class MainActivity : BaseActivity() {




    private val navController by lazy { findNavController(R.id.fragment_controller) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).also {
            it.lifecycleOwner = this

        }

    }


    override fun onSupportNavigateUp(): Boolean {
        Log.d(TAG, "onSupportNavigateUp: 返回键")
        return navController.navigateUp()
    }

    override fun navigate(event: Navigate) {
        Log.d(TAG, "navigate: 得到跳转需求")
        navController.navigate(event.resId, event.args, event.navOptions)
    }


}