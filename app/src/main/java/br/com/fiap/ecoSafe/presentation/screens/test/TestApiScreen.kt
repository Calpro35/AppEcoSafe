package br.com.fiap.ecoSafe.presentation.screens.test

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.data.service.RetrofitFactory
import br.com.fiap.ecoSafe.data.model.*
import br.com.fiap.ecoSafe.data.repository.SpeciesRepository
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
    var specie by remember {
        mutableStateOf(
            Specie(
                "", "", "",
                "", "",
                "", "", false, false, false,
                false, false, false
            )
        )
    }

    val speciesListState = remember { mutableStateOf<List<Specie>>(emptyList()) }

    LaunchedEffect(Unit) {
        isLoading.value = true
        val species = speciesRepository.getAllSpecies(context)
        speciesListState.value = species
        isLoading.value = false
    }

    Column(modifier = Modifier.padding(16.dp), horizontalAlignment =
    Alignment.CenterHorizontally) {
        if (isLoading.value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            Text(text = "Extinção", fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black)
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn {
                items(speciesListState.value) { specie ->
                    SpecieCard(specie)
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    }
}

@Composable
fun SpecieCard(specie: Specie){
    var specieSearched = remember { mutableStateOf(SpecieMain())}
    var situation = ""
    if(specie.ew)
    {
        situation = "Extinta na Natureza"
    }
    else if(specie.cr)
    {
        situation = "Criticamente em Perigo"
    }
    else if(specie.en)
    {
        situation = "Em Perigo"
    }
    else if(specie.vu)
    {
        situation = "Vulnerável"
    }
    else if(specie.re)
    {
        situation = "Extinta no Brasil"
    }
    else if(specie.ex)
    {
        situation = "Extinta"
    }
    Card(colors = CardDefaults.cardColors(Color.DarkGray),
        modifier = Modifier.padding(bottom = 8.dp),
        onClick = {
            getSpecieDetails(
                specie, specieSearched
            )
        }
    )
    {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            Text(
                text = "Espécie: ${specie.especie}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            if(specieSearched.value.kingdom.isNotEmpty()){
                Text(
                    modifier = Modifier.alpha(1f),
                    text = "Reino: ${specieSearched.value.kingdom}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
                Text(
                    modifier = Modifier.alpha(1f),
                    text = "Grupo Taxonômico: ${specie.grupoTaxonomico}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
                Text(
                    modifier = Modifier.alpha(1f),
                    text = "Situação: $situation",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
                Text(
                    modifier = Modifier.alpha(1f),
                    text = "Família: ${specieSearched.value.family}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
                Text(
                    modifier = Modifier.alpha(1f),
                    text = "Gênero: ${specieSearched.value.genus}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
            }
            else{
                Text(
                    modifier = Modifier.alpha(1f),
                    text = "Clique para buscar",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
            }
        }
    }
}

fun getSpecieDetails(specie: Specie, specieSearched: MutableState<SpecieMain>) {
    val call = RetrofitFactory.getSpeciesLinkService().getNewSpecie(specie.especie)
    call.enqueue(object : Callback<ApiSpeciesLinkResponse> {
        override fun onResponse(
            call: Call<ApiSpeciesLinkResponse>,
            response: Response<ApiSpeciesLinkResponse>
        ) {
            response.body()?.let{ newSpecie ->
                if(newSpecie.features.isNotEmpty()){
                    specieSearched.value = newSpecie.features[0].properties
                }
                else{
                    specieSearched.value = SpecieMain("Sem Dados Disponíveis",
                        "Sem Dados Disponíveis", "Sem Dados Disponíveis",
                        "Sem Dados Disponíveis", "Sem Dados Disponíveis")
                }
            } ?: run {
                println("Erro: resposta vazia do servidor!")
                return
            }
        }
        override fun onFailure(call: Call<ApiSpeciesLinkResponse>, t: Throwable) {
            t.printStackTrace()
        }
    })
}

/*

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
}*/