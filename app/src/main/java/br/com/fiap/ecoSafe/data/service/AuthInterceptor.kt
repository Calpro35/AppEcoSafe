package br.com.fiap.ecoSafe.data.service

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", " $token")
            .build()
        return chain.proceed(newRequest)
    }
}
