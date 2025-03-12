package br.com.fiap.ecoSafe.presentation.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.ecosafe.R

@Composable
fun AnimalCarousel(
    modifier: Modifier = Modifier, // Permite personalizar o layout do carrossel
    cardWidth: Int = 300, // Largura padrão do card
    cardHeight: Int = 400, // Altura padrão do card
    contentPadding: PaddingValues = PaddingValues(vertical = 16.dp), // Espaçamento interno
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(16.dp) // Espaçamento entre os itens
) {
    val animals = listOf(
        Animal(
            name = "Arara Azul",
            location = "Pantanal, Brasil",
            date = "11/02/2025",
            time = "12:45 p.m.",
            status = "Em perigo",
            imageRes = R.drawable.arara
        ),
        Animal(
            name = "Onça Pintada",
            location = "Amazônia, Brasil",
            date = "16/02/2025",
            time = "10:30 a.m.",
            status = "Vulnerável",
            imageRes = R.drawable.oncapintada
        ),
        Animal(
            name = "Tartaruga Gigante",
            location = "Galápagos, Equador",
            date = "17/02/2025",
            time = "09:15 a.m.",
            status = "Em perigo",
            imageRes = R.drawable.tartarugagigante
        )
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = contentPadding,
            horizontalArrangement = horizontalArrangement
        ) {
            items(animals) { animal ->
                AnimalCard(
                    animal = animal,
                    cardWidth = cardWidth,
                    cardHeight = cardHeight
                )
            }
        }
    }
}

@Composable
fun AnimalCard(
    animal: Animal,
    cardWidth: Int,
    cardHeight: Int
) {
    Card(
        modifier = Modifier
            .width(cardWidth.dp)
            .height(cardHeight.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(248, 248, 255)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = animal.imageRes),
                contentDescription = animal.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = animal.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = animal.location,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Data: ${animal.date}",
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Hora: ${animal.time}",
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Situação: ${animal.status}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
        }
    }
}

data class Animal(
    val name: String,
    val location: String,
    val date: String,
    val time: String,
    val status: String,
    val imageRes: Int
)