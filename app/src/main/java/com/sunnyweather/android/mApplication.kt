package com.sunnyweather.android

import android.app.Application

class mApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        get(this)
    }

    companion object {
        lateinit var application: Application

        fun get(application: Application) {

         this.application = application
        }
    }

}