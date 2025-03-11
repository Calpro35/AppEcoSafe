package br.com.fiap.ecoSafe.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.CacheDrawModifierNode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.presentation.componets.HamburgerMenu

import br.com.fiap.ecoSafe.ui.theme.InterFontFamily
import br.com.fiap.ecosafe.R

@Composable
fun HomeScreen(navController: NavController) {
    var isMenuOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Conteúdo principal da tela
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ) {
            item {
                HeaderSection(onMenuClick = { isMenuOpen = true })
                Spacer(modifier = Modifier.height(24.dp).padding(10.dp))
                StatisticsSection()
                Spacer(modifier = Modifier.height(15.dp))
                IdentifySpeciesButton()
                Spacer(modifier = Modifier.height(30.dp))
                RecentDiscoveriesSection()
                Spacer(modifier = Modifier.height(24.dp))
                ResourcesSection()
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
    }
}

@Composable
fun HeaderSection(
    onMenuClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Linha com o ícone do menu e o texto "Home"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Texto "Home" centralizado
            Text(
                text = "Home",
                modifier = Modifier
                    .weight(1f) // Ocupa o espaço restante e centraliza o texto
                    .offset(x = 22.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2D2A2A),


            )

            // Ícone do menu hambúrguer (agora à direita)
            IconButton(
                onClick = onMenuClick,
                modifier = Modifier.size(48.dp) // Tamanho do ícone aumentado
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.menu_ham),
                    contentDescription = "Abrir Menu",
                    modifier = Modifier.size(32.dp) // Tamanho do ícone interno
                )
            }
        }

        // Reduzir o espaço abaixo do banner
        Spacer(modifier = Modifier.height(4.dp)) // Espaço reduzido

        // Banner e logo
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.banner),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )

            Image(
                painter = painterResource(id = R.drawable.ecosafe_home),
                contentDescription = "Logo",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .size(75.dp)
                    .aspectRatio(1f)
                    .offset(x = 4.dp, y = 48.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.logo_home),
                contentDescription = "Logo",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .size(70.dp)
                    .aspectRatio(1f)
                    .offset(x = 18.dp, y = 90.dp)
            )
        }

        // Reduzir o espaço abaixo do banner
        Spacer(modifier = Modifier.height(10.dp)) // Espaço reduzido

        // Textos abaixo do banner
        Text(
            text = "O Meio Ambiente Conectado com Você",
            fontSize = 16.sp,
            letterSpacing = 0.5.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = InterFontFamily,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Explore e Identifique\nPreserve e Proteja!",
            fontSize = 16.sp,
            letterSpacing = 0.5.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = InterFontFamily,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun StatisticsSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StatisticItem("1254", "Espécies")
        StatisticItem("120.54", "Usuários")
        StatisticItem("156", "Países")
    }
}

@Composable
fun StatisticItem(value: String, label: String) {
    Column(
        modifier = Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20)
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color(0xFF388E3C)
        )
    }
}

@Composable
fun IdentifySpeciesButton() {

   Row(
       modifier = Modifier.fillMaxWidth(),
       horizontalArrangement = Arrangement.Center
   ) {
       Button(
           onClick = { /* Ação ao clicar no botão */ },
           modifier = Modifier.size(width = 380.dp, height = 58.dp),
           colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF417505)),
           shape = RoundedCornerShape(8.dp)

       ) {
           Icon(
               painter = painterResource(id = R.drawable.camera_explore),
               contentDescription = "abrir camera",
               modifier = Modifier.size(37.dp)
                   .padding(5.dp)// Tamanho do ícone interno
           )

           Text(
               text = "Identificar Espécie",
               fontSize = 18.sp,
               letterSpacing = 1.sp,

               )
       }
   }
}

@Composable
fun RecentDiscoveriesSection() {
    val discoveries = listOf(
        Discovery("Arara-azul", "Pantanal, Brasil"),
        Discovery("Onça-Pintada", "Amazônia")
    )

    Column {
        Text(
            text = "Descobertas Recentes",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20),
            modifier = Modifier.offset(x = 8.dp)
            
        )
        Spacer(modifier = Modifier.height(8.dp))
        discoveries.forEach { discovery ->
            DiscoveryItem(discovery)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun DiscoveryItem(discovery: Discovery) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Ação ao clicar no item */ },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Column {
                Text(
                    text = discovery.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B5E20)
                )
                Text(
                    text = discovery.location,
                    fontSize = 14.sp,
                    color = Color(0xFF388E3C)
                )
            }
        }
    }
}

@Composable
fun ResourcesSection() {
    val resources = listOf(
        Resource("Identificação em tempo Real", "Use nossa IA para identificar espécies instantâneas"),
        Resource("Mapeamento de espécies", "Explore a Biodiversidade de diferentes regiões"),
        Resource("Análise de Dados", "Animais em extinção e Órgãos de Proteção")
    )

    Column {
        Text(
            text = "Recursos",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20)
        )
        Spacer(modifier = Modifier.height(8.dp))
        resources.forEach { resource ->
            ResourceCard(resource)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ResourceCard(resource: Resource) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Ação ao clicar no card */ },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = resource.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B5E20)
            )
            Text(
                text = resource.description,
                fontSize = 14.sp,
                color = Color(0xFF388E3C)
            )
        }
    }
}

data class Discovery(val name: String, val location: String)
data class Resource(val title: String, val description: String)