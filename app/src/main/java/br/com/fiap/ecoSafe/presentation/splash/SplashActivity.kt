package br.com.fiap.ecoSafe.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.data.model.ApiResponseAnimal
import br.com.fiap.ecoSafe.presentation.navigation.Screen
import br.com.fiap.ecoSafe.tests.testBack
import br.com.fiap.ecosafe.R
import kotlinx.coroutines.delay

@Composable
fun SplashActivity(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(Screen.Home.route) {
            popUpTo(0) // Remove a Splash Screen da pilha
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,

    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(190.dp)
                .aspectRatio(1f) // Mantém a proporção (quadrado)
        )
        //Código de Teste. Comentar se necessário para facilitar dev do Front-End
        /*val listaAnimaisState = remember { mutableStateOf<List<ApiResponseAnimal>>(emptyList()) }
        testBack(listaAnimaisState)
        ListaAnimaisUI(listaAnimaisState.value)*/
    }
}
/*
@Composable
fun ListaAnimaisUI(listaAnimais: List<ApiResponseAnimal>) {
    Column {
        Text(text = "$listaAnimais")
        listaAnimais.forEach { animal ->
            Text(text = "Nome Científico: ${animal.name}")
            animal.result.forEach {
                Text(text = "Nome Comum: ${it.nomeComum}")
                Text(text = "Reino: ${it.reino}")
                Text(text = "Situação: ${it.situacao}")
                Text(text = "Informações: ${it.informacoes}")
            }
        }
    }
}*/
