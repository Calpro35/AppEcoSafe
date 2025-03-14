package br.com.fiap.ecoSafe.presentation.screens.layouts


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.presentation.components.Footer
import br.com.fiap.ecoSafe.presentation.components.HamburgerMenu
import br.com.fiap.ecosafe.R

@Composable
fun EndangeredSpecies(navController: NavController) {
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
                MainEndangered()
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
fun MainEndangered() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
       //conteudo da pagina aqui
    }
}