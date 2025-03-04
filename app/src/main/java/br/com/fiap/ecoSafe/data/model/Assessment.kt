package br.com.fiap.ecoSafe.data.model

import com.google.gson.annotations.SerializedName

data class Assessment(
    @SerializedName("year_published") val yearPublished: String,
    @SerializedName("latest") val latest: Boolean,
    @SerializedName("possibly_extinct") val possiblyExtinct: Boolean,
    @SerializedName("url") val url: String
)