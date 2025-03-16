package br.com.fiap.ecoSafe.presentation.screens.layouts

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.presentation.components.Footer
import br.com.fiap.ecoSafe.presentation.components.HamburgerMenu
import br.com.fiap.ecoSafe.presentation.components.PermissionDeniedScreen
import br.com.fiap.ecosafe.R
import java.io.File
import br.com.fiap.ecoSafe.utils.toBase64 // Ajuste o caminho do pacote
import java.util.Base64


@RequiresApi(Build.VERSION_CODES.O)
fun ByteArray.toBase64(): String {
    val charset = Charsets.UTF_8
    return String(Base64.getEncoder().encode(this), charset)
}
@Composable
fun CameraScreen(
    onBackClick: () -> Unit,
    navController: NavController
) {
    var isMenuOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize().padding(top = 20.dp)) {
        Column {
            // Header da tela
            HeaderCamera(onMenuClick = { isMenuOpen = true })

            // Conteúdo principal da câmera
            CameraContent(
                onBackClick = onBackClick,
                onCaptureClick = { imageBytes ->
                    // Navegar para a tela de detalhes da foto
                    navController.navigate("photo_details_screen/${imageBytes.toBase64()}")
                }
            )
        }

        // Menu Hambúrguer (fora do Column, mas dentro do Box)
        if (isMenuOpen) {
            HamburgerMenu(
                onCloseClick = { isMenuOpen = false },
                onItemClick = { destination ->
                    navController.navigate(destination)
                    isMenuOpen = false
                }
            )
        }

        // Footer fixo na parte inferior (fora do Column, mas dentro do Box)
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
fun HeaderCamera(
    onMenuClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
               // .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Texto "Explore" centralizado
            Text(
                text = "Explore",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 25.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                letterSpacing = 1.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF35580C)
            )

            // Ícone do menu hambúrguer (agora à direita)
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
fun CameraContent(
    onBackClick: () -> Unit,
    onCaptureClick: (image: ByteArray) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    // Estado para verificar se a permissão da câmera foi concedida
    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    // Launcher para solicitar permissão da câmera
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCameraPermission = granted
            if (!granted) {
                // Caso a permissão seja negada, exiba uma mensagem ou redirecione
                onBackClick()
            }
        }
    )

    // Solicitar permissão da câmera ao iniciar a tela
    LaunchedEffect(key1 = true) {
        if (!hasCameraPermission) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (hasCameraPermission) {
            // Configurar o controlador da câmera
            val controller = remember {
                LifecycleCameraController(context).apply {
                    setEnabledUseCases(CameraController.IMAGE_CAPTURE or CameraController.VIDEO_CAPTURE)
                    bindToLifecycle(lifecycleOwner)
                }
            }

            // Exibir a visualização da câmera (70% da tela)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.75f) // Ocupa 70% da altura da tela
            ) {
                AndroidView(
                    factory = { context ->
                        PreviewView(context).apply {
                            this.controller = controller
                            scaleType = PreviewView.ScaleType.FIT_CENTER
                        }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            // Espaço para o botão (30% da tela)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.25f) // Ocupa 30% da altura da tela
                    .padding(0.dp),
                contentAlignment = Alignment.Center // Centraliza o botão
            ) {
                IconButton(
                    onClick = {
                        // Capturar a foto
                        val outputOptions = ImageCapture.OutputFileOptions.Builder(
                            File(context.externalCacheDir, "photo.jpg") // Salvar a foto em um arquivo temporário
                        ).build()

                        controller.takePicture(
                            outputOptions,
                            ContextCompat.getMainExecutor(context),
                            object : ImageCapture.OnImageSavedCallback {
                                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                                    // Ler a foto salva e converter para ByteArray
                                    val file = File(context.externalCacheDir, "photo.jpg")
                                    val imageBytes = file.readBytes()
                                    onCaptureClick(imageBytes) // Passar a foto capturada
                                }

                                override fun onError(exception: ImageCaptureException) {
                                    // Tratar erro
                                    Log.e("CameraContent", "Erro ao capturar foto: ${exception.message}")
                                }
                            }
                        )
                    },
                    modifier = Modifier
                        .size(70.dp).offset(x =(-5).dp, y = (-25).dp )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_radio_button_checked_24), // Ícone de câmera
                        contentDescription = "Capturar Foto",
                        modifier = Modifier.size(80.dp)
                                            )
                }
            }
        } else {
            // Exibir uma tela ou mensagem caso a permissão seja negada
            PermissionDeniedScreen(onBackClick = onBackClick)
        }
    }
}