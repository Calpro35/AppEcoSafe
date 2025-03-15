package br.com.fiap.ecoSafe.data.service

import br.com.fiap.ecoSafe.data.model.ApiSpeciesLinkResponse
import br.com.fiap.ecoSafe.data.model.SpecieMain
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpeciesLinkService {

    @GET("search")
    fun getSpecie(
        @Query("scientificname") scientificname: String,
        @Query("limit") limit: Int = 1,
        @Query("apikey") apikey: String = "d0x9WY2cy4ngmC798lZ9"
    ): Call<ApiSpeciesLinkResponse>

    @GET("search")
    fun getNewSpecie(
        @Query("scientificname") scientificname: String,
        @Query("limit") limit: Int = 1,
        @Query("apikey") apikey: String = "d0x9WY2cy4ngmC798lZ9"
    ): Call<ApiSpeciesLinkResponse>

}