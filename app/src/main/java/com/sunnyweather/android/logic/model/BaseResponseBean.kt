package com.sunnyweather.android.logic.model

open class BaseResponseBean<T>(val status: String, val result: Result<T>)

data class  Result<T>(val data:T)