package com.sunnyweather.android.logic.model
/**
 * SB接口设计， 天气接口 实时数据接口 返回 status ，result
 * 地址接口 返回 status，places
 */
open class BaseResponseBean<T>(val status: String, val result: T?, val places: List<Place>?)