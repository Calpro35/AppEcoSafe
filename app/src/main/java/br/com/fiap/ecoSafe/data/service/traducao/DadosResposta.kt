package br.com.fiap.ecoSafe.data.service.traducao

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class DadosResposta(
    @field:JsonAlias("translatedText")
    @param:JsonAlias("translatedText")
    val textoTraduzido: String = "" // Valor padrão para evitar problemas na desserialização
)
