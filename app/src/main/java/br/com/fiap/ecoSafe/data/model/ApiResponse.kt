package br.com.fiap.ecoSafe.data.model

import com.google.gson.annotations.SerializedName

data class ApiTaxSisResponse(
    @SerializedName("sis_id") val sisId: Int,
    @SerializedName("taxon") val taxon: Taxon,
    @SerializedName("assessments") val assessments: List<Assessment>
)

data class ApiKingdomResponse(
    @SerializedName("kingdom_names") val kingdomNames: List<String>
)

data class ApiResponseAssessment(
    @SerializedName("assessments") val assessments: List<Assessment>
)

data class ApiResponsePhylum(
    @SerializedName("phylum_names") var filos: List<String>
)

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

data class Assessment(
    @SerializedName("year_published") val yearPublished: String,
    @SerializedName("latest") val latest: Boolean,
    @SerializedName("possibly_extinct") val possiblyExtinct: Boolean,
    @SerializedName("possibly_extinct_in_the_wild") val possiblyExtinctInTheWild: Boolean,
    @SerializedName("sis_taxon_id") val sisTaxonId: Int,
    @SerializedName("url") val url: String,
    @SerializedName("assessment_id") val assessmentId: Int,
    @SerializedName("scopes") val scopes: List<Scope>
)

data class Scope(
    @SerializedName("description") val description: Description
)

data class Description(
    @SerializedName("en") val en: String
)

data class ApiSpeciesLinkResponse(
    val features: List<Features>
)

data class Features(
    val properties: SpecieMain
)
