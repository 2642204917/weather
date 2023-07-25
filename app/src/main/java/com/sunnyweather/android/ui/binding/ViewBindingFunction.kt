package com.sunnyweather.android.ui.binding

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment

@BindingAdapter("ShowFragment")
fun TextView.setShowFragment(string: String) {

    Log.d("TAG", "setFragment:$string ")

}