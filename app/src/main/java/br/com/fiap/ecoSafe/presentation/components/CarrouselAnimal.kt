package br.com.fiap.ecoSafe.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.ecosafe.R

@Composable
fun AnimalCarousel(
    modifier: Modifier = Modifier, // Permite personalizar o layout do carrossel
    cardWidth: Int = 300, // Largura padrão do card
    cardHeight: Int = 400,
    imageCard :Int = 200,// Altura padrão do card
    iconId: Int,
    contentPadding: PaddingValues = PaddingValues(vertical = 16.dp), // Espaçamento interno
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(16.dp), // Espaçamento entre os itens

) {
    val animals = listOf(
        Animal(
            name = "Arara-Azul",
            location = "Pantanal, Brasil",
            date = "11/02/2025",
            time = "12:45 p.m.",
            status = "Em perigo",
            imageRes = R.drawable.arara2
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
                    cardHeight = cardHeight,
                    imageCard = imageCard,
                    iconId = iconId
                )
            }
        }
    }
}

@Composable
fun AnimalCard(
    animal: Animal,
    cardWidth: Int,
    cardHeight: Int,
    fontSize: TextUnit = 16.sp,
    imageCard:Int,
    iconId: Int,
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

        ) {
            Image(
                painter = painterResource(id = animal.imageRes),
                contentDescription = animal.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageCard.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(9.dp))


         Column (
                modifier = Modifier.fillMaxSize().padding(start = 8.dp)

         ) {
             Text(
                 text = animal.name,
                 fontSize = fontSize.times(1.25f),
                 fontWeight = FontWeight.Bold,
                 color = Color(0xFF35580C),
                 letterSpacing = 0.5.sp

             )

             Spacer(modifier = Modifier.height(6.dp))

            Row (
                modifier = Modifier.fillMaxWidth()
            ){
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = null,
                    tint = Color(0xFF177437),
                    modifier = Modifier
                        .size(19.dp)
                        .offset(y = 3.dp)

                )

                Text(
                    text = animal.location,
                    fontSize = fontSize,
                    color = Color(0xFF696565)
                )
               }
             Spacer(modifier = Modifier.height(6.dp))

             Text(
                 text = buildAnnotatedString {
                     withStyle(
                         style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,// Cor cinza para "Situação:"
                         )
                     ) {
                         append("Data: ")
                     }
                     withStyle(
                         style = SpanStyle(
                             fontWeight = FontWeight.Normal                     )
                     ) {
                         append(animal.date)
                     }
                 },
                 fontSize = fontSize,
                 color = Color(0xFF696565)

             )

             Spacer(modifier = Modifier.height(6.dp))

             Text(
                 text = buildAnnotatedString {
                     withStyle(
                         style = SpanStyle(
                             fontWeight = FontWeight.SemiBold,// Cor cinza para "Situação:"
                         )
                     ) {
                         append("Hora: ")
                     }
                     withStyle(
                         style = SpanStyle(
                             fontWeight = FontWeight.Normal                     )
                     ) {
                         append(animal.time)
                     }
                 },
                 fontSize = fontSize,
                 color = Color(0xFF696565)

             )

             Spacer(modifier = Modifier.height(12.dp))

             // Texto com partes estilizadas
             Text(
                 text = buildAnnotatedString {
                     withStyle(
                         style = SpanStyle(
                             color = Color(0xFF696565) // Cor cinza para "Situação:"
                         )
                     ) {
                         append("Situação: ")
                     }
                     withStyle(
                         style = SpanStyle(
                             color = Color(0xFFD61212) // Cor vermelha para o status
                         )
                     ) {
                         append(animal.status)
                     }
                 },
                 fontSize = fontSize.times(1.0f),
                 fontWeight = FontWeight.Bold,
                 letterSpacing = 1.sp
             )

         }
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