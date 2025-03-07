package br.com.fiap.ecoSafe.data.service.traducao

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class DadosTraducao(
    @field:JsonAlias("responseData")
    @param:JsonAlias("responseData")
    val dadosResposta: DadosResposta = DadosResposta()
)
