package br.com.fiap.ecoSafe.data.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {

    private const val URL = "https://api.iucnredlist.org/api/v4/"

    private val retrofit: Retrofit by lazy {
        val token = "Tbswz6LFLRHQACr5Teq52EiC2RWB1uQrZNnf"

        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(token))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl(URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val sisService: TaxaSisService by lazy {
        retrofit.create(TaxaSisService::class.java)
    }
}
