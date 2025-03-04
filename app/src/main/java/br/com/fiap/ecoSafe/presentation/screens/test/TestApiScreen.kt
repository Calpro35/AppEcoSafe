package br.com.fiap.ecoSafe.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.fiap.ecoSafe.data.service.RetrofitFactory
import br.com.fiap.ecoSafe.data.model.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun TestApiScreen() {
    val animalInfo = remember { mutableStateOf<ApiResponse?>(null) }
    val isLoading = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(16.dp)) {
        Button(
            onClick = {
                isLoading.value = true
                fetchData(animalInfo) {
                    isLoading.value = false
                }
            }
        ) {
            Text("Fazer Requisição")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading.value) {
            CircularProgressIndicator()
        } else {
            animalInfo.value?.let { animal ->
                Text("Nome científico: ${animal.taxon.scientificName}")
                Text("Família: ${animal.taxon.family}")
                Text("Classe: ${animal.taxon.className}")
            } ?: Text("Clique no botão para buscar dados.")
        }
    }
}

fun fetchData(animalInfo: MutableState<ApiResponse?>, onComplete: () -> Unit) {
    val token = "Tbswz6LFLRHQACr5Teq52EiC2RWB1uQrZNnf"
    val sisId = 11058

    val call = RetrofitFactory.sisService.getTaxon(sisId, token) // Acessando corretamente

    call.enqueue(object : Callback<ApiResponse> {
        override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
            if (response.isSuccessful) {
                animalInfo.value = response.body()
                Log.d("API_RESPONSE", "Sucesso: ${response.body()}")
            } else {
                Log.e("API_RESPONSE", "Erro na resposta: ${response.code()}")
            }
            onComplete()
        }

        override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            Log.e("API_RESPONSE", "Falha na requisição", t)
            onComplete()
        }
    })
}

