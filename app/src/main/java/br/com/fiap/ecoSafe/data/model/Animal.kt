package br.com.fiap.ecoSafe.data.model

import com.google.gson.annotations.SerializedName

data class Animal(
    @SerializedName("scientific_name") val nomeCientifico: String = "",
    @SerializedName("kingdom") val reino: String = "",
    @SerializedName("main_common_name") val nomeComum: String = "",
    @SerializedName("population_trend") val situacao: String = "",
    @SerializedName("amended_reason") val informacoes: String = ""
)
