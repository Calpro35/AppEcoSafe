package br.com.fiap.ecoSafe.data.service

import com.google.gson.Gson
//import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object RetrofitFactory {

    private const val URL = "https://api.iucnredlist.org/api/v4/"

    private const val SPECIES_LINK_URL = "https://specieslink.net/ws/1.0/"

    /*private val retrofit: Retrofit by lazy {
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
    }*/

    private val retrofitFactorySpecies = Retrofit
        .Builder()
        .baseUrl(SPECIES_LINK_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getSpeciesLinkService(): SpeciesLinkService {
        return retrofitFactorySpecies.create(SpeciesLinkService::class.java)
    }

    /*private val retrofitSpecies: Retrofit by lazy{
        val token = "d0x9WY2cy4ngmC798lZ9"

        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(token))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl(SPECIES_LINK_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }*/
}
