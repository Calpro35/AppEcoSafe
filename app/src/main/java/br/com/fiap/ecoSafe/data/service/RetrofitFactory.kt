package br.com.fiap.ecoSafe.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val URL = "https://apiv3.iucnredlist.org/api/v3/species/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getAnimalService(): AnimalService {
        return retrofitFactory.create(AnimalService::class.java)
    }


}