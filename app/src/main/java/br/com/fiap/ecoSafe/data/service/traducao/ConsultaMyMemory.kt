package br.com.fiap.ecoSafe.data.service.traducao

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import java.net.URLEncoder

object ConsultaMyMemory {
    fun obterTraducao(text: String?): String {
        val mapper = ObjectMapper()

        val consumo = ConsumoApi()

        val texto = URLEncoder.encode(text)
        val langpair = URLEncoder.encode("en|pt-br")

        val url =
            "https://api.mymemory.translated.net/get?q=$texto&langpair=$langpair"

        val json: String = consumo.obterDados(url)

        val traducao: DadosTraducao
        try {
            traducao = mapper.readValue(json, DadosTraducao::class.java)
        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }

        return traducao.dadosResposta.textoTraduzido
    }
}