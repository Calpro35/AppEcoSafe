package br.com.fiap.ecoSafe.data.model

import com.google.gson.annotations.SerializedName

data class Taxon(
    @SerializedName("sis_id") val sisId: Int,
    @SerializedName("scientific_name") val scientificName: String,
    @SerializedName("kingdom_name") val kingdom: String,
    @SerializedName("phylum_name") val phylum: String,
    @SerializedName("class_name") val className: String,
    @SerializedName("order_name") val order: String,
    @SerializedName("family_name") val family: String,
    @SerializedName("genus_name") val genus: String,
    @SerializedName("species_name") val species: String
)