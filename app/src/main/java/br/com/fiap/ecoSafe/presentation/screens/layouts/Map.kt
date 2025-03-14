package br.com.fiap.ecoSafe.presentation.screens.layouts


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.presentation.components.BannerWithMap
import br.com.fiap.ecoSafe.presentation.components.Footer
import br.com.fiap.ecoSafe.presentation.components.HamburgerMenu
import br.com.fiap.ecoSafe.presentation.components.SearchBar
import br.com.fiap.ecoSafe.ui.theme.OpensSansFontFamily

import br.com.fiap.ecosafe.R

@Composable
fun Mapa(navController: NavController) {
    var isMenuOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Conteúdo principal da tela
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ) {
            item {
                HeaderMap(onMenuClick = { isMenuOpen = true })
                Spacer(modifier = Modifier.height(24.dp))
                MainMap()
                FaunaLocalScreen()
                Spacer(modifier = Modifier.height(24.dp))

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

        // Footer
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
fun HeaderMap(onMenuClick: () -> Unit) {
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
            Text(
                text = "Mapa",
                modifier = Modifier
                    .weight(1f)
                    .offset(x = 22.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2D2A2A)
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
fun MainMap(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar {}
    }
}

@Composable
fun FaunaLocalScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        BannerWithMap() // Para exibir um mapa

        Spacer(modifier = Modifier.height(16.dp))
        // Títulos
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "Todos",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .offset(y = (-3).dp),
                color = Color(0xFF35580C))
            Text(
                text = "Em Perigo",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2D2A2A))
            Text(
                text = "Vuneravel",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2D2A2A))

        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        )
        {
            Text(
                text ="Explore a biodiversidade em diferentes regiões do Brasil",
                fontSize = 15.sp,
                fontFamily = OpensSansFontFamily,
                color = Color.Gray

            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Descobertas Recentes",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF35580C),
            modifier = Modifier.offset(x = 15.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))





    }
}


