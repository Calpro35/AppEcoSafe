package br.com.fiap.ecoSafe.presentation.screens.layouts

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement.Horizontal
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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

@Composable
fun CameraScreen(
    onBackClick: () -> Unit,
    navController: NavController
) {
    var isMenuOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header da tela
            HeaderCamera(onMenuClick = { isMenuOpen = true })

            // Conteúdo principal da câmera
            CameraContent(
                onBackClick = onBackClick,
                navController = navController
            )
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
//        Box(
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
//                .fillMaxWidth()
//        ) {
//            Footer(navController = navController)
//        }
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
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 1.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Texto "Explore" centralizado
            Text(
                text = "Explore",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 32.dp),
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
    navController: NavController
) {
    val context = LocalContext.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

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
                onBackClick() // Voltar se a permissão for negada
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
                    setEnabledUseCases(CameraController.IMAGE_CAPTURE)
                    bindToLifecycle(lifecycleOwner)
                }
            }

            // Exibir a visualização da câmera
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.75f) // Ocupa todo o espaço disponível
            ) {
                AndroidView(
                    factory = { context ->
                        PreviewView(context).apply {
                            this.controller = controller
                            scaleType = PreviewView.ScaleType.FILL_START
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
                // Canvas para desenhar o quadrado de foco
                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height

                    // Tamanho do quadrado de foco
                    val focusSize = 320.dp.toPx()

                    // Coordenadas para centralizar o quadrado
                    val focusLeft = (canvasWidth - focusSize) / 2
                    val focusTop = (canvasHeight - focusSize) / 2
                    val focusRight = focusLeft + focusSize
                    val focusBottom = focusTop + focusSize

                    // Cor e espessura das linhas
                    val focusColor = Color(0xFFCDC4C4)
                    val focusStrokeWidth = 3.dp.toPx()

                    // Desenhar as linhas nos cantos do quadrado
                    drawLine(
                        color = focusColor,
                        start = Offset(focusLeft, focusTop),
                        end = Offset(focusLeft + focusSize / 4, focusTop),
                        strokeWidth = focusStrokeWidth
                    )
                    drawLine(
                        color = focusColor,
                        start = Offset(focusLeft, focusTop),
                        end = Offset(focusLeft, focusTop + focusSize / 4),
                        strokeWidth = focusStrokeWidth
                    )
                    drawLine(
                        color = focusColor,
                        start = Offset(focusRight, focusTop),
                        end = Offset(focusRight - focusSize / 4, focusTop),
                        strokeWidth = focusStrokeWidth
                    )
                    drawLine(
                        color = focusColor,
                        start = Offset(focusRight, focusTop),
                        end = Offset(focusRight, focusTop + focusSize / 4),
                        strokeWidth = focusStrokeWidth
                    )
                    drawLine(
                        color = focusColor,
                        start = Offset(focusLeft, focusBottom),
                        end = Offset(focusLeft + focusSize / 4, focusBottom),
                        strokeWidth = focusStrokeWidth
                    )
                    drawLine(
                        color = focusColor,
                        start = Offset(focusLeft, focusBottom),
                        end = Offset(focusLeft, focusBottom - focusSize / 4),
                        strokeWidth = focusStrokeWidth
                    )
                    drawLine(
                        color = focusColor,
                        start = Offset(focusRight, focusBottom),
                        end = Offset(focusRight - focusSize / 4, focusBottom),
                        strokeWidth = focusStrokeWidth
                    )
                    drawLine(
                        color = focusColor,
                        start = Offset(focusRight, focusBottom),
                        end = Offset(focusRight, focusBottom - focusSize / 4),
                        strokeWidth = focusStrokeWidth
                    )
                }
            }

            // Botão de captura
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp) // Padding para o botão
                    .height(100.dp)
                    .offset(x = (-6).dp, y = (-10).dp), // Altura fixa para o botão
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = {
                        // Capturar a foto
                        val file = File(context.externalCacheDir, "photo.jpg")
                        val outputOptions = ImageCapture.OutputFileOptions.Builder(file).build()

                        controller.takePicture(
                            outputOptions,
                            ContextCompat.getMainExecutor(context),
                            object : ImageCapture.OnImageSavedCallback {
                                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                                    // Ler a foto salva e converter para ByteArray
                                    val imageBytes = file.readBytes()
                                    val imageBase64 = imageBytes.toBase64()

                                    // Navegar para a tela de detalhes da foto
                                    navController.navigate("photo_details/$imageBase64")
                                }

                                override fun onError(exception: ImageCaptureException) {
                                    // Tratar erro
                                    Log.e("CameraContent", "Erro ao capturar foto: ${exception.message}")
                                }
                            }
                        )
                    },
                    modifier = Modifier.size(75.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.btn_photo),
                        contentDescription = "Capturar Foto",
                        modifier = Modifier.size(90.dp)
                    )
                }
            }
        } else {
            PermissionDeniedScreen(onBackClick = onBackClick)
        }
    }
}