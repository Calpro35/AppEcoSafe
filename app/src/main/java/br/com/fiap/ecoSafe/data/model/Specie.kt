package br.com.fiap.ecoSafe.data.model

data class Specie(
    val id: String,
    val grupoTaxonomico: String,
    val ordem: String,
    val familia: String,
    val especie: String,
    val categoria2014: String?,
    val categoria2021: String?,
    val ew: Boolean,
    val cr: Boolean,
    val en: Boolean,
    val vu: Boolean,
    val re: Boolean,
    val ex: Boolean
)
