package br.com.fiap.ecoSafe.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.ecosafe.R

@Composable
fun AnimalCard(
    name: String,
    location: String,
    status: String,
    statusColor: Color,
    iconResId: Int,
    sightingsText: String,
    onClick: () -> Unit // Função de callback para o clique
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
            .clickable { onClick() }// Margem externa do card
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(7.dp) // Padding interno do card
    ) {
        // Coluna à esquerda: Nome do animal e localização
        Column(
            modifier = Modifier
                .weight(1f),
              // .padding(start = 3.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = name,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 6.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = null,
                    tint = Color(0xFF177437),
                    modifier = Modifier
                        .size(23.dp)
                        .padding(end = 6.dp)
                )
                Text(
                    text = location,
                    fontSize = 13.sp,
                    color = Color(0xFF696565)
                )
            }
        }

        // Coluna à direita: Status e avistamentos
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .padding(end = 6.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = status,
                fontSize = 12.sp,
                color = statusColor,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.visibility_on),
                    contentDescription = null,
                    tint = Color(0xFF314C1C),
                    modifier = Modifier
                        .size(22.dp)
                        .padding(end = 4.dp)
                )
                Text(
                    text = sightingsText,
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }
        }
    }
}