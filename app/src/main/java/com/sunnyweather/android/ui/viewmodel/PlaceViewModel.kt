package com.sunnyweather.android.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

class PlaceViewModel : ViewModel() {


    private  val TAG=this.javaClass.simpleName



    fun startSearch(searchStr:String){
        Log.d(TAG, "startSearch: "+searchStr)

    }




}