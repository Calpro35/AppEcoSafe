package br.com.fiap.ecoSafe.tests

import androidx.compose.runtime.MutableState
import br.com.fiap.ecoSafe.data.model.ApiResponse
import br.com.fiap.ecoSafe.data.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.sql.DriverManager.println

var listaAnimais: List<ApiResponse> = emptyList<ApiResponse>()

fun testBack(listaAnimaisState: MutableState<List<ApiResponse>>){
    //Criação do objeto para o método da interface
    /*var call = RetrofitFactory.sisService.getTaxon("loxodonta%20africana") //Pede a resposta
    call.enqueue(object : Callback<List<ApiResponse>> {
        //Resposta
        override fun onResponse(
            call: Call<List<ApiResponse>>,
            response:
            Response<List<ApiResponse>>
        ) {
            //Atribuindo o corpo do retorno da chamada à lista
            response.body()?.let {
                listaAnimais = it
            } ?: run {
                println("Erro: resposta vazia do servidor!")
            }
            //listaEnderecoState = response.body()!!
        }

        override fun onFailure(call: Call<List<ApiResponse>>, t: Throwable) {
            //TODO("Not yet implemented")
            t.printStackTrace() // Mostra o erro no Logcat
        }
    })
    println(listaAnimais)
    for (animal in listaAnimais)
    {
        println(animal.taxon)
        println(animal.assessments)
    }*/
}