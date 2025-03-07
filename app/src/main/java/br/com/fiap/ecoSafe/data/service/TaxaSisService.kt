package br.com.fiap.ecoSafe.data.service

import br.com.fiap.ecoSafe.data.model.ApiTaxSisResponse
import br.com.fiap.ecoSafe.data.model.ApiKingdomResponse
import br.com.fiap.ecoSafe.data.model.ApiResponseAssessment
import br.com.fiap.ecoSafe.data.model.ApiResponsePhylum
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface TaxaSisService {

    @GET("taxa/sis/{sis_id}")
    fun getTaxon(
        @Path("sis_id") sisId: Int
    ): Call<ApiTaxSisResponse>

    @GET("taxa/kingdom")
    fun getAllKingdoms(): Call<ApiKingdomResponse>

    @GET("taxa/kingdom/{kingdom}")
    fun getKingdomData(
        @Path("kingdom") kingdom: String
    ): Call<ApiResponseAssessment>

    @GET("taxa/phylum")
    fun getPhylums()
    : Call<ApiResponsePhylum>

    @GET("taxa/phylum/{phylum}")
    fun getPhylumData(
        @Path("phylum") filo: String
    ): Call<ApiResponseAssessment>
}
