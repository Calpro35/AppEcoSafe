package br.com.fiap.ecoSafe.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.data.service.RetrofitFactory
import br.com.fiap.ecoSafe.data.model.*
import br.com.fiap.ecoSafe.data.repository.SpeciesRepository
import br.com.fiap.ecoSafe.data.repository.UserRepository
import br.com.fiap.ecoSafe.data.service.traducao.ConsultaMyMemory.obterTraducao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val speciesRepository = SpeciesRepository()

@Composable
fun TestApiScreen(navController: NavController, context: Context) {
    val taxonInfo = remember { mutableStateOf<ApiTaxSisResponse?>(null) }
    val kingdoms = remember { mutableStateOf<List<String>?>(null) }
    val kingdomData = remember { mutableStateOf<ApiResponseAssessment?>(null) }
    val phylums = remember { mutableStateOf<List<String>?>(null) }
    val phylumData = remember { mutableStateOf<ApiResponseAssessment?>(null) }
    val specieData = remember { mutableStateOf<ApiSpeciesLinkResponse?>(null) }
    val isLoading = remember { mutableStateOf(false) }

    var sisId by remember { mutableStateOf("") }
    var kingdom by remember { mutableStateOf("") }
    var phylum by remember { mutableStateOf("") }
    var commonName = remember { mutableStateOf("") }
    var specie by remember { mutableStateOf(Specie("", "", "",
        "", "",
    "", "",false, false, false,
        false, false, false)) }

    Column(modifier = Modifier.padding(16.dp)) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                /*OutlinedTextField(
                    value = sisId,
                    onValueChange = { sisId = it },
                    label = { Text("ID do Taxon (sis_id)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        isLoading.value = true
                        fetchTaxon(sisId.toIntOrNull(), taxonInfo) { isLoading.value = false }
                    },
                    enabled = sisId.isNotBlank()
                ) {
                    Text("Buscar Taxon")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        isLoading.value = true
                        fetchAllKingdoms(kingdoms) { isLoading.value = false }
                    }
                ) {
                    Text("Buscar Todos os Reinos")
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = kingdom,
                    onValueChange = { kingdom = it },
                    label = { Text("Nome do Reino") },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        isLoading.value = true
                        fetchKingdomData(kingdom, kingdomData) { isLoading.value = false }
                    },
                    enabled = kingdom.isNotBlank()
                ) {
                    Text("Buscar Dados do Reino")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        isLoading.value = true
                        fetchAllPhylums(phylums) { isLoading.value = false }
                    }
                ) {
                    Text("Buscar Todos os Filos")
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = phylum,
                    onValueChange = { phylum = it },
                    label = { Text("Nome do Filo") },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        isLoading.value = true
                        fetchPhylumData(phylum, phylumData) { isLoading.value = false }
                    },
                    enabled = phylum.isNotBlank()
                ) {
                    Text("Buscar Dados do Filo")
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (isLoading.value) {
                    CircularProgressIndicator()
                }

                taxonInfo.value?.let { taxon ->
                    Text("Taxon encontrado: ${taxon.taxon.scientificName}")
                }

                kingdoms.value?.let {
                    Text("Reinos disponíveis: ${it.joinToString(", ")}")
                }

                kingdomData.value?.let {
                    Text("Reino $kingdom possui ${it.assessments.size} avaliações.")
                }

                phylums.value?.let {
                    Text("Filos disponíveis: ${it.joinToString(", ")}")
                }

                phylumData.value?.let {
                    Text("Filo $phylum possui ${it.assessments.size} avaliações.")
                }*/

                OutlinedTextField(
                    value = commonName.value,
                    onValueChange = { commonName.value = it },
                    label = { Text("ID do Taxon (sis_id)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        //isLoading.value = true
                        fetchSpeciesData(context, commonName.value, specieData) {
                            //isLoading.value = false
                        }
                    },
                    //enabled = sisId.isNotBlank()
                ) {
                    Text("Buscar Taxon")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Espécie: ${specie.especie}")
                Text("Família: ${specie.familia}")
                Text("Ameaça: ${specie.cr}")
            }
        }
    }
}

fun fetchTaxon(sisId: Int?, taxonInfo: MutableState<ApiTaxSisResponse?>, onComplete: () -> Unit) {
    if (sisId == null) return

    val call = RetrofitFactory.sisService.getTaxon(sisId)
    call.enqueue(object : Callback<ApiTaxSisResponse> {
        override fun onResponse(call: Call<ApiTaxSisResponse>, response: Response<ApiTaxSisResponse>) {
            taxonInfo.value = response.body()
            onComplete()
        }
        override fun onFailure(call: Call<ApiTaxSisResponse>, t: Throwable) {
            onComplete()
        }
    })
}

fun fetchAllKingdoms(kingdoms: MutableState<List<String>?>, onComplete: () -> Unit) {
    val call = RetrofitFactory.sisService.getAllKingdoms()
    call.enqueue(object : Callback<ApiKingdomResponse> {
        override fun onResponse(call: Call<ApiKingdomResponse>, response: Response<ApiKingdomResponse>) {
            kingdoms.value = response.body()?.kingdomNames
            onComplete()
        }
        override fun onFailure(call: Call<ApiKingdomResponse>, t: Throwable) {
            onComplete()
        }
    })
}

fun fetchKingdomData(kingdom: String, kingdomData: MutableState<ApiResponseAssessment?>, onComplete: () -> Unit) {
    val call = RetrofitFactory.sisService.getKingdomData(kingdom)
    call.enqueue(object : Callback<ApiResponseAssessment> {
        override fun onResponse(call: Call<ApiResponseAssessment>, response: Response<ApiResponseAssessment>) {
            kingdomData.value = response.body()

            //Código de Exemplo para uso posterior da tradução
            /*CoroutineScope(Dispatchers.IO).launch {
                val traducao = obterTraducao("Chair and Mollusc RLA Coordinator")
                //A traducao retornada é do tipo String
                exampleVariable.value  = traducao
            }*/
            onComplete()
        }
        override fun onFailure(call: Call<ApiResponseAssessment>, t: Throwable) {
            onComplete()
        }
    })
}

fun fetchAllPhylums(phylums: MutableState<List<String>?>, onComplete: () -> Unit) {
    val call = RetrofitFactory.sisService.getPhylums()
    call.enqueue(object : Callback<ApiResponsePhylum> {
        override fun onResponse(call: Call<ApiResponsePhylum>, response: Response<ApiResponsePhylum>) {
            phylums.value = response.body()?.filos
            onComplete()
        }
        override fun onFailure(call: Call<ApiResponsePhylum>, t: Throwable) {
            onComplete()
        }
    })
}

fun fetchPhylumData(phylum: String, phylumData: MutableState<ApiResponseAssessment?>, onComplete: () -> Unit) {
    val call = RetrofitFactory.sisService.getPhylumData(phylum)
    call.enqueue(object : Callback<ApiResponseAssessment> {
        override fun onResponse(call: Call<ApiResponseAssessment>, response: Response<ApiResponseAssessment>) {
            phylumData.value = response.body()
            onComplete()
        }
        override fun onFailure(call: Call<ApiResponseAssessment>, t: Throwable) {
            onComplete()
        }
    })
}

fun fetchSpeciesData(context: Context, commonName: String, speciesData: MutableState<ApiSpeciesLinkResponse?>, onComplete: () -> Unit){
    val specie = speciesRepository.getSpecieByName( context, commonName)
    if (specie != null) {
        println(specie.especie)
    }
    onComplete()
    //val call = RetrofitFactory.speciesLinkService.getSpecie()
}
