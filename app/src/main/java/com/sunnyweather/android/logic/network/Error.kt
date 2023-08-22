package com.sunnyweather.android.logic.network

sealed class ResponseError : RuntimeException() {
    companion object {
        fun of(status: String): ResponseError? {
            return when (status) {
                ResponseCode.OK -> null
                ResponseCode.FAILED -> Error
                else -> Unknown(status)
            }
        }
    }
    data class Unknown(val status: String) : ResponseError()
    object Error : ResponseError()
}

@Suppress("unused")
object ResponseCode {
    const val OK = "ok"
    const val FAILED = "failed"

}