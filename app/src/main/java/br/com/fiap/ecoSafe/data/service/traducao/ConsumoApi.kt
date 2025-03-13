package br.com.fiap.ecoSafe.data.service.traducao


import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class ConsumoApi {
    private val client = OkHttpClient()

    fun obterDados(endereco: String): String {
        val request = Request.Builder()
            .url(endereco)
            .build()

        return try {
            val response = client.newCall(request).execute()
            response.body?.string() ?: throw IOException("Resposta vazia")
           // response.body()?.string() ?: throw IOException("Resposta vazia") esta comentei carlos para usar a api do gogole maps
        } catch (e: IOException) {
            throw RuntimeException("Erro ao consumir API", e)
        }
    }
}