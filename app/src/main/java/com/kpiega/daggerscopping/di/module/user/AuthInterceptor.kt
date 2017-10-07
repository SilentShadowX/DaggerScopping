package com.kpiega.daggerscopping.di.module.user

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(val secret: String? = "") : Interceptor {

    val AUTH_HEADER: String = "TokenHeader"

    override fun intercept(chain: Interceptor.Chain): Response {
        val orginalRequest = chain.request()
        val authRequest = orginalRequest.newBuilder()

        val secretToken = secret
        if (orginalRequest.header(AUTH_HEADER) == null) {
            if (secretToken == null)
                authRequest.addHeader(AUTH_HEADER, "")
            else
                authRequest.addHeader(AUTH_HEADER, secret)
        }

        return chain.proceed(authRequest.build())
    }
}