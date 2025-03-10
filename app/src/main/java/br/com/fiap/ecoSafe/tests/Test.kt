
//package br.com.fiap.ecoSafe.tests
//
//import androidx.compose.runtime.MutableState
////import br.com.fiap.ecoSafe.data.model.ApiResponseAnimal
//import br.com.fiap.ecoSafe.data.service.RetrofitFactory
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//import java.sql.DriverManager.println
//
//var listaAnimais: List<ApiResponseAnimal> = emptyList<ApiResponseAnimal>()
//
//fun testBack(listaAnimaisState: MutableState<List<ApiResponseAnimal>>){
//    //Criação do objeto para o método da interface
//    var call = RetrofitFactory().getAnimalService().getAnimal("loxodonta%20africana") //Pede a resposta
//    call.enqueue(object : Callback<List<ApiResponseAnimal>> {
//        //Resposta
//        override fun onResponse(
//            call: Call<List<ApiResponseAnimal>>,
//            response:
//            Response<List<ApiResponseAnimal>>
//        ) {
//            //Atribuindo o corpo do retorno da chamada à lista
//            response.body()?.let {
//                listaAnimais = it
//            } ?: run {
//                println("Erro: resposta vazia do servidor!")
//            }
//            //listaEnderecoState = response.body()!!
//        }
//
//        override fun onFailure(call: Call<List<ApiResponseAnimal>>, t: Throwable) {
//            //TODO("Not yet implemented")
//            t.printStackTrace() // Mostra o erro no Logcat
//        }
//    })
//    println(listaAnimais)
//    for (animal in listaAnimais)
//    {
//        println(animal.name)
//        println(animal.result)
//    }
//}

package br.com.fiap.ecoSafe.tests

import androidx.compose.runtime.MutableState
//import br.com.fiap.ecoSafe.data.model.ApiResponseAnimal
import br.com.fiap.ecoSafe.data.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.sql.DriverManager.println
/*
var listaAnimais: List<ApiResponseAnimal> = emptyList<ApiResponseAnimal>()

fun testBack(listaAnimaisState: MutableState<List<ApiResponseAnimal>>){
    //Criação do objeto para o método da interface
    var call = RetrofitFactory().getAnimalService().getAnimal("loxodonta%20africana") //Pede a resposta
    call.enqueue(object : Callback<List<ApiResponseAnimal>> {
        //Resposta
        override fun onResponse(
            call: Call<List<ApiResponseAnimal>>,
            response:
            Response<List<ApiResponseAnimal>>
        ) {
            //Atribuindo o corpo do retorno da chamada à lista
            response.body()?.let {
                listaAnimais = it
            } ?: run {
                println("Erro: resposta vazia do servidor!")
            }
            //listaEnderecoState = response.body()!!
        }

        override fun onFailure(call: Call<List<ApiResponseAnimal>>, t: Throwable) {
            //TODO("Not yet implemented")
            t.printStackTrace() // Mostra o erro no Logcat
        }
    })
    println(listaAnimais)
    for (animal in listaAnimais)
    {
        println(animal.name)
        println(animal.result)
    }
}*/

