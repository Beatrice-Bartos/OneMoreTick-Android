package com.example.onemoretick.networking

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("x-device-type", Build.DEVICE)
                .build()
        )
    }
}
