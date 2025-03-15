package br.com.fiap.ecoSafe.presentation.screens.layouts


import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.data.model.ApiSpeciesLinkResponse
import br.com.fiap.ecoSafe.data.model.Specie
import br.com.fiap.ecoSafe.data.model.SpecieMain
import br.com.fiap.ecoSafe.data.service.RetrofitFactory
import br.com.fiap.ecoSafe.presentation.components.Footer
import br.com.fiap.ecoSafe.presentation.components.HamburgerMenu
import br.com.fiap.ecoSafe.presentation.screens.test.speciesRepository
import br.com.fiap.ecosafe.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun EndangeredSpecies(navController: NavController, context: Context) {
    var isMenuOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Main content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ) {
            item {
                HeaderEndangered(
                    onMenuClick = { isMenuOpen = true },
                    BackClose = { navController.navigate("home_Screen")}
                )
                Spacer(modifier = Modifier.height(5.dp))
                MainEndangered(context)
                Spacer(modifier = Modifier.height(15.dp))

            }
        }

        // Hamburger Menu
        if (isMenuOpen) {
            HamburgerMenu(
                onCloseClick = { isMenuOpen = false },
                onItemClick = { destination ->
                    navController.navigate(destination)
                    isMenuOpen = false
                }
            )
        }

        // Fixed Footer at the bottom
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            Footer(navController = navController)
        }
    }
}


@Composable
fun HeaderEndangered(
    onMenuClick: () -> Unit,
    BackClose: () -> Unit) {

   Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ícone de voltar à esquerda

            IconButton(
                onClick = BackClose, // Ação ao clicar na seta de voltar
                modifier = Modifier.size(48.dp) // Tamanho do ícone aumentado
            ) {
                Icon(
                    imageVector = Icons.Default.Close, // Ícone de seta de voltar
                    contentDescription = "Fechar",
                    tint = Color.Black // Cor do ícone
                )
            }
            Text(
                text = "Espécies Ameaçadas",
                modifier = Modifier
                    .weight(1f),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                color = Color(0xFF2D2A2A),
            )

            IconButton(
                onClick = onMenuClick,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.menu_ham),
                    contentDescription = "Abrir Menu",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}


@Composable
fun MainEndangered(context: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val isLoading = remember { mutableStateOf(false) }
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
                LazyColumn(modifier = Modifier.height(500.dp))  {
                    items(speciesListState.value) { specie ->
                        SpecieCard(specie)
                        Spacer(modifier = Modifier.height(5.dp))
                    }
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