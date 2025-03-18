package br.com.fiap.ecoSafe.presentation.screens.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.presentation.components.CardItem
import br.com.fiap.ecoSafe.presentation.components.CardItemView
import br.com.fiap.ecoSafe.presentation.components.Footer
import br.com.fiap.ecoSafe.presentation.components.HamburgerMenu
import br.com.fiap.ecoSafe.presentation.components.SearchBar
import br.com.fiap.ecosafe.R


@Composable
fun Extinction(navController: NavController) {

    var isMenuOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Main content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ) {
            item {
                HeaderExtincion(onMenuClick = { isMenuOpen = true })
                Spacer(modifier = Modifier.height(5.dp))
                MainExtincion()
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
fun HeaderExtincion(onMenuClick: () -> Unit) {
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
                text = "Extinção",
                modifier = Modifier
                    .weight(1f)
                    .offset(x = 20.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                color = Color(0xFF35580C),
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
fun MainExtincion() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
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
                color = Color(0xFF35580C)
            )
            Text(
                text = "Animais",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2D2A2A)
            )
            Text(
                text = "Plantas",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2D2A2A)
            )
            Text(
                text = "Insetos",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2D2A2A)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 3.dp)
        ) {// Barra de Pesquisa
            SearchBar {}
        }
        Spacer(modifier = Modifier.height(2.dp))

        // Lista de Cards
        val items = listOf(
            CardItem("Onça-pintada", "", R.drawable.oncapintada),
            CardItem("Arara-azul", "", R.drawable.arara),
            CardItem("Tigre-de-sumatra", "", R.drawable.sumatra),
            CardItem("Borboleta-monarca", "", R.drawable.monarca),
            CardItem("Tartaruga-gigante", "", R.drawable.tartarugagigante),
            CardItem("Orquídea-fantasma", "", R.drawable.orquidea)
        )

        // Layout de duas colunas manualmente
        TwoColumnGrid(items = items)
    }
    Spacer(modifier = Modifier.height(10.dp))
}


@Composable
fun TwoColumnGrid(items: List<CardItem>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Divide a lista em pares para duas colunas
        items.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp), // Adiciona padding horizontal
                horizontalArrangement = Arrangement.SpaceBetween // Espaço entre os cards
            ) {
                // Primeiro item da linha
                if (rowItems.isNotEmpty()) {
                    CardItemView(
                        item = rowItems[0],
                        modifier = Modifier.weight(1f) // Ocupa metade da largura
                    )
                }

                // Segundo item da linha (se existir)
                if (rowItems.size > 1) {
                    Spacer(modifier = Modifier.width(10.dp)) // Espaço entre os cards
                    CardItemView(
                        item = rowItems[1],
                        modifier = Modifier.weight(1f) // Ocupa metade da largura
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp)) // Espaço entre as linhas
        }
    }
}