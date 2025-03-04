package br.com.fiap.ecoSafe.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("sis_id") val sisId: Int,
    @SerializedName("taxon") val taxon: Taxon,
    @SerializedName("assessments") val assessments: List<Assessment>
)