package br.com.fiap.ecoSafe.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponseAnimal(
    @SerializedName("name") val name: String = "",
    @SerializedName("result") val result: List<Animal> = emptyList()
) {

}