package br.com.fiap.ecoSafe.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
//import br.com.fiap.ecoSafe.data.model.ApiResponse
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.presentation.navigation.Screen
import br.com.fiap.ecosafe.R
import kotlinx.coroutines.delay

@Composable
fun SplashActivity(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(Screen.Login.route) {
            popUpTo(0) // Remove a Splash Screen da pilha
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,

        ) {
        Image(
            painter = painterResource(id = R.drawable.ecoficial),
            contentDescription = "Logo",
            modifier = Modifier
                .size(100.dp)
                .aspectRatio(1f) // Mantém a proporção (quadrado)
                .offset(x = 2.dp, y = (-17).dp)
        )



//           //Código de Teste. Comentar se necessário para facilitar dev do Front-End
//          val listaAnimaisState = remember { mutableStateOf<List<ApiResponse>>(emptyList()) }
//             testBack(listaAnimaisState)
//            ListaAnimaisUI(listaAnimaisState.value)
     }
}

//@Composable
//fun ListaAnimaisUI(listaAnimais: List<ApiResponse>) {
//    Column {
//        Text(text = "$listaAnimais")
//        listaAnimais.forEach { animal ->
//            Text(text = "Nome Científico: ${animal.sisId}")
//            animal.assessments.forEach {
//                Text(text = "Nome Comum: ${it.url}")
//                Text(text = "Reino: ${ it.possiblyExtinct}")
//            }
//        }
//    }
//}