package br.com.fiap.ecoSafe.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.presentation.navigation.Screen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.ecoSafe.ui.theme.RobotoFontFamily
import br.com.fiap.ecosafe.R


@Composable
fun HomeScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            //.background(Color(0xFFE8F5E9)) // Cor de fundo
            .padding(16.dp)
    ) {
        item {
            HeaderSection()
            Spacer(modifier = Modifier.height(24.dp))
            StatisticsSection()
            Spacer(modifier = Modifier.height(24.dp))
            IdentifySpeciesButton()
            Spacer(modifier = Modifier.height(24.dp))
            RecentDiscoveriesSection()
            Spacer(modifier = Modifier.height(24.dp))
            ResourcesSection()
        }
    }
}

@Composable
fun HeaderSection(

) {

    Text(
        text = "Home",
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Bold,
        color = Color.Black ,
        textAlign = TextAlign.Center

    )

    Image(
        painter = painterResource(id = R.drawable.banner), // Ícone de exemplo
        contentDescription = null,
        modifier = Modifier.fillMaxWidth()

         )

    Text(
        text = "O Meio Ambiente Conectado com Você",
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Gray ,
        textAlign = TextAlign.Center
    )

    Text(
        text = "Explore e Identifique\nProteja e Preserve!",
        fontSize = 16.sp,
        color = Color(0xFF388E3C)
    )
}

@Composable
fun StatisticsSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StatisticItem("1254", "Espécies")
        StatisticItem("120.54", "Usuários")
        StatisticItem("156", "Países")
    }
}

@Composable
fun StatisticItem(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
    Button(
        onClick = { /* Ação ao clicar no botão */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
    ) {
        Text(text = "Identificar Espécie", fontSize = 18.sp)
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
            color = Color(0xFF1B5E20)
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
                painter = painterResource(id = R.drawable.ic_logo), // Ícone de exemplo
                contentDescription = null,
                modifier = Modifier.size(40.dp)
                        //Spacer(modifier = Modifier.width(16.dp))
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