package br.com.fiap.ecoSafe.presentation.screens.layouts

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap


@Composable
fun PhotoScreen(imageBase64: String?) {
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(imageBase64) {
        if (imageBase64 != null) {
            try {
                val imageBytes = Base64.decode(imageBase64, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                imageBitmap = bitmap?.asImageBitmap()
            } catch (e: Exception) {
                errorMessage = "Erro ao carregar a imagem"
            }
        } else {
            errorMessage = "Nenhuma imagem disponível"
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (imageBitmap != null) {
            Image(
                bitmap = imageBitmap!!,
                contentDescription = "Captured Image",
                modifier = Modifier.fillMaxSize()
            )
        } else if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}