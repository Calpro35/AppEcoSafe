//package br.com.fiap.ecoSafe.presentation.components
//
//import android.net.Uri
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.core.content.FileProvider
//import coil.compose.rememberImagePainter
//import java.io.File
//
//@Composable
//fun CameraScreen() {
//    val context = LocalContext.current
//    var imageUri by remember { mutableStateOf<Uri?>(null) }
//    var capturedImage by remember { mutableStateOf<File?>(null) }
//
//    // Cria um arquivo temporário para salvar a foto
//    val photoFile = remember {
//        File.createTempFile(
//            "photo_", // Prefixo do nome do arquivo
//            ".jpg",   // Sufixo do nome do arquivo
//            context.externalCacheDir // Diretório para salvar o arquivo
//        )
//    }
//
//    // URI do arquivo
//    val photoUri = remember {
//        FileProvider.getUriForFile(
//            context,
//            "${context.packageName}.fileprovider", // Autoridade do FileProvider
//            photoFile
//        )
//    }
//
//    // Launcher para abrir a câmera
//    val cameraLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.TakePicture(),
//        onResult = { success ->
//            if (success) {
//                imageUri = photoUri
//                capturedImage = photoFile
//            }
//        }
//    )
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp), // Padding lateral de 16.dp
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // Botão para abrir a câmera
//        Button(
//            onClick = { cameraLauncher.launch(photoUri) }, // Abre a câmera ao clicar
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(60.dp)
//        ) {
//            Text(text = "Abrir Câmera")
//        }
//
//        // Exibir a foto capturada
//        if (imageUri != null) {
//            Spacer(modifier = Modifier.height(16.dp))
//            Image(
//                painter = rememberImagePainter(data = imageUri),
//                contentDescription = "Foto Capturada",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .aspectRatio(1f) // Mantém a proporção quadrada
//            )
//        }
//    }
//}