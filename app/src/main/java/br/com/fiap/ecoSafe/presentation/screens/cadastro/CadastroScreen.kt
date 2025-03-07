package br.com.fiap.ecoSafe.presentation.screens.cadastro

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.presentation.navigation.Screen
import br.com.fiap.ecoSafe.ui.theme.RobotoFontFamily

@Composable
fun CadastroScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Tela de Cadastro")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate(Screen.Home.route) }) {
            Text(
                "Voltar para Home",
                fontFamily = RobotoFontFamily
            )
        }
    }
}