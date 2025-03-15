package br.com.fiap.ecoSafe.presentation.screens.layouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import br.com.fiap.ecoSafe.presentation.components.AnimalCard
import br.com.fiap.ecoSafe.presentation.components.BannerWithMap
import br.com.fiap.ecoSafe.presentation.components.Footer
import br.com.fiap.ecoSafe.presentation.components.HamburgerMenu
import br.com.fiap.ecoSafe.presentation.components.SearchBar
import br.com.fiap.ecoSafe.ui.theme.RobotoFontFamily

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
                Spacer(modifier = Modifier.height(5.dp))
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
            .padding(horizontal = 2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar {}
    }
}

@Composable
fun FaunaLocalScreen(

) {

     BannerWithMap() // Para exibir um mapa

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) // Adiciona um padding geral na tela
    ) {

     // Títulos
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "Todos",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp , // Tamanho da fonte muda ao clicar
                modifier = Modifier
                    .offset(y = (-3).dp)
                    .clickable {  },
                color =  Color(0xFF074B07)
            )

            Text(
                text = "Em perigo",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp, // Tamanho da fonte muda ao clicar
                modifier = Modifier
                    .offset(y = (-3).dp)
                   .clickable {},
                color =  Color(0xFF2D2A2A) // Muda a cor ao clicar
            )

            Text(
                text = "Vunerável",
                fontWeight = FontWeight.Bold,
                fontSize =  13.sp, // Tamanho da fonte muda ao clicar
                modifier = Modifier
                    .offset(y = (-3).dp)
                    .clickable {  },
                color =  Color(0xFF2D2A2A) // Muda a cor ao clicar
            )

        }

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Explore a biodiversidade em diferentes regiões do Brasil",
                fontSize = 13.sp,
                fontFamily = RobotoFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFC09119)
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Encontros Recentes",
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF35580C)
            //modifier = Modifier.offset(x = 2.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            NumberWithText(
                number = "127",
                text = "Espécies",
                modifier = Modifier.weight(1f)
            )
            NumberWithText(
                number = "45",
                text = "Locais",
                modifier = Modifier.weight(1f)
            )
            NumberWithText(
                number = "2,3 mil",
                text = "Registros",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Card 1
        AnimalCard(
            name = "Mico-leão-dourado",
            location = "Amazônia, Brasil",
            status = "Em perigo",
            statusColor = Color(0xFFD20E0E),
            iconResId = R.drawable.location_map,
            sightingsText = "75 Avistamentos",
            onClick = {
                 }
        )

        Spacer(modifier = Modifier.height(8.dp)) // Espaço entre os cards

        // Card 2
        AnimalCard(
            name = "Onça-pintada",
            location = "Pantanal, Brasil",
            status = "Vulnerável",
            statusColor = Color(0xFFC09119),
            iconResId = R.drawable.location_map,
            sightingsText = "50 Avistamentos",
            onClick = {
                // Ação ao clicar no card
                println("Card 2 clicado!")
            }
        )

        Spacer(modifier = Modifier.height(8.dp)) // Espaço entre os cards

        // Card 3
        AnimalCard(
            name = "Curua-marrom",
            location = "Pantanal, Brasil",
            status = "Vulnerável",
            statusColor = Color(0xFFC09119),
            iconResId = R.drawable.location_map,
            sightingsText = "123 Avistamentos",
            onClick = {
                // Ação ao clicar no card
                println("Card 3 clicado!")
            }
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}



@Composable
fun NumberWithText(number: String, text: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Número em cima
        Text(
            text = number,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        // Texto embaixo
        Text(
            text = text,
            fontSize = 13.sp,
            color = Color.Gray
        )
    }
}