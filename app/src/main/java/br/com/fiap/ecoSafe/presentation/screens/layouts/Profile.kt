package br.com.fiap.ecoSafe.presentation.screens.layouts

import CustomTextField
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.ecoSafe.data.model.User
import br.com.fiap.ecoSafe.presentation.components.Footer
import br.com.fiap.ecoSafe.presentation.components.GradientButton
import br.com.fiap.ecoSafe.presentation.components.HamburgerMenu
import br.com.fiap.ecoSafe.ui.theme.RobotoFontFamily
import br.com.fiap.ecosafe.R

@Composable
fun Profile(navController: NavController) {
    var isMenuOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Conteúdo principal da tela
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ) {
            item {
                HeaderProfile(onMenuClick = { isMenuOpen = true })
                Spacer(modifier = Modifier
                    .height(24.dp)
                    .padding(10.dp))
                MainProfile()
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
fun HeaderProfile(onMenuClick: () -> Unit) {
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
                text = "Perfil",
                modifier = Modifier
                    .weight(1f) // Ocupa o espaço restante e centraliza o texto
                    .offset(x = 22.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                letterSpacing = 1.sp,
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
    }
}

@Composable
fun MainProfile() {

    var name by remember { mutableStateOf("José Silva Junior") }
    var email by remember { mutableStateOf("10/02/2025") }
    var password by remember { mutableStateOf("********") }
    var passwordNew by remember { mutableStateOf("*******") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally // Centraliza o conteúdo horizontalmente
    ) {
        // Conteudo da pagina

        Spacer(modifier = Modifier.height(5.dp))

        //ProfileImage()

        Text(
            text = "João Silva",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = "Desde: 10/02/2025",
            fontSize = 16.sp,
            color = Color.Gray,
       )


        Spacer(modifier = Modifier.height(25.dp))

        CustomTextField(
            value = name,
            onValueChange = { name = it },
            label = "Nome",
            placeholder = "Digite seu nome completo"
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            value = email,
            onValueChange = { email = it },
            label = "E-mail",
            placeholder = "email@email.com",
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.email_24),
                    contentDescription = "Email",
                    tint = Color.Gray
                )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            value = password,
            onValueChange = { password = it },
            label = "Senha",
            placeholder = "Digite sua senha",
            trailIcon = {
                IconButton(
                    onClick = { isPasswordVisible = !isPasswordVisible }
                ) {
                    Icon(
                        painter = painterResource(
                            if (isPasswordVisible) R.drawable.visibility_on else R.drawable.visibility_off
                        ),
                        contentDescription = if (isPasswordVisible) "Ocultar senha" else "Mostrar senha",
                        tint = Color.Gray
                    )
                }
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            value = passwordNew,
            onValueChange = { passwordNew = it },
            label = "Confirma Senha",
            placeholder = "Digite Novamente",
            trailIcon = {
                IconButton(
                    onClick = { isConfirmPasswordVisible = !isConfirmPasswordVisible }
                ) {
                    Icon(
                        painter = painterResource(
                            if (isConfirmPasswordVisible) R.drawable.visibility_on else R.drawable.visibility_off
                        ),
                        contentDescription = if (isConfirmPasswordVisible) "Ocultar senha" else "Mostrar senha",
                        tint = Color.Gray
                    )
                }
            },
            visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(10.dp))

        GradientButton(
            text = "Alterar",
            onClick = {
                // Lógica para alterar senha

            },

        )
    }
}


//@Composable
//fun ProfileImage() {
//    var image by remember { mutableIntStateOf(R.drawable.placeholder) } // Use uma imagem placeholder inicial
//
//    Box(
//        contentAlignment = Alignment.BottomEnd,
//        modifier = Modifier.size(120.dp)
//    ) {
//        Image(
//            painter = painterResource(id = image),
//            contentDescription = "Profile Image",
//            modifier = Modifier
//                .size(120.dp)
//                .clip(CircleShape),
//            contentScale = ContentScale.Crop
//        )
//
//        IconButton(
//            onClick = { /* Lógica para abrir a galeria ou câmera */ },
//            modifier = Modifier.size(40.dp)
//        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.add_photo), // Ícone de câmera
//                contentDescription = "Upload Photo"
//            )
//        }
//    }
//}