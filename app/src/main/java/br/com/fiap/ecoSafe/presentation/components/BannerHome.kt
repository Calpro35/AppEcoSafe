package br.com.fiap.ecoSafe.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

@Composable
fun BannerItem(
    icon: Painter,
    number: String,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
        .width(150.dp)
        .height(90.dp)
) {
    Box(
        modifier = modifier
            .background(Color(0xFFF4F4F4), shape = RoundedCornerShape(8.dp))
            //.border(width = )
            .clickable { onClick() }
            .zIndex(2f), // Adiciona um pequeno shadow (pode ser ajustado)
            //.padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Ícone
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color(0xFF417505) // Cor do ícone
            )

            // Número
            Text(
                text = number,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF417505)
            )

            // Texto
            Text(
                text = text,
                fontSize = 14.sp,
                color = Color.Gray,
                letterSpacing = 1.sp
            )
        }
    }
}

