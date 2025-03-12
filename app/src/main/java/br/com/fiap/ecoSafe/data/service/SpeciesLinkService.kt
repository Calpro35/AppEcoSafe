package br.com.fiap.ecoSafe.data.service

import br.com.fiap.ecoSafe.data.model.ApiSpeciesLinkResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SpeciesLinkService {

    @GET("search?scientificname={scientific_name}&limit=1")
    fun getSpecie(
        @Path("scientific_name") scientificName: String
    ): Call<ApiSpeciesLinkResponse>
}