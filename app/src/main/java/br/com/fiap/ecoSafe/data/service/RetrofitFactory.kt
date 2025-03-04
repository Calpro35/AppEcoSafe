package br.com.fiap.ecoSafe.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    private const val URL = "https://api.iucnredlist.org/api/v4/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val sisService: TaxaSisService by lazy {
        retrofit.create(TaxaSisService::class.java)
    }
}
