package br.com.fiap.ecoSafe.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import br.com.fiap.ecoSafe.ui.theme.RobotoFontFamily
import br.com.fiap.ecosafe.R

@Composable
fun Footer(navController: NavController) {
    // Obtém a rota atual da navegação
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.White)
            .border(width = 0.4.dp, color = Color.LightGray) // Linha divisora (borda superior)
            .padding(horizontal = 16.dp, vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ícone Home
        FooterIcon(
            painter = painterResource(id = R.drawable.home_ham),
            label = "Home",
            isSelected = currentRoute == "home_screen",
            onClick = {
                navController.navigate("home_screen") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )

        // Ícone Mapa
        FooterIcon(
            painter = painterResource(id = R.drawable.map_ham),
            label = "Mapa",
            isSelected = currentRoute == "mapa",
            onClick = {
                navController.navigate("mapa") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )

        // Ícone Explorar
        FooterIcon(
            painter = painterResource(id = R.drawable.camera_ham),
            label = "Explorar",
            isSelected = currentRoute == "camera_screen",
            onClick = {
                navController.navigate("camera_screen") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )

        // Ícone Extinção
        FooterIcon(
            painter = painterResource(id = R.drawable.book_ham),
            label = "Extinção",
            isSelected = currentRoute == "Extincao",
            onClick = {
                navController.navigate("Extincao") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )

        // Ícone Perfil
        FooterIcon(
            painter = painterResource(id = R.drawable.person_ham),
            label = "Perfil",
            isSelected = currentRoute == "perfil",
            onClick = {
                navController.navigate("perfil") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )
    }
}

@Composable
fun FooterIcon(
    painter: Painter,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() }, // Interação personalizada
                indication = null // Remove o efeito de sombra (ripple effect)
            ) {
                onClick() // Ação ao clicar
            }
            .padding(vertical = 2.dp) // Adiciona um padding vertical para melhorar o espaçamento
    ) {
        Icon(
            painter = painter,
            contentDescription = label,
            modifier = Modifier
                .padding(0.dp)
                .size(25.dp), // Tamanho do ícone
            tint = if (isSelected) Color(0xFF417505) else Color(0xFF696565) // Muda a cor do ícone quando selecionado
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = if (isSelected) Color(0xFF417505) else Color(0xFF696565), // Muda a cor do texto quando selecionado
            fontFamily = RobotoFontFamily,
            fontWeight = FontWeight.Normal,

        )
    }
}