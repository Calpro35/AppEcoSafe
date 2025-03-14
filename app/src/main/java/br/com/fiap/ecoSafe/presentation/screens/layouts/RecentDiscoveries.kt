package br.com.fiap.ecoSafe.presentation.screens.layouts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.presentation.components.Footer
import br.com.fiap.ecoSafe.presentation.components.AnimalCarousel
import br.com.fiap.ecoSafe.presentation.components.HamburgerMenu
import br.com.fiap.ecoSafe.presentation.components.SearchBar
import br.com.fiap.ecosafe.R

@Composable
fun RecentDiscoveries(navController: NavController) {
    var isMenuOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Conteúdo principal da tela
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ) {
            item {
                HeaderDiscovery(
                    onMenuClick = { isMenuOpen = true },
                    onBackClick = { navController.navigate("home_Screen") }
                )
                Spacer(modifier = Modifier.height(15.dp))
                MainRecovery()
            }
        }
        // Menu Hambúrguer
        if (isMenuOpen) {
            HamburgerMenu(
                onCloseClick = { isMenuOpen = false },
                onItemClick = { destination ->
                    navController.navigate(destination)
                    isMenuOpen = false
                }
            )
        }

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
fun HeaderDiscovery(
    onMenuClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ícone de voltar à esquerda
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Voltar",
                tint = Color.Black
            )
        }

        // Spacer para empurrar o ícone do menu hambúrguer para a direita
        Spacer(modifier = Modifier.weight(1f))

        // Ícone do menu hambúrguer à direita
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




@Composable
fun MainRecovery(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
             horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Barra de Pesquisa
        SearchBar {}

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Descobertas Recentes",
            modifier = Modifier,
            fontSize = 18.sp,
            color = Color(0xFF1B5E20),
            fontWeight = FontWeight.Bold
        )

        // Componente AnimalCarousel
        AnimalCarousel()
    }
}


