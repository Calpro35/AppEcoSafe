package br.com.fiap.ecoSafe.presentation.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import br.com.fiap.ecoSafe.presentation.components.AnimalCarousel
import br.com.fiap.ecoSafe.presentation.components.BannerItem
import br.com.fiap.ecoSafe.presentation.components.HamburgerMenu
import br.com.fiap.ecoSafe.ui.theme.InterFontFamily
import br.com.fiap.ecosafe.R

@Composable
fun HomeScreen(navController: NavController) {
    var isMenuOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize().padding(top = 5.dp)) {
        // Conteúdo principal da tela (rolável)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp) // Espaço para o footer
        ) {
            item {
                HeaderSection(onMenuClick = { isMenuOpen = true })
                Spacer(modifier = Modifier.height(5.dp))
                StatisticsSection()
                Spacer(modifier = Modifier.height(3.dp))
                IdentifySpeciesButton(onClick = {
                    navController.navigate("camera_screen") // Navega para a tela de câmera
                })
                Spacer(modifier = Modifier.height(30.dp))
                RecentDiscoveriesSection()
                Spacer(modifier = Modifier.height(15.dp))
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
                    .offset(x = 19.dp, y = 4.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF35580C),
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
        Spacer(modifier = Modifier.height(8.dp)) // Espaço reduzido

        // Banner e logo
        Box(
            modifier = Modifier
                .fillMaxWidth() // Ocupa a largura máxima disponível
        ) {
            Image(
                painter = painterResource(id = R.drawable.banner),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )

            Image(
                painter = painterResource(id = R.drawable.ecosafehome),
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
        Spacer(modifier = Modifier.height(9.dp)) // Espaço reduzido

        // Textos abaixo do banner
        Text(
            text = "O Meio Ambiente Conectado com Você",
            fontSize = 16.sp,
            letterSpacing = 0.9.sp,
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Primeira linha com dois banners lado a lado
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Banner 1: Espécies
            BannerItem(
                icon = painterResource(id = R.drawable.leaf_banner),
                number = "1254",
                text = "Espécies",
                onClick = { /* Ação ao clicar */ },
                modifier = Modifier
                    .weight(1f) // Ocupa o mesmo espaço
                    .padding(end = 8.dp) // Espaçamento entre os banners
                    .height(90.dp) // Mesma altura do terceiro banner
            )

            // Banner 2: Usuários
            BannerItem(
                icon = painterResource(id = R.drawable.group_banner),
                number = "120.54",
                text = "Usuários",
                onClick = { /* Ação ao clicar */ },
                modifier = Modifier
                    .weight(1f) // Ocupa o mesmo espaço
                    .padding(start = 8.dp) // Espaçamento entre os banners
                    .height(90.dp) // Mesma altura do terceiro banner
            )
        }

        // Espaçamento entre os banners
        Spacer(modifier = Modifier.height(15.dp))

        // Banner 3: Países (banner maior)
        BannerItem(
            icon = painterResource(id = R.drawable.world), // Substitua pelo seu ícone
            number = "156",
            text = "Países",
            onClick = { /* Ação ao clicar */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp) // Mesma altura dos outros banners
        )
    }
}

@Composable
fun IdentifySpeciesButton(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.size(width = 380.dp, height = 58.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF157A38)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.camera_explore),
                contentDescription = "abrir camera",
                modifier = Modifier.size(37.dp)
                    .padding(5.dp) // Tamanho do ícone interno
            )

            Text(
                text = "Identificar Espécie",
                fontSize = 18.sp,
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
fun RecentDiscoveriesSection() {
    Column {
        Text(
            text = "Descobertas Recentes",
            fontSize = 19.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF35580C),
            modifier = Modifier.offset(x = 15.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))

        AnimalCarousel(
            modifier = Modifier.fillMaxWidth(),
            cardWidth = 200, // Cards menores
            cardHeight = 350, // Altura reduzida
            contentPadding = PaddingValues(horizontal = 7.dp), // Espaçamento horizontal
            horizontalArrangement = Arrangement.spacedBy(12.dp),// Menor espaçamento entre os itens
            imageCard = 150,
            iconId = R.drawable.location_map

        )
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
            fontSize = 19.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF35580C),
            modifier = Modifier.offset(x = 15.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))
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
            .fillMaxWidth().padding(5.dp)
            .clickable { /* Ação ao clicar no card */ },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White // Cor de fundo do card
        ),
        border = BorderStroke(
            width = 1.dp, // Espessura da borda
            color = Color.LightGray.copy(alpha = 0.5f) // Cor da borda com transparência
        )
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = resource.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                text = resource.description,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

data class Resource(val title: String, val description: String)