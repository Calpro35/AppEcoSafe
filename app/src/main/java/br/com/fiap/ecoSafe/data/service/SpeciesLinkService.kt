package br.com.fiap.ecoSafe.data.service

import br.com.fiap.ecoSafe.data.model.ApiSpeciesLinkResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpeciesLinkService {

    @GET("search")
    fun getSpecie(
        @Query("scientificname") scientificName: String,
        @Query("limit") limit: Int = 1
    ): Call<ApiSpeciesLinkResponse>
}