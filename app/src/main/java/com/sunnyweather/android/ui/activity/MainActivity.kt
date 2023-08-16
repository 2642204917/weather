package com.sunnyweather.android.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.sunnyweather.android.R
import com.sunnyweather.android.databinding.ActivityMainBinding
import com.sunnyweather.android.logic.event.Navigate
import com.sunnyweather.android.ui.base.BaseActivity


class MainActivity : BaseActivity() {

    private val navController by lazy { findNavController(R.id.fragment_controller) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).also {
            it.lifecycleOwner = this
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
    override fun navigate(event: Navigate) {
            navController.navigate(event.resId, event.args, event.navOptions)
    }


}