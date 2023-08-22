package com.sunnyweather.android.logic.network.adapter

import com.sunnyweather.android.logic.model.BaseResponseBean
import com.sunnyweather.android.logic.network.ResponseError
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import java.lang.IllegalStateException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ServiceResponseCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val callType = getRawType(returnType)
        if (callType != Call::class.java) {
            return null
        }
        if (returnType !is ParameterizedType) {
            throw IllegalStateException(
                "CompletableFuture return type must be parameterized"
                        + " as CompletableFuture<Foo> or CompletableFuture<? extends Foo>"
            )
        }
        val responseType: Type = getParameterUpperBound(0, returnType)
        if (responseType !is ParameterizedType || responseType.rawType != BaseResponseBean::class.java) {
            throw IllegalArgumentException(
                "return type must be parameterized as BaseResponseBean<Foo> or BaseResponseBean<? extends Foo>"
            )
        }
        return object :
            CallAdapter<BaseResponseBean<Any>, Call<BaseResponseBean<Any>>> {
            override fun responseType(): Type = responseType

            override fun adapt(call: Call<BaseResponseBean<Any>>): Call<BaseResponseBean<Any>> {
                return ServiceResponseCall(call)
            }
        }


    }

    private class ServiceResponseCall<T>(val delegate: Call<BaseResponseBean<T>>) :
        Call<BaseResponseBean<T>> {

        override fun clone(): Call<BaseResponseBean<T>> =
            ServiceResponseCall(delegate.clone())

        override fun execute(): Response<BaseResponseBean<T>> =
            delegate.execute()

        override fun isExecuted(): Boolean = delegate.isExecuted

        override fun cancel() = delegate.cancel()

        override fun isCanceled(): Boolean = delegate.isCanceled

        override fun request(): Request = delegate.request()

        override fun timeout(): Timeout = delegate.timeout()

        override fun enqueue(callback: Callback<BaseResponseBean<T>>) {
            delegate.enqueue(object : Callback<BaseResponseBean<T>> {
                override fun onResponse(
                    call: Call<BaseResponseBean<T>>,
                    response: Response<BaseResponseBean<T>>
                ) {
                    response.body()?.let { baseResponseBean ->
                        ResponseError.of(
                            baseResponseBean.status,
                        )?.also { err ->
                            onFailure(call, err)
                            return
                        }
                    }
                    if (delegate.isCanceled) {
                        onFailure(call, IOException("Canceled"))
                    } else {
                        callback.onResponse(call, response)
                    }
                }

                override fun onFailure(
                    call: Call<BaseResponseBean<T>>,
                    e: Throwable
                ) {
                    callback.onFailure(call, e)
                }
            })
        }
    }
}