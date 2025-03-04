package br.com.fiap.ecoSafe.data.service

import br.com.fiap.ecoSafe.data.model.ApiResponseAnimal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimalService {

    //?token=9bb4facb6d23f48efbf424bb05c0c1ef1cf6f468393bc745d42179ac4aca5fee
    @GET("{animal}?token=9bb4facb6d23f48efbf424bb05c0c1ef1cf6f468393bc745d42179ac4aca5fee")
    fun getAnimal(@Path("animal") animal: String): Call<List<ApiResponseAnimal>>

    /*
    //https://viacep.com.br/ws/RS/Porto%20Alegre/Domingos/json/
    @GET("{uf}/{cidade}/{rua}/json/")
    fun getEnderecos(
        @Path("uf") uf: String,
        @Path("cidade") cidade: String,
        @Path("rua") rua: String
    ): Call<List<Endereco>>*/
}