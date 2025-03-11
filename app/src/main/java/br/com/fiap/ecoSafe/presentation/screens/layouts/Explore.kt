package br.com.fiap.ecoSafe.presentation.screens.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.presentation.components.Footer

@Composable
fun Explore(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Conteúdo principal da tela
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter) // Alinha o Row no topo da tela
        ) {
            Text(
                text = "Vai abrir a câmera"
            )
        }

        // Footer fixo na parte inferior
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            Footer(navController = navController)
        }
    }
}